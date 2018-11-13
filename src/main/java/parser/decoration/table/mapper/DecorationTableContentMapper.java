package parser.decoration.table.mapper;

import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.element.Affectation;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.Variable;
import model.seed.transducer.ArcSemanticLetter;
import parser.decoration.table.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.DecorationTablePOJO;
import parser.decoration.table.model.GuardPOJO;
import parser.decoration.table.model.TablePOJO;
import parser.decoration.table.model.UpdatePOJO;

import java.util.Optional;

import static parser.decoration.table.DecorationTableUtils.manageError;

public class DecorationTableContentMapper {

    private static final String tabColumnGuard = "GUARD";
    private static final String tabColumnUpdate = "UPDATE";

    // TODO - add semantic letter in parser

    /**
     * tt les erreurs sont remonté, continuer parsing jusqua pb bloquant
     * @param pojo
     * @param res
     * @param decorationTable
     */
    public static void parseDecorationTableContent(DecorationTablePOJO pojo, DecorationTableParsingResult res, DecorationTable decorationTable) {

        // Going through the decoration table instructions
        for(int tabIndex = 0; tabIndex < pojo.getTable().size(); tabIndex++) {

            TablePOJO tabItem = pojo.getTable().get(tabIndex);
            Instruction inst = new Instruction();

            String letter = tabItem.getLetter();

            Optional<ArcSemanticLetter> semanticLetter = ArcSemanticLetter.fromLabel(letter);
            String semanticLetterLab = semanticLetter.get().getLabel();

            if (tabItem.getLetter() == null) {
                manageError(res, DecorationTableParsingErrorType.INSTRUCTION_MISSING_SEMANTIC_LETTER, "in table element n°" + tabIndex);
            } else if (!semanticLetter.isPresent()) {
                manageError(res, DecorationTableParsingErrorType.INSTRUCTION_INVALID_SEMANTIQUE_LETTER,
                        "- expected: " + ArcSemanticLetter.valuesAsList() + ", actual: " + letter);
            } else {

                // Getting table line guards
                parserGuards(res, tabItem, inst, semanticLetterLab);

                // Getting table line updates
                parseUpdates(res, tabItem, inst, semanticLetterLab);

                decorationTable.addInstruction(semanticLetter.get(), inst);

            }

        }

    }


    private static Variable parseToVariable(String tabColumn, String semanticLetter, boolean canHaveIndex, String pojoName, Optional<String> pojoIndex, DecorationTableParsingResult res) {
        if(pojoName == null) {
            manageError(res, DecorationTableParsingErrorType.VARIABLE_MISSING_NAME,
                    "in " + tabColumn + "semantic letter " + semanticLetter);
        } else if (pojoIndex.isPresent() && !canHaveIndex) {
            manageError(res, DecorationTableParsingErrorType.VARIABLE_VALUE_UNEXPECTED_INDEX,
                    "in " + tabColumn + "semantic letter " + semanticLetter);
        }
        else if (!pojoIndex.isPresent()) {
            return new Variable(pojoName);
        } else {
            String indexStr = pojoIndex.get();
            Integer varIndex = Integer.parseInt(indexStr.substring(indexStr.indexOf("+"))); //TODO check var - 0 ou 1 ou plus
            return new IndexedVariable(pojoName, varIndex);
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    private static void parseUpdates(DecorationTableParsingResult res, TablePOJO tabItem, Instruction inst, String semanticLetterLab) {
        for (int updateIndex = 0; updateIndex < tabItem.getUpdates().size(); updateIndex++) {

            UpdatePOJO updatePOJO = tabItem.getUpdates().get(updateIndex);

            String updateVarName = updatePOJO.getVariable();
            Variable guardVar = parseToVariable(tabColumnUpdate, semanticLetterLab, false, updateVarName, Optional.empty(), res);

            Affectation aff = new Affectation(guardVar, ValueMapper.parseValue(tabColumnUpdate, semanticLetterLab, updatePOJO.getValue(), res));
            inst.addUpdate(guardVar.getName(), aff);

        }
    }

    private static void parserGuards(DecorationTableParsingResult res, TablePOJO tabItem, Instruction inst, String semanticLetterLab) {
        for (int guardIndex = 0; guardIndex < tabItem.getGuards().size(); guardIndex++) {

            GuardPOJO guardPOJO = tabItem.getGuards().get(guardIndex);

            String guardVarName = guardPOJO.getVariable();
            String guardVarIndex = guardPOJO.getIndex();
            Variable guardVar = parseToVariable(tabColumnGuard, semanticLetterLab, true, guardVarName, Optional.of(guardVarIndex), res);

            Affectation aff = new Affectation(guardVar, ValueMapper.parseValue(tabColumnGuard, semanticLetterLab, guardPOJO.getValue(), res));
            inst.addGuard(guardVar.getName(), aff);

        }
    }

}
