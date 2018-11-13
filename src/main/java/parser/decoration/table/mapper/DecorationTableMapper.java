package parser.decoration.table.mapper;

import model.decoration.table.DecorationTable;
import model.decoration.table.element.*;
import parser.decoration.table.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.*;

import java.util.ArrayList;
import java.util.List;

import static parser.decoration.table.DecorationTableUtils.manageError;

public class DecorationTableMapper {

    public static Function parseInitValueToFunction(FunctionPOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            return new Function(pojo.getName(), new ArrayList<>());
        }
        return new Function(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_MISSING.getLabel(), new ArrayList<>());
    }

    public static Variable parseRegisterValueToVariable(VariablePOJO pojo, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME,
                    pojo.getName());
        } else if (pojo.getIndex() != null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_REGISTER_VARIABLE_UNEXPECTED_INDEX,
                    pojo.getName());
        } else {
            return new IndexedVariable(pojo.getName(), Integer.parseInt(pojo.getIndex()));
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    public static IndexedVariable parseReturnValueToVariable(VariablePOJO pojo, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_RETURN_VALUE_VARIABLE_MISSING_NAME,
                    pojo.getName());
        } else if (pojo.getIndex() == null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_INDEX,
                    pojo.getName());
        } else {
            return new IndexedVariable(pojo.getName(), Integer.parseInt(pojo.getIndex()));
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    public static Function parseToFunction(FunctionPOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            List<Element> params = new ArrayList<>();
            pojo.getParameters().forEach((param) -> {
                if (param instanceof FunctionPOJO) {
                    params.add(DecorationTableMapper.parseToFunction((FunctionPOJO) param, res));
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

}
