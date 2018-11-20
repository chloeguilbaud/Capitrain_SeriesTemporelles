package parser.seed.transducer.errors;

import parser.common.ParsingError;

/**
 * Seed transducer parsing error representation containing error type ({@link SeedTransducerParsingErrorType})
 * and error message.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public abstract class SeedTransducerParsingError extends ParsingError<SeedTransducerParsingErrorType> {

    public SeedTransducerParsingError(SeedTransducerParsingErrorType errorType, String errorMsg) {
        super(errorType, errorMsg);
    }

}
