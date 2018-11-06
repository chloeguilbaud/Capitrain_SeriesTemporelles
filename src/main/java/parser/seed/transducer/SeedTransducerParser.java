package parser.seed.transducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.seed.transducer.SeedTransducer;
import parser.seed.transducer.model.SeedTransducerPOJO;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SeedTransducerParser {


    public static SeedTransducerParsingResult parse(File jsonFile) throws IOException {
        return SeedTransducerConverter.convert(jsonFile);
    }

}
