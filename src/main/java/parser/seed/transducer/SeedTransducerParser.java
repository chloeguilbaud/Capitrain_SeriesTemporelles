package parser.seed.transducer;

import model.seed.transducer.SeedTransducer;
import parser.seed.transducer.model.SeedTransducerParsingResult;
import parser.seed.transducer.process.SeedTransducerConverter;

import java.io.File;

/**
 * Utils class and entry point to the {@link SeedTransducer} parser extracting data from the given JSON file.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
class SeedTransducerParser {


    static SeedTransducerParsingResult parse(File jsonFile) {
        return SeedTransducerConverter.convert(jsonFile);
    }

}
