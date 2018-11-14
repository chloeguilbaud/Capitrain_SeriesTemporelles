package parser.decoration.table.mapper;

import model.decoration.table.element.*;
import parser.decoration.table.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.FunctionPOJO;
import parser.decoration.table.model.ValuePOJO;
import parser.decoration.table.model.VariablePOJO;

import java.util.ArrayList;
import java.util.List;

import static parser.decoration.table.DecorationTableUtils.manageError;

class ValueMapper {


    static Element mapValue(String tabColumn, String tabIndexSemanticLetter, ValuePOJO pojo, DecorationTableParsingResult res) {
        VariablePOJO var = pojo.getVariable();
        FunctionPOJO func = pojo.getFunction();
        if(func == null && var == null) {
            manageError(res, DecorationTableParsingErrorType.VARIABLE_VALUE_MISSING, "in " + tabColumn + "semantic letter " + tabIndexSemanticLetter);
        } else if(var != null) {
            return mapValueToVariable(tabColumn, tabIndexSemanticLetter, var, res);
        } else {
            return mapValueToFunction(func, res);
        }
        return new Variable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel());
    }

    private static Variable mapValueToVariable(String tabColumn, String tabIndexSemanticLetter, VariablePOJO pojo, DecorationTableParsingResult res) {
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

    private static Function mapValueToFunction(FunctionPOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            List<Element> params = new ArrayList<>();
            pojo.getParameters().forEach((param) -> {
                if (param instanceof FunctionPOJO) {
                    params.add(ValueMapper.mapValueToFunction((FunctionPOJO) param, res));
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

}
