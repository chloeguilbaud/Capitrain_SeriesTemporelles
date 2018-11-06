package parser.seed.transducer;

import parser.seed.transducer.model.SeedTransducerParsingResult;

import java.io.File;
import java.io.IOException;

public class SeedTransducerParser {


    public static SeedTransducerParsingResult parse(File jsonFile) throws IOException {
        return SeedTransducerConverter.convert(jsonFile);
    }

}
