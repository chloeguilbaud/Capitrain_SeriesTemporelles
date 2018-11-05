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


    @Test
    public void testing() throws IOException {

        File jsonFile = new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample.json");
        ObjectMapper mapper = new ObjectMapper();
        SeedTransducerPOJO seed = mapper.readValue(jsonFile, SeedTransducerPOJO.class);
        System.out.println("----------- POJO -----------");
        System.out.println(seed);

        Map<String, Integer> scoreByName = mapper.readValue(jsonFile, Map.class);
        System.out.println("----------- Map -----------");
        System.out.println(scoreByName);

        jsonFile = new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample_map.json");
        Map<String, Object> scoreByName2 = mapper.readValue(jsonFile, Map.class);
        System.out.println("----------- Map JSON obj And list -----------");
        System.out.println(scoreByName2);
        Object lhm = ((LinkedHashMap) (scoreByName2.get("testlist"))).get("prenom");
        System.out.println(scoreByName2.get("testlist").getClass());
        System.out.println("Sarah expected : " + lhm);

        Object tab = scoreByName2.get("testTab");
        System.out.println(scoreByName2.get("testTab").getClass());
        System.out.println("testTab : " + tab.getClass());

        System.out.println("----------- JSON Node -----------");
        jsonFile = new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample_jsonNode.json");
        ObjectNode root = (ObjectNode) mapper.readTree(jsonFile);
        String name = root.get("name").asText();
        int age = root.get("num").asInt();
        System.out.println("name : " + name);
        System.out.println("num : " + age);

        System.out.println("----------- Writting -----------");
        // can modify as well: this adds child Object as property 'other', set property 'type'
        root.with("other").put("type", "student");
        String json = mapper.writeValueAsString(root);
        System.out.println(json);


        System.out.println("----------- POJO List -----------");
        jsonFile = new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample_pojolist.json");
        mapper = new ObjectMapper();
        POJO pojo = mapper.readValue(jsonFile, POJO.class);
        System.out.println(pojo);

        System.out.println("----------- POJO List + Tab -----------");
        jsonFile = new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample_pojoListTab.json");
        mapper = new ObjectMapper();
        pojo = mapper.readValue(jsonFile, POJO.class);
        System.out.println(pojo);



    }


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
