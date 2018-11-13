package parser.decoration.table.mapper;

import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.element.*;
import model.seed.transducer.ArcSemanticLetter;
import parser.decoration.table.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static parser.decoration.table.DecorationTableUtils.manageError;

public class DecorationTableMapper {

    private static final String tabColumnGuard = "GUARD";
    private static final String tabColumnUpdate = "UPDATE";

    // TODO - add semantic letter in parser

    public static Function parseInitValueToFunction(FunctionPOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            return new Function(pojo.getName(), new ArrayList<>());
        }
        return new Function(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_MISSING.getLabel(), new ArrayList<>());
    }

    // index interdit
    public static Variable parseRegisterValueToVariable(VariablePOJO pojo, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME,
                    pojo.getName());
        } else if (pojo.getIndex() != null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_REGISTER_VARIABLE_UNEXPECTED_INDEX,
                    pojo.getName());
        } else {
            return new IndexedVariable(pojo.getName(), Integer.parseInt(pojo.getIndex())); // TODO - check index
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    // index obligatoire
    public static IndexedVariable parseReturnValueToVariable(VariablePOJO pojo, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_RETURN_VALUE_VARIABLE_MISSING_NAME,
                    pojo.getName());
        } else if (pojo.getIndex() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_INDEX,
                    pojo.getName());
        } else {
            return new IndexedVariable(pojo.getName(), Integer.parseInt(pojo.getIndex())); // TODO - check index
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    public static Element parseValue(String tabColumn, String tabIndexSemanticLetter, ValuePOJO pojo, DecorationTableParsingResult res) {
        VariablePOJO var = pojo.getVariable();
        FunctionPOJO func = pojo.getFunction();
        if(func == null && var == null) {
            manageError(res, DecorationTableParsingErrorType.VARIABLE_VALUE_MISSING, "in " + tabColumn + "semantic letter " + tabIndexSemanticLetter);
        } else if(var != null) {
            return parseValueToVariable(tabColumn, tabIndexSemanticLetter, var, res);
        } else {
            return parseValueToFunction(func, res);
        }
        return new Variable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel());
    }

    public static Variable parseValueToVariable(String tabColumn, String tabIndexSemanticLetter, VariablePOJO pojo, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            manageError(res, DecorationTableParsingErrorType.VALUE_MISSING_NAME,
                    "in " + tabColumn + "at semantic letter " + tabIndexSemanticLetter);
        } else if (pojo.getIndex() == null) {
            return new Variable(pojo.getName());
        } else {
            return new IndexedVariable(pojo.getName(), Integer.parseInt(pojo.getIndex())); // TODO - check index
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    public static Function parseValueToFunction(FunctionPOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            List<Element> params = new ArrayList<>();
            pojo.getParameters().forEach((param) -> {
                if (param instanceof FunctionPOJO) {
                    params.add(DecorationTableMapper.parseValueToFunction((FunctionPOJO) param, res));
                } else if (param instanceof String) {
                    params.add(new Variable((String) param));
                } else if (param instanceof Integer) {
                    params.add(new IntegerVal((Integer) param));
                } else {
                    manageError(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, pojo.getName());
                }
            });
            return new Function(pojo.getName(), params);
        }
        return new Function(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_MISSING.getLabel(), new ArrayList<>());
    }

    // index facultatif
    public static Variable parseToVariable(String tabColumn, String semanticLetter, boolean canHaveIndex, VariablePOJO pojo, DecorationTableParsingResult res) {
        return parseToVariable(tabColumn, semanticLetter, canHaveIndex, pojo.getName(), Optional.of(pojo.getIndex()), res);
    }

    public static Variable parseToVariable(String tabColumn, String semanticLetter, boolean canHaveIndex, String pojoName, Optional<String> pojoIndex, DecorationTableParsingResult res) {
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


    public static void parseReturns(DecorationTablePOJO pojo, DecorationTableParsingResult res, DecorationTable decorationTable) {
        // Parsing returns
        for (int index = 0; index < pojo.getReturns().size(); index++) {
            ReturnsPOJO item = pojo.getReturns().get(index);
            String name = item.getName();
            ValuePOJO val = item.getValue();
            if (name == null) {
                manageError(res,
                        DecorationTableParsingErrorType.MISSING_RETURN_NAME, "" + index);
            } else if (item.getIndex() == null) {
                manageError(res,
                        DecorationTableParsingErrorType.MISSING_RETURN_INDEX, "" + index + ", " + name);
            } else if (val == null) {
                manageError(res,
                        DecorationTableParsingErrorType.MISSING_RETURN_VALUE, "" + index);
            } else if ((val.getFunction() == null) == (val.getVariable() == null)) {
                manageError(res,
                        DecorationTableParsingErrorType.BOTH_RETURNS_FUNCTION_AND_VARIABLE_IN_VALUE, "" + index);
            } else if (val.getFunction() != null) {
                decorationTable.addReturn(name, DecorationTableMapper.parseInitValueToFunction(val.getFunction(), res));
            } else if (val.getVariable() != null) {
                decorationTable.addReturn(name, DecorationTableMapper.parseReturnValueToVariable(val.getVariable(), res));
            }

        }
    }

    public static void parseRegisters(DecorationTablePOJO pojo, DecorationTableParsingResult res, DecorationTable decorationTable) {
        for (int index = 0; index < pojo.getRegisters().size(); index++) {
            RegistersPOJO item = pojo.getRegisters().get(index);
            String name = item.getName();
            ValuePOJO val = item.getValue();
            if (name == null) {
                manageError(res,
                        DecorationTableParsingErrorType.MISSING_REGISTER_NAME,
                        DecorationTableParsingErrorType.MISSING_REGISTER_NAME.getLabel() + index);
            } else if (val == null) {
                manageError(res,
                        DecorationTableParsingErrorType.MISSING_REGISTER_VALUE,
                        DecorationTableParsingErrorType.MISSING_REGISTER_VALUE.getLabel() + index);
            } else if ((val.getFunction() == null) == (val.getVariable() == null)) {
                manageError(res,
                        DecorationTableParsingErrorType.BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE,
                        DecorationTableParsingErrorType.BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE.getLabel() + index);
            } else if (val.getFunction() != null) {
                decorationTable.addRegister(name, DecorationTableMapper.parseInitValueToFunction(val.getFunction(), res));
            } else if (val.getVariable() != null) {
                decorationTable.addRegister(name, DecorationTableMapper.parseRegisterValueToVariable(val.getVariable(), res));
            }

        }
    }

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
                for (int guardIndex = 0; guardIndex < tabItem.getGuards().size(); guardIndex++) {

                    GuardPOJO guardPOJO = tabItem.getGuards().get(guardIndex);

                    String guardVarName = guardPOJO.getVariable();
                    String guardVarIndex = guardPOJO.getIndex();
                    Variable guardVar = parseToVariable(tabColumnGuard, semanticLetterLab, true, guardVarName, Optional.of(guardVarIndex), res);

                    Affectation aff = new Affectation(guardVar, parseValue(tabColumnGuard, semanticLetterLab, guardPOJO.getValue(), res));
                    inst.addGuard(guardVar.getName(), aff);

                }

                // Getting table line updates

                for (int updateIndex = 0; updateIndex < tabItem.getUpdates().size(); updateIndex++) {

                    UpdatePOJO updatePOJO = tabItem.getUpdates().get(updateIndex);

                    String updateVarName = updatePOJO.getVariable();
                    Variable guardVar = parseToVariable(tabColumnUpdate, semanticLetterLab, false, updateVarName, Optional.empty(), res);

                    Affectation aff = new Affectation(guardVar, parseValue(tabColumnUpdate, semanticLetterLab, updatePOJO.getValue(), res));
                    inst.addUpdate(guardVar.getName(), aff);

                }

                decorationTable.addInstruction(semanticLetter.get(), inst);

            }

        }

    }

}
