package parser.decoration.table.mapper;

import model.decoration.table.DecorationTable;
import model.decoration.table.element.Function;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.Variable;
import parser.decoration.table.process.DecorationTableParsingResult;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.*;

import java.util.ArrayList;

import static parser.decoration.table.errors.DecorationTableErrorHandler.handle;

/**
 * Decoration table mapper enabling pojo transformation to model for initialisation elements like register and return statements.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class InitialisationMapper {

    /**
     * Maps return elements from the given {@link DecorationTablePOJO} object and adds them to {@link DecorationTable} object.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param pojo The {@link DecorationTablePOJO} to map from
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @param decorationTable The {@link DecorationTable} parsed result object (modified by process)
     */
    public static void mapReturns(DecorationTablePOJO pojo, DecorationTableParsingResult res, DecorationTable decorationTable) {
        // Parsing returns
        for (int index = 0; index < pojo.getReturns().size(); index++) {
            ReturnsPOJO item = pojo.getReturns().get(index);
            String name = item.getName();
            ValuePOJO val = item.getValue();
            if (name == null) {
                handle(res,
                        DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_NAME, (index + 1));
            } else if (item.getIndex() == null) {
                handle(res,
                        DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_INDEX, (index + 1), name);
            } else if (val == null) {
                handle(res,
                        DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_VALUE, (index + 1));
            } else if ((val.getFunction() == null) == (val.getVariable() == null)) {
                handle(res,
                        DecorationTableParsingErrorType.BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE, (index + 1));
            } else if (val.getFunction() != null) {
                decorationTable.addReturn(name, parseInitValueToFunction(val.getFunction(), DecorationTableContentMapper.tabColumnReturn, index, res));
            } else if (val.getVariable() != null) {
                decorationTable.addReturn(name, mapReturnValueToVariable(val.getVariable(), index, res));
            }

        }
    }

    /**
     * Maps register elements from the given {@link DecorationTablePOJO} object and adds them to {@link DecorationTable} object.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param pojo The {@link DecorationTablePOJO} to map from
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @param decorationTable The {@link DecorationTable} parsed result object (modified by process)
     */
    public static void mapRegisters(DecorationTablePOJO pojo, DecorationTableParsingResult res, DecorationTable decorationTable) {
        for (int index = 0; index < pojo.getRegisters().size(); index++) {
            RegistersPOJO item = pojo.getRegisters().get(index);
            String name = item.getName();
            ValuePOJO val = item.getValue();
            if (name == null) {
                handle(res,
                        DecorationTableParsingErrorType.MISSING_REGISTER_NAME,
                        (index + 1));
            } else if (val == null) {
                handle(res,
                        DecorationTableParsingErrorType.MISSING_REGISTER_VALUE,
                        (index + 1));
            } else if ((val.getFunction() == null) == (val.getVariable() == null)) {
                handle(res,
                        DecorationTableParsingErrorType.BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE, (index + 1));
            } else if (val.getFunction() != null) {
                decorationTable.addRegister(name, parseInitValueToFunction(val.getFunction(), DecorationTableContentMapper.tabColumnRegister, index, res));
            } else if (val.getVariable() != null) {
                decorationTable.addRegister(name, mapRegisterValueToVariable(val.getVariable(), index, res));
            }

        }
    }

    /**
     * Maps functions used in initialisation to {@link Function} model.
     * Errors are added to {@link DecorationTableParsingResult}.
     * @param pojo The {@link FunctionPOJO} to map from
     * @param tabColumn The concerned column in the decoration table (Register or Return).
     * @param tabIndex The table line corresponding to this instruction
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Function}
     */
    private static Function parseInitValueToFunction(FunctionPOJO pojo, String tabColumn, int tabIndex, DecorationTableParsingResult res) {
        if (pojo.getName() == null ){
            handle(res, DecorationTableParsingErrorType.INITIALISATION_VALUE_FUNCTION_MISSING_NAME, tabColumn, (tabIndex + 1));
        } else if (pojo.getParameters() != null) {
            handle(res, DecorationTableParsingErrorType.FUNCTION_UNEXPECTED_PARAMETER_IN_INITIALISATION, pojo.getName());
        } else {
            return new Function(pojo.getName(), new ArrayList<>());
        }
        return new Function(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_ERROR.getLabel(), new ArrayList<>());
    }

    /**
     * Maps register {@link VariablePOJO} to model {@link Variable}
     * Errors are added to {@link DecorationTableParsingResult}.
     * Indexed variables are forbidden.
     * @param pojo The {@link VariablePOJO} to map from
     * @param registerIndex The register index corresponding
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Variable}
     */
    private static Variable mapRegisterValueToVariable(VariablePOJO pojo, int registerIndex, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            handle(res, DecorationTableParsingErrorType.INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME,
                    (registerIndex + 1));
        } else if (pojo.getIndex() != null) {
            handle(res, DecorationTableParsingErrorType.INITIALISATION_REGISTER_VALUE_VARIABLE_UNEXPECTED_INDEX,
                    pojo.getName());
        } else {
            return new IndexedVariable(pojo.getName(),
                    ValueMapper.parseVariableIndex(pojo.getIndex(),
                            DecorationTableContentMapper.tabColumnRegister, pojo.getName(), DecorationTableContentMapper.tabColumnRegister, res));
        }
        return new IndexedVariable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel(), Integer.MAX_VALUE);
    }

    /**
     * Maps return {@link VariablePOJO} to model {@link Variable}
     * Errors are added to {@link DecorationTableParsingResult}.
     * Indexed variables are needed.
     * @param pojo The {@link VariablePOJO} to map from
     * @param index The return index corresponding
     * @param res The {@link DecorationTableParsingResult} parsing result (modified by process)
     * @return The model mapped {@link Variable}
     */
    private static Variable mapReturnValueToVariable(VariablePOJO pojo, int index, DecorationTableParsingResult res) {
        if(pojo.getName() == null) {
            handle(res, DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_NAME,
                    (index+1));
        } else {
            return new Variable(pojo.getName());
        }
        return new Variable(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR.getLabel());
    }

}
