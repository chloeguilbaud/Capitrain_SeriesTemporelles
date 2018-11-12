package parser.decoration.table;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import parser.decoration.table.errors.DecorationTableParsingError;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.DecorationTablePOJO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DecorationTableConverter {

    public static DecorationTableParsingResult convert(File jsonFile) {

        DecorationTableParsingResult res = new DecorationTableParsingResult();
        ObjectMapper mapper = new ObjectMapper();
        try {
            DecorationTablePOJO pojo = mapper.readValue(jsonFile, DecorationTablePOJO.class);
            System.out.println(res);
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

       return res;

    }

    private static DecorationTableParsingResult process(DecorationTablePOJO pojo, DecorationTableParsingResult res) {
        return null;
    }


    /**
     * Enables parsing error management.
     * @param res The {@link DecorationTableParsingResult} parsing result object (modified)
     * @param err The {@link DecorationTableParsingErrorType} occurred error
     * @param msg The related error message
     */
    private static void manageError(DecorationTableParsingResult res, DecorationTableParsingErrorType err, String msg) {
        res.addParsingError(new DecorationTableParsingError(err, msg));
    }



}
