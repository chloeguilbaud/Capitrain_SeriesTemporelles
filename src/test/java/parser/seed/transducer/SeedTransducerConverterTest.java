package parser.seed.transducer;

import conf.TestConfiguration;
import model.seed.transducer.SeedTransducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parser.seed.transducer.error.manager.SeedTransducerParsingErrorType;
import parser.seed.transducer.model.SeedTransducerParsingResult;
import utils.Comparator;
import utils.SeedTransducerMock;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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


}
