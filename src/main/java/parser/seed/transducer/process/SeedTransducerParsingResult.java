package parser.seed.transducer.process;

import parser.common.ParsingResult;
import parser.seed.transducer.errors.SeedTransducerParsingError;
import model.seed.transducer.SeedTransducer;

import java.util.Optional;

/**
 * Seed transducer parsing result containing the {@link SeedTransducer} extracted from the given JSON file.
 * If general error occur during the parsing, the result will contain it. If further error occur they will also
 * be listed. In that cas, the {@link Optional} {@link SeedTransducer} should be empty.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class SeedTransducerParsingResult extends ParsingResult<SeedTransducer, SeedTransducerParsingError> {

    public SeedTransducerParsingResult() {
        super();
    }

}
