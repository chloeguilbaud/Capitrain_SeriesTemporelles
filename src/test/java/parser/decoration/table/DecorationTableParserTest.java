package parser.decoration.table;

import conf.TestConfiguration;
import model.decoration.table.DecorationTable;

import org.junit.Test;
import parser.decoration.table.process.DecorationTableParsingResult;
import utils.Comparator;
import utils.DecorationTableMock;

import java.io.File;
import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DecorationTableParserTest {

    @Test
    public void testParse() throws IOException {

        DecorationTable tab = DecorationTableMock.getFeatures();
        DecorationTableParsingResult res = DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "decorationTableExampleComplete_feature.json"));

        System.out.println(res.getParsingErrors());
        assertFalse("Parsing OK so no errors", res.hasErrors());
        assertTrue("Decoration table is present so no errors", res.getResult().isPresent());
        assertEquals("Decoration table Name", tab.getName(), res.getResult().get().getName());
        assertEquals("Decoration table registers", tab.getRegisters(), res.getResult().get().getRegisters());
        assertEquals("Decoration table returns", tab.getReturns(), res.getResult().get().getReturns());
        assertEquals("Decoration table returns", tab.getInstructions(), res.getResult().get().getInstructions());

    }

    @Test
    public void test_model() throws IOException {
        DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "decorationTableExampleComplet_features.json"));
    }



    /**
     * TODO
     * this att or this att
     * parameters typing
     * unexeoected element in json
     * Mappe parser errors
     * InitVaruablesPOJO : var or function
     */
}
