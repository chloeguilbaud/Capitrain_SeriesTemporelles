package parser.seed.transducer.process;

import parser.seed.transducer.errors.SeedTransducerParsingError;
import parser.seed.transducer.errors.SeedTransducerParsingErrorType;

/**
 * Seed Transducer functional util class
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class SeedTransducerUtils {

    /**
     * Enables parsing error management.
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @param err The {@link SeedTransducerParsingErrorType} occurred error
     * @param msg The related error message
     */
    public static void manageError(SeedTransducerParsingResult res, SeedTransducerParsingErrorType err, String...msg) {
        res.addParsingError(new SeedTransducerParsingError(err, String.format(err.getLabel(), msg)));
    }

}
