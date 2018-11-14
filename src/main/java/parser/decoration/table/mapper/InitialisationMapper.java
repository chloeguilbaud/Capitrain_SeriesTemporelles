package parser.decoration.table.mapper;

import model.decoration.table.DecorationTable;
import model.decoration.table.element.Function;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.Variable;
import parser.decoration.table.process.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.*;

import java.util.ArrayList;

import static parser.decoration.table.process.DecorationTableUtils.manageError;

public class InitialisationMapper {

    public static void mapReturns(DecorationTablePOJO pojo, String tabIndexSemanticLetter, DecorationTableParsingResult res, DecorationTable decorationTable) {
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
                        DecorationTableParsingErrorType.BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE, "" + index);
            } else if (val.getFunction() != null) {
                decorationTable.addReturn(name, parseInitValueToFunction(val.getFunction(), res));
            } else if (val.getVariable() != null) {
                decorationTable.addReturn(name, mapReturnValueToVariable(val.getVariable(), tabIndexSemanticLetter, DecorationTableContentMapper.tabColumnReturn, res));
            }

        }
    }

    public static void mapRegisters(DecorationTablePOJO pojo, String tabIndexSemanticLetter, DecorationTableParsingResult res, DecorationTable decorationTable) {
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
                decorationTable.addRegister(name, parseInitValueToFunction(val.getFunction(), res));
            } else if (val.getVariable() != null) {
                decorationTable.addRegister(name, mapRegisterValueToVariable(val.getVariable(), tabIndexSemanticLetter, DecorationTableContentMapper.tabColumnRegister, res));
            }

        }
    }

    private static Function parseInitValueToFunction(FunctionPOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            return new Function(pojo.getName(), new ArrayList<>());
        }
        return new Function(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_ERROR.getLabel(), new ArrayList<>());
    }

    // index interdit
    private static Variable mapRegisterValueToVariable(VariablePOJO pojo, String tabIndexSemanticLetter, String tabColumn, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME,
                    pojo.getName());
        } else if (pojo.getIndex() != null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_REGISTER_VARIABLE_UNEXPECTED_INDEX,
                    pojo.getName());
        } else {
            return new IndexedVariable(pojo.getName(), ValueMapper.parseVariableIndex(pojo.getIndex(), tabIndexSemanticLetter, pojo.getName(), tabColumn, res));
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    // index obligatoire
    private static IndexedVariable mapReturnValueToVariable(VariablePOJO pojo, String tabIndexSemanticLetter, String tabColumn, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_RETURN_VALUE_VARIABLE_MISSING_NAME,
                    pojo.getName());
        } else if (pojo.getIndex() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_INDEX,
                    pojo.getName());
        } else {
            return new IndexedVariable(pojo.getName(), ValueMapper.parseVariableIndex(pojo.getIndex(), tabIndexSemanticLetter, pojo.getName(), tabColumn, res));
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

}
