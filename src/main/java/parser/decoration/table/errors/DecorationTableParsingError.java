package parser.decoration.table.errors;

import parser.common.ParsingError;

/**
 * Decoration table parsing error representation containing error type ({@link DecorationTableParsingErrorType})
 * and error message.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class DecorationTableParsingError extends ParsingError<DecorationTableParsingErrorType> {

    public DecorationTableParsingError(DecorationTableParsingErrorType errorType, String errorMsg) {
        super(errorType, errorMsg);
    }

}
