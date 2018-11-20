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
import java.util.Optional;

import static parser.decoration.table.errors.DecorationTableErrorHandler.handle;

/**
 * Decoration table mapper specialized in value processing.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
abstract class ValueMapper {

    /**
     * Mapper from {@link ValuePOJO} to model {@link Element}.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param tabColumn The concerned column in the decoration table (Update or Guard).
     * @param tabIndexSemanticLetter The semantic letter corresponding to this instruction
     * @param pojo The pojo to map from
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Element}
     */
    static Element mapValue(String tabColumn, String tabIndexSemanticLetter, ValuePOJO pojo, DecorationTableParsingResult res) {
        if (pojo == null) {
            handle(res, DecorationTableParsingErrorType.VARIABLE_VALUE_MISSING, tabColumn, tabIndexSemanticLetter);
        } else {
            VariablePOJO var = pojo.getVariable();
            FunctionPOJO func = pojo.getFunction();
            if (func == null && var == null) {
                handle(res, DecorationTableParsingErrorType.VARIABLE_VALUE_MISSING, tabColumn, tabIndexSemanticLetter);
            } else if (func != null && var != null) {
                handle(res, DecorationTableParsingErrorType.BOTH_FUNCTION_AND_VARIABLE_IN_VALUE, tabColumn, tabIndexSemanticLetter);
            } else if (var != null) {
                return mapValueToVariable(tabColumn, tabIndexSemanticLetter, var, res);
            } else {
                return mapValueToFunction(func, tabIndexSemanticLetter, tabColumn, res);
            }
        }
        return new Variable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel());
    }

    /**
     * Mapper from {@link VariablePOJO} to model {@link Variable}.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param tabColumn The concerned column in the decoration table (Update or Guard).
     * @param tabIndexSemanticLetter The semantic letter corresponding to this instruction
     * @param pojo The pojo to map from
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Variable}
     */
    private static Variable mapValueToVariable(String tabColumn, String tabIndexSemanticLetter, VariablePOJO pojo, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            handle(res, DecorationTableParsingErrorType.VARIABLE_VALUE_MISSING_NAME,
                    tabColumn, tabIndexSemanticLetter);
        } else if (pojo.getIndex() == null) {
            return new Variable(pojo.getName());
        } else {
            Integer varIndex = ValueMapper.parseVariableIndex(pojo.getIndex(), tabIndexSemanticLetter, pojo.getName(), tabColumn, res);
            return new IndexedVariable(pojo.getName(), varIndex);
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    /**
     * Mapper from {@link FunctionPOJO} to model {@link Function}.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param pojo The pojo to map from
     * @param semanticLetter The semantic letter corresponding to this instruction
     * @param tabColumn The concerned column in the decoration table (Update or Guard).
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Function}
     */
    private static Function mapValueToFunction(FunctionPOJO pojo, String semanticLetter, String tabColumn, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            handle(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,semanticLetter, tabColumn);
        } else {
            Object[] invalidFunctionParamTypeErrMsg = {pojo.getName(), semanticLetter, tabColumn};
            Function function = new Function(pojo.getName());
            if(pojo.getParameters() != null) {
                try {
                    // Parsing parameters
                    pojo.getParameters().forEach((param) -> {
                        if (param instanceof String) {
                            Optional<Operation> op = parseIndexedParameter((String) param, tabColumn, semanticLetter, res);
                            if(op.isPresent()) {
                                function.addParameter(op.get());
                            } else {
                                function.addParameter(new Variable((String) param));
                            }
                        } else if (param instanceof Integer) {
                            function.addParameter(new IntegerVal((Integer) param));
                        } else if (((LinkedHashMap) param).values().size() != 1) {
                            handle(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                        } else if (((LinkedHashMap) param).get("function") instanceof LinkedHashMap) {
                            ObjectMapper mapper = new ObjectMapper();
                            LinkedHashMap function2 = (LinkedHashMap) ((LinkedHashMap) param).get("function");
                            try {
                                FunctionPOJO function2Pojo = mapper.convertValue(function2, FunctionPOJO.class);
                                function.addParameter(ValueMapper.mapValueToFunction(function2Pojo, semanticLetter, tabColumn, res));
                            } catch (IllegalArgumentException ex ) {
                                handle(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                            }
                        } else if (((LinkedHashMap) param).get("variable") instanceof LinkedHashMap) {
                            ObjectMapper mapper = new ObjectMapper();
                            LinkedHashMap variable = (LinkedHashMap) ((LinkedHashMap) param).get("variable");
                            try {
                                VariablePOJO var2 = mapper.convertValue(variable, VariablePOJO.class);
                                function.addParameter(ValueMapper.mapValueToVariable(tabColumn, semanticLetter, var2, res));
                            } catch (IllegalArgumentException ex ) {
                                handle(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                            }
                        } else {
                            handle(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                        }
                    });
                } catch (ClassCastException ex) {
                    handle(res, DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE, invalidFunctionParamTypeErrMsg);
                }
            }
            return function;
        }
        return new Function(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_ERROR.getLabel(), new ArrayList<>());
    }

    /**
     * Enables parsing for indexed parameters (ex: "i+1").
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param param The parameter to parse
     * @param functionName The function name having the given parameter
     * @param semanticLetter The semantic letter corresponding to this instruction
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Operation} corresponding to the given parameter. If an error occurs it will be empty
     */
    private static Optional<Operation> parseIndexedParameter(String param, String functionName, String semanticLetter, DecorationTableParsingResult res) {
        Optional<Operation> op = Optional.empty();
        if (param.contains("+")) {
            op = parseToOperation("+", param, functionName, semanticLetter, res);
        } else if (param.contains("-")) {
            op = parseToOperation("-", param, functionName, semanticLetter, res);
        } else if (param.contains("/")) {
            op = parseToOperation("/", param, functionName, semanticLetter, res);
        } else if (param.contains("x")) {
            op = parseToOperation("x", param, functionName, semanticLetter, res);
        } else {
            op = Optional.empty();
        }
        return op;
    }

    /**
     * Maps indexed parameter to model {@link Operation}
     * @param operationStr The string operation representation (+, -, / or x)
     * @param param The parameter to parse
     * @param functionName The function name having the given parameter
     * @param semanticLetter The semantic letter corresponding to this instruction
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Operation} corresponding to the given parameter. If an error occurs it will be empty
     */
    private static Optional<Operation> parseToOperation(String operationStr, String param, String functionName, String semanticLetter, DecorationTableParsingResult res) {
        int tmp1 = param.indexOf(operationStr);
        String leftVal = param.substring(0, tmp1);
        String rightVal = param.substring(tmp1+1);
        Optional<Element> opLeftElem = parseOperationElement(leftVal, param, functionName, semanticLetter, res);
        Optional<Element> opRightElem = parseOperationElement(rightVal, param, functionName, semanticLetter, res);
        return ((opLeftElem.isPresent() && opRightElem.isPresent()))?toOperation(operationStr, opLeftElem, opRightElem):Optional.empty();
    }

    /**
     * Transforming to model {@link Operation}
     * @param operationStr The string operation representation
     * @param opLeftElem The left element of the operation
     * @param opRightElem The right element of the operation
     * @return The model mapped {@link Operation} corresponding to the given parameter. If an error occurs it will be empty.
     */
    private static Optional<Operation> toOperation(String operationStr, Optional<Element> opLeftElem, Optional<Element> opRightElem) {
        Optional<Operation> elem = Optional.empty();
        if (opLeftElem.isPresent() && opRightElem.isPresent()) {
            switch (operationStr) {
                case "+":
                    elem = Optional.of(new Sum(opLeftElem.get(), opRightElem.get()));
                    break;
                case "-":
                    elem = Optional.of(new Substraction(opLeftElem.get(), opRightElem.get()));
                    break;
                case "/":
                    elem = Optional.of(new Division(opLeftElem.get(), opRightElem.get()));
                    break;
                case "x":
                    elem = Optional.of(new Product(opLeftElem.get(), opRightElem.get()));
                    break;
                default:
                    elem = Optional.empty();
            }
        }
        return elem;
    }

    /**
     * Parse to model {@link Element}
     * @param elem The string representation of left or right operation element.
     * @param param The string representation of the indexed function parameter
     * @param functionName The function name having the given parameter
     * @param semanticLetter The semantic letter corresponding to this instruction
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Element} corresponding to the given parameter. If an error occurs it will be empty
     */
    private static Optional<Element> parseOperationElement(String elem, String param, String functionName, String semanticLetter, DecorationTableParsingResult res) {
        try {
            if(elem.isEmpty()) {
                handle(res, DecorationTableParsingErrorType.FUNCTION_PARAMETER_MISSING_VALUE_IN_OPERATION, functionName, semanticLetter, param);
                return Optional.empty();
            }
            return Optional.of(new IntegerVal(Integer.parseInt(elem)));
        } catch (NumberFormatException ex ) {
            return Optional.of(new Variable(elem));
        }

    }

    /**
     * Gets the index of the indexed function parameter.
     * 0 if the right side of the operation is empty.
     * Positive and negative indexes are possible.
     * @param indexedStr The string index representation of the indexed function parameter
     * @param semanticLetter The semantic letter corresponding to this instruction
     * @param varName The variable name concerned by this indexed function parameter
     * @param tabColumn The concerned column in the decoration table (Register, Return, Update or Guard).
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The indexed function parameter value
     */
    public static Integer parseVariableIndex(String indexedStr, String semanticLetter, String varName, String tabColumn, DecorationTableParsingResult res) {
        int tmp1;
        if (indexedStr.contains("+")) {
            tmp1 = indexedStr.indexOf("+");
            String tmp2 = indexedStr.substring(tmp1+1);
            try {
                return Integer.parseInt(tmp2);
            } catch (NumberFormatException ex) {
                handle(res, DecorationTableParsingErrorType.VARIABLE_INVALID_INDEX, varName, semanticLetter, tabColumn);
                return Integer.MAX_VALUE;
            }
        } else if (indexedStr.contains("-")) {
            tmp1 = indexedStr.indexOf("-");
            String tmp2 = indexedStr.substring(tmp1+1);
            try {
                Integer varIndex = Integer.parseInt(tmp2);
                return -varIndex;
            } catch (NumberFormatException ex) {
                handle(res, DecorationTableParsingErrorType.VARIABLE_INVALID_INDEX, varName, semanticLetter, tabColumn);
                return Integer.MAX_VALUE;
            }
        } else {
            return 0;
        }


    }

}
