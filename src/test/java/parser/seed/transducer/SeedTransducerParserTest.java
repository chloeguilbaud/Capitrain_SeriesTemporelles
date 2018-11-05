package parser.seed.transducer;

import conf.TestConfiguration;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SeedTransducerParserTest {

    /**
     * com.fasterxml.jackson.databind.JsonMappingException: No content to map due to end-of-input
     *java.io.FileNotFoundException: src\resources\seedTransducerExample.json (Le chemin d’accès spécifié est introuvable)
     * com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of parser.seed.transducer.model.SeedTransducerPOJO: no suitable constructor found, can not deserialize from Object value (missing default constructor or creator, or perhaps need to add/enable type information?)
     *  at [Source: src\test\resources\seedTransducerExample.json; line: 2, column: 3]
     */
    @Test
    public void parseTest() throws IOException {
        SeedTransducerParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample.json"));
    }

}
