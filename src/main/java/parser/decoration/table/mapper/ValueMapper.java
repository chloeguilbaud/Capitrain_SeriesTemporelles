package parser.decoration.table.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.decoration.table.element.*;
import parser.decoration.table.process.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.FunctionPOJO;
import parser.decoration.table.model.ValuePOJO;
import parser.decoration.table.model.VariablePOJO;

import java.util.ArrayList;
import java.util.LinkedHashMap;

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
            return mapValueToFunction(func, tabIndexSemanticLetter, tabColumn, res);
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

    public static Function mapValueToFunction(FunctionPOJO pojo, String semanticLetter, String tabColumn, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            String invalidFunctionParamTypeErrMsg = pojo.getName() + " in semantic letter " + semanticLetter + " in " + tabColumn;
            Function function = new Function(pojo.getName());
            if(pojo.getParameters() != null) {
                try {
                    pojo.getParameters().forEach((param) -> {
                        if (param instanceof String) {
                            function.addParameter(new Variable((String) param));
                        } else if (param instanceof Integer) {
                            function.addParameter(new IntegerVal((Integer) param));
                        } else if (((LinkedHashMap) param).values().size() != 1) {
                            manageError(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                        } else if (((LinkedHashMap) param).get("function") instanceof LinkedHashMap) {
                            ObjectMapper mapper = new ObjectMapper();
                            LinkedHashMap function2 = (LinkedHashMap) ((LinkedHashMap) param).get("function");
                            try {
                                FunctionPOJO function2Pojo = mapper.convertValue(function2, FunctionPOJO.class);
                                function.addParameter(ValueMapper.mapValueToFunction(function2Pojo, semanticLetter, tabColumn, res));
                            } catch (IllegalArgumentException ex ) {
                                manageError(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                            }
                        } else if (((LinkedHashMap) param).get("variable") instanceof LinkedHashMap) {
                            ObjectMapper mapper = new ObjectMapper();
                            LinkedHashMap variable = (LinkedHashMap) ((LinkedHashMap) param).get("variable");
                            try {
                                VariablePOJO var2 = mapper.convertValue(variable, VariablePOJO.class);
                                function.addParameter(ValueMapper.mapValueToVariable(tabColumn, semanticLetter, var2, res));
                            } catch (IllegalArgumentException ex ) {
                                manageError(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                            }
                        } else {
                            manageError(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                        }
                    });
                } catch (ClassCastException ex) {
                    manageError(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                }
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
