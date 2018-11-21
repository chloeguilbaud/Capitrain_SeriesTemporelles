package parser.seed.transducer.errors;

import parser.common.ParsingError;

/**
 * Seed transducer parsing error representation containing error type ({@link SeedTransducerParsingErrorType})
 * and error message.
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class SeedTransducerParsingError extends ParsingError<SeedTransducerParsingErrorType> {

    public SeedTransducerParsingError(SeedTransducerParsingErrorType errorType, String errorMsg) {
        super(errorType, errorMsg);
    }

}
