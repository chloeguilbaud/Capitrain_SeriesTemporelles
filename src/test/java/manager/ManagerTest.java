package manager;

import generator.GeneratorAvailableLanguages;
import manager.model.ManagerResult;
import model.seed.transducer.SeedTransducer;
import org.junit.Test;
import utils.*;

import static org.junit.Assert.*;

public class ManagerTest {

    private final String path = TestConfiguration.TEST_FILE_PATH_SEED_TRANSDUCER_PARSER_MANAGER.getValue();

    @Test
    public void process_NoFileWriting() {
        ManagerResult managerResult = Manager.process(path + "SeedTransducerPeak.json",
                path + "DecorationTableFeature.json",
                GeneratorAvailableLanguages.JAVA);

        assertTrue("Parsing success", managerResult.parsingSuccess());
        assertEquals("Parsed feature decoration table", DecorationTableMock.getFeatures(), managerResult.getDecorationTableParsingResult().getResult().get());

        SeedTransducer stExpected = PeakSeedTransducerMock.get();
        SeedTransducer stActual = managerResult.getSeedTransducerParsingResult().getResult().get();
        assertEquals("Seed Name", stExpected.getName(), stActual.getName());
        assertEquals("Seed Before", stExpected.getBefore(), stActual.getBefore());
        assertEquals("Seed After", stExpected.getAfter(), stActual.getAfter());
        assertEquals("Seed init state", stExpected.getInitState(), stActual.getInitState());
        assertEquals("Seed states", stExpected.getStates(), stActual.getStates());
        assertTrue("Seed arcs", Comparator.compare(stExpected, stActual));
        assertTrue("Generation success", managerResult.generationSuccess());

    }

    @Test
    public void process_NoFileWriting_ParsingErrorsSeedTransducer() {
        ManagerResult managerResult = Manager.process(path + "SeedTransducerPeak_Error.json",
                path + "DecorationTableFeature.json",
                GeneratorAvailableLanguages.JAVA);

        assertFalse("Parsing success", managerResult.parsingSuccess());
        assertFalse("Decoration table has no errors", managerResult.getDecorationTableParsingResult().hasErrors());
        assertTrue("Decoration table has errors", managerResult.getSeedTransducerParsingResult().hasErrors());

    }

    @Test
    public void process_NoFileWriting_ParsingErrorsDecorationTable() {

        ManagerResult managerResult = Manager.process(path + "SeedTransducerPeak.json",
                path + "DecorationTableFeature_Error.json",
                GeneratorAvailableLanguages.JAVA);

        assertFalse("Parsing success", managerResult.parsingSuccess());
        assertTrue("Decoration table has no errors", managerResult.getDecorationTableParsingResult().hasErrors());
        assertFalse("Decoration table has errors", managerResult.getSeedTransducerParsingResult().hasErrors());

    }


    @Test(expected = RuntimeException.class)
    public void processTest_WithFileWritingInvalidPath() {

        ManagerResult managerResult = Manager.process(
                path + "SeedTransducerPeak.json",
                path + "DecorationTableFeature.json",
                "Path",
                GeneratorAvailableLanguages.JAVA);

    }

    @Test
    public void processTest_WithFileWriting() {

        ManagerResult managerResult = Manager.process(
                path + "SeedTransducerPeak.json",
                path + "DecorationTableFeature.json",
                path,
                GeneratorAvailableLanguages.JAVA);

        assertTrue("Parsing success", managerResult.parsingSuccess());
        assertEquals("Parsed feature decoration table", DecorationTableMock.getFeatures(), managerResult.getDecorationTableParsingResult().getResult().get());

        SeedTransducer stExpected = PeakSeedTransducerMock.get();
        SeedTransducer stActual = managerResult.getSeedTransducerParsingResult().getResult().get();
        assertEquals("Seed Name", stExpected.getName(), stActual.getName());
        assertEquals("Seed Before", stExpected.getBefore(), stActual.getBefore());
        assertEquals("Seed After", stExpected.getAfter(), stActual.getAfter());
        assertEquals("Seed init state", stExpected.getInitState(), stActual.getInitState());
        assertEquals("Seed states", stExpected.getStates(), stActual.getStates());
        assertTrue("Seed arcs", Comparator.compare(stExpected, stActual));
        assertTrue("Generation success", managerResult.generationSuccess());

    }


}