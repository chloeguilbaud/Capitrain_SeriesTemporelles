package parser.decoration.table;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.decoration.table.DecorationTable;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.mapper.DecorationTableMapper;
import parser.decoration.table.model.DecorationTablePOJO;
import parser.decoration.table.model.RegistersPOJO;
import parser.decoration.table.model.ReturnsPOJO;
import parser.decoration.table.model.ValuePOJO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static parser.decoration.table.DecorationTableUtils.manageError;

public class DecorationTableConverter {

    public static DecorationTableParsingResult convert(File jsonFile) throws IOException {

        DecorationTableParsingResult res = new DecorationTableParsingResult();
        ObjectMapper mapper = new ObjectMapper();
        try {
            DecorationTablePOJO pojo = mapper.readValue(jsonFile, DecorationTablePOJO.class);
            System.out.println(pojo);
            return process(pojo, res);
        } catch (UnrecognizedPropertyException ex) {
            manageError(res,
                    DecorationTableParsingErrorType.UNRECOGNIZED_PROPERTY,
                    ex.getMessage());
        } catch (JsonMappingException ex) {
            manageError(res,
                    DecorationTableParsingErrorType.JSON_MAPPING_EXCEPTION,
                    ex.getMessage());
        } catch (FileNotFoundException ex) {
            manageError(res,
                    DecorationTableParsingErrorType.FILE_NOT_FOUND,
                    ex.getMessage());
        } catch (IOException ex) {
            manageError(res,
                    DecorationTableParsingErrorType.UNKNOWN_ERROR,
                    ex.getMessage());
        }

        System.out.println(res);
        return res;

    }

    private static DecorationTableParsingResult process(DecorationTablePOJO pojo, DecorationTableParsingResult res) {

        System.out.println(pojo);
        DecorationTable decorationTable = new DecorationTable(pojo.getName());

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
                decorationTable.addRegister(name, DecorationTableMapper.parseToInitialisationFunction(val.getFunction(), res));
            } else if (val.getVariable() != null) {
                decorationTable.addRegister(name, DecorationTableMapper.parseToInitialisationVariable(val.getVariable(), res));
            }

        }

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
                        DecorationTableParsingErrorType.BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE,
                        DecorationTableParsingErrorType.BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE.getLabel() + index);
            } else if (val.getFunction() != null) {
                decorationTable.addReturn(name, DecorationTableMapper.parseToInitialisationFunction(val.getFunction(), res));
            } else if (val.getVariable() != null) {
                decorationTable.addReturn(name, DecorationTableMapper.parseToInitialisationVariable(val.getVariable(), res));
            }

        }

        return res;
    }



}
