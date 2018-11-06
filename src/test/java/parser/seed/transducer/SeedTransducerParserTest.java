package parser.seed.transducer;

import conf.TestConfiguration;
import model.seed.transducer.*;

import org.junit.Test;
import parser.seed.transducer.model.SeedTransducerParsingResult;
import utils.Comparator;
import utils.SeedTransducerMock;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

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
     *java.io.FileNotFoundException: src\resources\convertTest_unknownElement.json (Le chemin d’accès spécifié est introuvable)
     * com.fasterxml.jackson.databind.JsonMappingException: Can not construct instance of parser.seed.transducer.model.SeedTransducerPOJO: no suitable constructor found, can not deserialize from Object value (missing default constructor or creator, or perhaps need to add/enable type information?)
     *  at [Source: src\test\resources\convertTest_unknownElement.json; line: 2, column: 3]
     */
    @Test
    public void parseTest() throws IOException {
        // TODO - IOException

        SeedTransducer seed = SeedTransducerMock.get();
        
        SeedTransducerParsingResult res = SeedTransducerParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample.json"));
        assertTrue("Seed is present so no errors", res.getSeedTransducer().isPresent());
        assertFalse("Parsing OK so no errors", res.hasErrors());
        assertEquals("Seed Name", seed.getName(), res.getSeedTransducer().get().getName());
        assertEquals("Seed init state", seed.getInitState(), res.getSeedTransducer().get().getInitState());
        assertEquals("Seed states", seed.getStates(), res.getSeedTransducer().get().getStates());
        assertTrue("Seed arcs", Comparator.compare(seed, res.getSeedTransducer().get()));

    }

    @Test
    public void compTest() {
        HashSet<Arc> hash1 = new HashSet<>();
        HashSet<Arc> hash2= new HashSet<>();

        hash1.add(new Arc(new State("s1"), new State("s2"), Operator.LEQ, SemanticLetter.MAYBE_AFTER));
        //hash1.add(new Arc(new State("s1"), new State("s3"), Operator.LEQ, SemanticLetter.MAYBE_AFTER));
        hash2.add(new Arc(new State("s1"), new State("s2"), Operator.LEQ, SemanticLetter.MAYBE_AFTER));

        Arc a1 = new Arc(new State("s1"), new State("s2"), Operator.LEQ, SemanticLetter.MAYBE_AFTER);
        Arc a2 = new Arc(new State("s1"), new State("s2"), Operator.LEQ, SemanticLetter.MAYBE_AFTER);

        System.out.println("hash - " + hash1.equals(hash2));
        System.out.println("contains - " + hash1.containsAll(Arrays.asList(a1)));
        System.out.println("arcs - " + a1.equals(a2));

        SeedTransducer s1 = new SeedTransducer("s1");
        s1.setArcs(hash1);
        SeedTransducer s2 = new SeedTransducer("s2");
        s2.setArcs(hash2);
        System.out.println(Comparator.compare(s1, s2));

    }

}
