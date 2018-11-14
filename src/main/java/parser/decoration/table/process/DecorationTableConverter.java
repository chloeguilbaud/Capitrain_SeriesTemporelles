package parser.decoration.table.process;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.decoration.table.DecorationTable;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.mapper.DecorationTableContentMapper;
import parser.decoration.table.model.DecorationTablePOJO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static parser.decoration.table.process.DecorationTableUtils.manageError;
import static parser.decoration.table.mapper.DecorationTableContentMapper.mapDecorationTableContent;
import static parser.decoration.table.mapper.InitialisationMapper.mapRegisters;
import static parser.decoration.table.mapper.InitialisationMapper.mapReturns;

public class DecorationTableConverter {

    public static DecorationTableParsingResult convert(File jsonFile) {

        DecorationTableParsingResult res = new DecorationTableParsingResult();
        ObjectMapper mapper = new ObjectMapper();
        try {
            DecorationTablePOJO pojo = mapper.readValue(jsonFile, DecorationTablePOJO.class);
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

        DecorationTable decorationTable = new DecorationTable(pojo.getName());

        // Parsing initialisation fields
        mapRegisters(pojo, DecorationTableContentMapper.tabColumnRegister, res, decorationTable);
        mapReturns(pojo, DecorationTableContentMapper.tabColumnReturn, res, decorationTable);

        // Parsing table
        mapDecorationTableContent(pojo, res, decorationTable);

        // Saving result
        res.setParsingResult(decorationTable);

        return res;
    }


}
