package parser.decoration.table;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.decoration.table.DecorationTable;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.DecorationTablePOJO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static parser.decoration.table.DecorationTableUtils.manageError;
import static parser.decoration.table.mapper.DecorationTableMapper.parseRegisters;
import static parser.decoration.table.mapper.DecorationTableMapper.parseReturns;

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

        // Parsing initialisation fields
        parseRegisters(pojo, res, decorationTable);
        parseReturns(pojo, res, decorationTable);


        return res;
    }


}
