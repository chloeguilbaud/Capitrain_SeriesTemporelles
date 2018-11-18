package parser.seed.transducer;

import utils.TestConfiguration;
import model.seed.transducer.*;

import org.junit.Test;
import parser.seed.transducer.process.SeedTransducerParsingResult;
import utils.Comparator;
import utils.SeedTransducerMock;

import java.io.File;

import static org.junit.Assert.*;

public class SeedTransducerParserTest {

    @Test
    public void parseTest() {

        SeedTransducer seed = SeedTransducerMock.get();
        
        SeedTransducerParsingResult res = SeedTransducerParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "seedTransducerExample.json"));
        assertTrue("Seed is present so no errors", res.getResult().isPresent());
        assertFalse("Parsing OK so no errors", res.hasErrors());
        assertEquals("Seed Name", seed.getName(), res.getResult().get().getName());
        assertEquals("Seed Before", seed.getBefore(), res.getResult().get().getBefore());
        assertEquals("Seed After", seed.getAfter(), res.getResult().get().getAfter());
        assertEquals("Seed init state", seed.getInitState(), res.getResult().get().getInitState());
        assertEquals("Seed states", seed.getStates(), res.getResult().get().getStates());
        assertTrue("Seed arcs", Comparator.compare(seed, res.getResult().get()));

    }

}
