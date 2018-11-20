package parser.seed.transducer.errors;

import parser.seed.transducer.process.SeedTransducerParsingResult;

/**
 * Seed Transducer parsing error handler
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public abstract class SeedTransducerErrorHandler {

    /**
     * Enables parsing error management.
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @param err The {@link SeedTransducerParsingErrorType} occurred error
     * @param msg The related error message
     */
    public static void handle(SeedTransducerParsingResult res, SeedTransducerParsingErrorType err, String...msg) {
        res.addParsingError(new SeedTransducerParsingError(err, String.format(err.getLabel(), msg)));
    }

}
