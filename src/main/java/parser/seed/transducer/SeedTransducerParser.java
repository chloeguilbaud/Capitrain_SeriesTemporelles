package parser.seed.transducer;

import parser.seed.transducer.model.SeedTransducerParsingResult;

import java.io.File;

public class SeedTransducerParser {


    public static SeedTransducerParsingResult parse(File jsonFile) {
        return SeedTransducerConverter.convert(jsonFile);
    }

}
