package parser.decoration.table.mapper;

import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.element.Affectation;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.Variable;
import model.seed.transducer.ArcSemanticLetter;
import parser.decoration.table.process.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.DecorationTablePOJO;
import parser.decoration.table.model.GuardPOJO;
import parser.decoration.table.model.TablePOJO;
import parser.decoration.table.model.UpdatePOJO;

import java.util.List;
import java.util.Optional;

import static parser.decoration.table.process.DecorationTableUtils.manageError;

public class DecorationTableContentMapper {

    public static final String tabColumnGuard = "GUARD";
    public static final String tabColumnUpdate = "UPDATE";
    public static final String tabColumnReturn = "RETURN";
    public static final String tabColumnRegister = "REGISTER";

    // TODO - add semantic letter in parser

    /**
     * tt les erreurs sont remonté, continuer parsing jusqua pb bloquant
     * @param pojo
     * @param res
     * @param decorationTable
     */
    public static void mapDecorationTableContent(DecorationTablePOJO pojo, DecorationTableParsingResult res, DecorationTable decorationTable) {

        // Going through the decoration table instructions
        for(int tabIndex = 0; tabIndex < pojo.getTable().size(); tabIndex++) {

            TablePOJO tabItem = pojo.getTable().get(tabIndex);
            Instruction inst = new Instruction();

            String letter = tabItem.getLetter();

            Optional<ArcSemanticLetter> semanticLetter = ArcSemanticLetter.fromLabel(letter);

            if (letter == null) {
                manageError(res, DecorationTableParsingErrorType.INSTRUCTION_MISSING_SEMANTIC_LETTER, "in table element n° " + (tabIndex+1));
            } else if (!semanticLetter.isPresent()) {
                manageError(res, DecorationTableParsingErrorType.INSTRUCTION_INVALID_SEMANTIC_LETTER,
                        "in table line n° " + (tabIndex+1) + "\n- expected: " + ArcSemanticLetter.valuesAsList() + ", actual: " + letter);
            } else {

                String semanticLetterLab = semanticLetter.get().getLabel();

                // Parsing after statement

                // Getting table line guards
                mapGuards(res, tabItem, inst, semanticLetterLab);

                // Getting table line updates
                mapUpdates(res, tabItem, inst, semanticLetterLab);

                if (tabItem.getAfter() != null) {
                    decorationTable.addInstruction(semanticLetter.get(), tabItem.getAfter(), inst);
                } else {
                    decorationTable.addInstruction(semanticLetter.get(), inst);

                }
            }

        }

    }

    private static Variable mapToVariable(String tabColumn, int guardIndex, String semanticLetter, String pojoName, Optional<String> pojoIndex, DecorationTableParsingResult res) {
        if(pojoName == null) {
            manageError(res, DecorationTableParsingErrorType.VARIABLE_MISSING_NAME,
                    "in " + tabColumn + " n° " + (guardIndex+1) + " for semantic letter " + semanticLetter);
        }
        else if (!pojoIndex.isPresent()) {
            return new Variable(pojoName);
        } else {
            String indexStr = pojoIndex.get();
            Integer varIndex = ValueMapper.parseVariableIndex(indexStr, semanticLetter, pojoName, tabColumn, res);
            return new IndexedVariable(pojoName, varIndex);
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    private static void mapUpdates(DecorationTableParsingResult res, TablePOJO tabItem, Instruction inst, String semanticLetterLab) {
        List<UpdatePOJO> updates = tabItem.getUpdates();
        if (updates != null) {
            for (int updateIndex = 0; updateIndex < tabItem.getUpdates().size(); updateIndex++) {

                UpdatePOJO updatePOJO = tabItem.getUpdates().get(updateIndex);

                String updateVarName = updatePOJO.getVariable();
                Variable guardVar = mapToVariable(tabColumnUpdate, updateIndex, semanticLetterLab, updateVarName, Optional.empty(), res);

                Affectation aff = new Affectation(guardVar, ValueMapper.mapValue(tabColumnUpdate, semanticLetterLab, updatePOJO.getValue(), res));
                inst.addUpdate(guardVar.getName(), aff);

            }
        }
    }

    private static void mapGuards(DecorationTableParsingResult res, TablePOJO tabItem, Instruction inst, String semanticLetterLab) {
        List<GuardPOJO> guards = tabItem.getGuards();
        if (guards != null) {
            for (int guardIndex = 0; guardIndex < tabItem.getGuards().size(); guardIndex++) {

                GuardPOJO guardPOJO = tabItem.getGuards().get(guardIndex);

                String guardVarName = guardPOJO.getVariable();
                String guardVarIndex = guardPOJO.getIndex();
                Variable guardVar = mapToVariable(tabColumnGuard, guardIndex, semanticLetterLab, guardVarName, Optional.of(guardVarIndex), res);

                Affectation aff = new Affectation(guardVar, ValueMapper.mapValue(tabColumnGuard, semanticLetterLab, guardPOJO.getValue(), res));
                inst.addGuard(guardVar.getName(), aff);

            }
        }
    }

}
