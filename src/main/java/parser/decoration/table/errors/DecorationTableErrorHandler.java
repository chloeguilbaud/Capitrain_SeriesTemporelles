package parser.decoration.table.errors;

import parser.decoration.table.process.DecorationTableParsingResult;

/**
 * Decoration Table parsing error handler
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public abstract class DecorationTableErrorHandler {

    /**
     * Enables parsing error management.
     * @param res The {@link DecorationTableParsingResult} parsing result object (modified)
     * @param err The {@link DecorationTableParsingErrorType} occurred error
     * @param msg The related error message
     */
    public static void handle(DecorationTableParsingResult res, DecorationTableParsingErrorType err, Object...msg) {
        res.addParsingError(new DecorationTableParsingError(err, String.format(err.getLabel(), msg)));
    }

}
