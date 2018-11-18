package parser.decoration.table.process;

import parser.decoration.table.errors.DecorationTableParsingError;
import parser.decoration.table.errors.DecorationTableParsingErrorType;

public class DecorationTableUtils {

    /**
     * Enables parsing error management.
     * @param res The {@link DecorationTableParsingResult} parsing result object (modified)
     * @param err The {@link DecorationTableParsingErrorType} occurred error
     * @param msg The related error message
     */
    public static void manageError(DecorationTableParsingResult res, DecorationTableParsingErrorType err, Object...msg) {
        res.addParsingError(new DecorationTableParsingError(err, String.format(err.getLabel(), msg)));
    }

}
