package parser.decoration.table.mapper;

import model.decoration.table.element.Element;
import model.decoration.table.element.Function;
import model.decoration.table.element.IntegerVal;
import model.decoration.table.element.Variable;
import parser.decoration.table.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.FunctionPOJO;
import parser.decoration.table.model.VariablePOJO;

import java.util.ArrayList;
import java.util.List;

import static parser.decoration.table.DecorationTableUtils.manageError;

public class DecorationTableMapper {

    public static Function parseToInitialisationFunction(FunctionPOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            manageError(res, DecorationTableParsingErrorType.FUNCTION_MISSING_NAME ,"name: " + null);
        } else {
            return new Function(pojo.getName(), new ArrayList<>());
        }
        return new Function(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_MISSING.getLabel(), new ArrayList<>());
    }

    public static Variable parseToInitialisationVariable(VariablePOJO pojo, DecorationTableParsingResult res) {
        if (pojo.getIndex() != null) {
            manageError(res, DecorationTableParsingErrorType.INITIALISATION_VARIABLE_UNEXPECTED_INDEX,
                    pojo.getName());
        } else {
            return new Variable(pojo.getName());
        }
        return new Variable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_MISSING.getLabel());
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


}
