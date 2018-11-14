package parser.decoration.table.mapper;

import model.decoration.table.element.*;
import parser.decoration.table.process.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.FunctionPOJO;
import parser.decoration.table.model.ValuePOJO;
import parser.decoration.table.model.VariablePOJO;

import java.util.ArrayList;
import java.util.List;

import static parser.decoration.table.process.DecorationTableUtils.manageError;

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
            manageError(res, DecorationTableParsingErrorType.VARIABLE_VALUE_MISSING_NAME,
                    "in " + tabColumn + "at semantic letter " + tabIndexSemanticLetter);
        } else if (pojo.getIndex() == null) {
            return new Variable(pojo.getName());
        } else {
            Integer varIndex = ValueMapper.parseVariableIndex(pojo.getIndex(), tabIndexSemanticLetter, pojo.getName(), tabColumn, res);
            return new IndexedVariable(pojo.getName(), varIndex);
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    private static Function mapValueToFunction(FunctionPOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            Function function = new Function(pojo.getName());
            if(pojo.getParameters() != null) {
                pojo.getParameters().forEach((param) -> {
                    if (param instanceof FunctionPOJO) {
                        function.addParameter(ValueMapper.mapValueToFunction((FunctionPOJO) param, res));
                    } else if (param instanceof String) {
                        function.addParameter(new Variable((String) param));
                    } else if (param instanceof Integer) {
                        function.addParameter(new IntegerVal((Integer) param));
                    } else {
                        manageError(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, pojo.getName());
                    }
                });
            }
            return function;
        }
        return new Function(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_ERROR.getLabel(), new ArrayList<>());
    }

    /**
     * 0 si pas de i+ quelque chose
     * val positive ou négative sinon
     * @param indexedStr
     * @param tabColumn
     * @return
     */
    public static Integer parseVariableIndex(String indexedStr, String semanticLetter, String varName, String tabColumn, DecorationTableParsingResult res) {
        int tmp1;
        if (indexedStr.contains("+")) {
            tmp1 = indexedStr.indexOf("+");
            String tmp2 = indexedStr.substring(tmp1+1);
            Integer varIndex = Integer.parseInt(tmp2);
            return varIndex;
        } else if (indexedStr.contains("-")) {
            tmp1 = indexedStr.indexOf("-");
            String tmp2 = indexedStr.substring(tmp1+1);
            try {
                Integer varIndex = Integer.parseInt(tmp2);
                return -varIndex;
            } catch (NumberFormatException ex) {
                manageError(res, DecorationTableParsingErrorType.VARIABLE_INVALID_INDEX, varName + "for semantic letter " + semanticLetter + " in " + tabColumn);
                return Integer.MAX_VALUE;
            }
        } else {
            return 0;
        }


    }

}
