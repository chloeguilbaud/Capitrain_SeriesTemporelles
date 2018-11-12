package parser.decoration.table.errors;

import parser.common.ParsingError;

public class DecorationTableParsingError extends ParsingError<DecorationTableParsingErrorType> {

    public DecorationTableParsingError(DecorationTableParsingErrorType errorType, String errorMsg) {
        super(errorType, errorMsg);
    }

}
