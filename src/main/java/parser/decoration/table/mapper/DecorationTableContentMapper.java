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

import static parser.decoration.table.errors.DecorationTableErrorHandler.handle;

/**
 * Decoration table mapper enabling pojo transformation to model.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public abstract class DecorationTableContentMapper {

    private static final String tabColumnGuard = "GUARD";
    private static final String tabColumnUpdate = "UPDATE";
    static final String tabColumnReturn = "RETURN";
    static final String tabColumnRegister = "REGISTER";

    /**
     * Main mapper enabling {@link DecorationTablePOJO} transformation to {@link DecorationTable}.
     * If an error occurs, then the result will not contain any decoration table value.
     * Error are added to {@link DecorationTableParsingResult} object.
     * @param pojo The mapped {@link DecorationTablePOJO} related to the Seed Transducer JSON file
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @param decorationTable The {@link DecorationTable} parsed object (modified by process)
     */
    public static void mapDecorationTableContent(DecorationTablePOJO pojo, DecorationTableParsingResult res, DecorationTable decorationTable) {

        // Going through the decoration table instructions
        for(int tabIndex = 0; tabIndex < pojo.getTable().size(); tabIndex++) {

            TablePOJO tabItem = pojo.getTable().get(tabIndex);
            Instruction inst = new Instruction();

            String letter = tabItem.getLetter();

            Optional<ArcSemanticLetter> semanticLetter = ArcSemanticLetter.fromLabel(letter);

            if (letter == null) {
                handle(res, DecorationTableParsingErrorType.INSTRUCTION_MISSING_SEMANTIC_LETTER, (tabIndex+1));
            } else if (!semanticLetter.isPresent()) {
                handle(res, DecorationTableParsingErrorType.INSTRUCTION_INVALID_SEMANTIC_LETTER,
                        (tabIndex+1), letter);
            } else {

                String semanticLetterLab = semanticLetter.get().getLabel();

                // Getting table line guards
                mapGuards(res, tabItem, inst, semanticLetterLab);

                // Getting table line updates
                mapUpdates(res, tabItem, inst, semanticLetterLab);

                // Adding decoration table after
                if (tabItem.getAfter() != null) {
                    decorationTable.addInstruction(semanticLetter.get(), tabItem.getAfter(), inst);
                } else {
                    decorationTable.addInstruction(semanticLetter.get(), inst);
                }

            }

        }

    }

    /**
     * Maps given pojo value to model Variable.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param tabColumn The concerned column in the decoration table (Register, Return, Update or Guard).
     * @param tabLine The table line corresponding to this instruction
     * @param semanticLetter The semantic letter corresponding to this instruction
     * @param pojoName The pojo name to map from
     * @param pojoIndex The pojo index to map from
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Variable}
     */
    private static Variable mapToVariable(String tabColumn, int tabLine, String semanticLetter, String pojoName, Optional<String> pojoIndex, DecorationTableParsingResult res) {
        if(pojoName == null) {
            handle(res, DecorationTableParsingErrorType.VARIABLE_MISSING_NAME,
                    tabColumn, (tabLine+1), semanticLetter);
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

    /**
     * Maps given update pojo element to model Update and adds it to {@link DecorationTableParsingResult} given object.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @param tabItem The table pojo corresponding to this instruction to parse
     * @param inst The model {@link Instruction} where the update value should be added
     * @param semanticLetterLab The semantic letter corresponding to this instruction
     */
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

    /**
     * Maps given guard pojo element to model Update and adds it to {@link DecorationTableParsingResult} given object.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @param tabItem The table pojo corresponding to this instruction to parse
     * @param inst The model {@link Instruction} where the update value should be added
     * @param semanticLetterLab The semantic letter corresponding to this instruction
     */
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
