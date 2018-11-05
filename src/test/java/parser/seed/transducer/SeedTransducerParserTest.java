package parser.seed.transducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import conf.TestConfiguration;
import org.junit.Test;
import parser.seed.transducer.model.SeedTransducerPOJO;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class SeedTransducerParserTest {


    /**
     * // JsonParser.Feature for configuring parsing settings:
     *
     * // to allow C/C++ style comments in JSON (non-standard, disabled by default)
     * // (note: with Jackson 2.5, there is also `mapper.enable(feature)` / `mapper.disable(feature)`)
     * mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
     * // to allow (non-standard) unquoted field names in JSON:
     * mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
     * // to allow use of apostrophes (single quotes), non standard
     * mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
     *
     * // JsonGenerator.Feature for configuring low-level JSON generation:
     *
     * // to force escaping of non-ASCII characters:
     * mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
     */


    /**
     * com.fasterxml.jackson.databind.JsonMappingException: No content to map due to end-of-input
     *java.io.FileNotFoundException: src\resources\seedTransducerExample.json (Le chemin d’accès spécifié est introuvable)
     * com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of parser.seed.transducer.model.SeedTransducerPOJO: no suitable constructor found, can not deserialize from Object value (missing default constructor or creator, or perhaps need to add/enable type information?)
     *  at [Source: src\test\resources\seedTransducerExample.json; line: 2, column: 3]
     */
    @Test
    public void parseTest() throws IOException {
        // TODO - IOException
        SeedTransducerParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample.json"));
    }

}
