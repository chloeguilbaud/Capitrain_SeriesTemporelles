package parser.seed.transducer;

import conf.TestConfiguration;
import model.seed.transducer.SeedTransducer;
import org.junit.Test;
import parser.seed.transducer.error.manager.SeedTransducerParsingErrorType;
import parser.seed.transducer.model.SeedTransducerParsingResult;
import utils.Comparator;
import utils.SeedTransducerMock;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SeedTransducerConverterTest {

    @Test
    public void convertTest() throws IOException {

        SeedTransducer seed = SeedTransducerMock.get();

        SeedTransducerParsingResult res = SeedTransducerConverter.convert(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample.json"));
        assertTrue("Seed is present so no errors", res.getSeedTransducer().isPresent());
        assertFalse("Parsing OK so no errors", res.hasErrors());
        assertEquals("Seed Name", seed.getName(), res.getSeedTransducer().get().getName());
        assertEquals("Seed init state", seed.getInitState(), res.getSeedTransducer().get().getInitState());
        assertEquals("Seed states", seed.getStates(), res.getSeedTransducer().get().getStates());
        assertTrue("Seed arcs", Comparator.compare(seed, res.getSeedTransducer().get()));

    }

    @Test
    public void convertTest_unknownElement() throws IOException {

        SeedTransducer seed = SeedTransducerMock.get();

        File jsonFile = new File(TestConfiguration.TEST_FILE_PATH_SEED_TRANSDUCER_PARSER.getValue() + "convertTest_unknownElement.json");
        SeedTransducerParsingResult res = SeedTransducerConverter.convert(jsonFile);

        assertFalse("No parsing so errors - unexpected element in json file", res.getSeedTransducer().isPresent());
        assertTrue("Parsing KO so errors", res.hasErrors());
        assertEquals("Unexpected JSON element error", 1, res.getParsingErrors().size());
        assertEquals("Unexpected JSON element error", SeedTransducerParsingErrorType.UNRECOGNIZED_PROPERTY_IN_JSON, res.getParsingErrors().get(0).getErrorType());

        assertTrue("Unexpected JSON element error - msg",
                res.getParsingErrors().get(0).getErrorMsg().contains("Unrecognized field \"je suis un intrus"));


    }

}
