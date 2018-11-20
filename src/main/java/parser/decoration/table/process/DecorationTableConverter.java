package parser.decoration.table.process;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.decoration.table.DecorationTable;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.model.DecorationTablePOJO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import static parser.decoration.table.errors.DecorationTableErrorHandler.handle;
import static parser.decoration.table.mapper.DecorationTableContentMapper.mapDecorationTableContent;
import static parser.decoration.table.mapper.InitialisationMapper.mapRegisters;
import static parser.decoration.table.mapper.InitialisationMapper.mapReturns;

/**
 * Enables Decoration table's transformation from JSON to POJO.
 * It is then transformed into a {@link DecorationTableParsingResult} containing the {@link DecorationTable}
 * or errors.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class DecorationTableConverter {

    /**
     * Transforms decoration table JSON file into POJO before creating a {@link DecorationTableParsingResult}.
     * If a parsing error occurs the result will not contain ant {@link DecorationTable}.
     * Errors are added to the result.
     * If the error is not a proper parsing error, processing errors can be notified.
     * @param jsonFile - path to the seed transducer JSON file representation
     * @return {@link DecorationTableParsingResult} containing the {@link DecorationTable} result or an empty {@link Optional}
     *         object and errors if the parsing did't go through
     */
    public static DecorationTableParsingResult convert(File jsonFile) {

        DecorationTableParsingResult res = new DecorationTableParsingResult();
        ObjectMapper mapper = new ObjectMapper();
        try {
            DecorationTablePOJO pojo = mapper.readValue(jsonFile, DecorationTablePOJO.class);
            return process(pojo, res);
        } catch (UnrecognizedPropertyException ex) {
            handle(res,
                    DecorationTableParsingErrorType.UNRECOGNIZED_PROPERTY,
                    ex.getMessage());
        } catch (JsonMappingException ex) {
            handle(res,
                    DecorationTableParsingErrorType.JSON_MAPPING_EXCEPTION,
                    ex.getMessage());
        } catch (FileNotFoundException ex) {
            handle(res,
                    DecorationTableParsingErrorType.FILE_NOT_FOUND,
                    ex.getMessage());
        } catch (IOException ex) {
            handle(res,
                    DecorationTableParsingErrorType.UNKNOWN_ERROR,
                    ex.getMessage());
        }

        return res;

    }

    /**
     * Process the parsing and converting.
     * @param pojo The mapped {@link DecorationTablePOJO} related to the Seed Transducer JSON file.
     * @param res The {@link DecorationTableParsingResult} parsing result
     * @return {@link DecorationTableParsingResult} containing the {@link DecorationTable} result or an empty {@link Optional}
     *      object and errors if the parsing did't go through
     */
    private static DecorationTableParsingResult process(DecorationTablePOJO pojo, DecorationTableParsingResult res) {

        DecorationTable decorationTable = new DecorationTable(pojo.getName());

        // Parsing initialisation fields
        mapRegisters(pojo, res, decorationTable);
        mapReturns(pojo, res, decorationTable);

        // Parsing table
        mapDecorationTableContent(pojo, res, decorationTable);

        // If their is some errors, then the seed transducer Optional object is set to empty
        if (res.hasErrors()) {
            res.removeParsingResult();
        } else {
            res.setResult(decorationTable);;
        }

        return res;
    }


}
