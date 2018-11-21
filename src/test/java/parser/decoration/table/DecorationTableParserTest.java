package parser.decoration.table;

import utils.TestConfiguration;
import model.decoration.table.DecorationTable;

import model.decoration.table.Instruction;
import model.decoration.table.InstructionKey;
import org.junit.Test;
import parser.decoration.table.process.DecorationTableParsingResult;
import utils.DecorationTableMock;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DecorationTableParserTest {

    @Test
    public void testParse() throws IOException {

        DecorationTable tab = DecorationTableMock.getFeatures();
        DecorationTableParsingResult res = DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "decorationTableExample_feature.json"));
        
        assertFalse("Parsing OK so no errors", res.hasErrors());
        assertTrue("Decoration table is present so no errors", res.getResult().isPresent());
        assertEquals("Decoration table Name", tab.getName(), res.getResult().get().getName());
        assertEquals("Decoration table registers", tab.getRegisters(), res.getResult().get().getRegisters());
        assertEquals("Decoration table returns", tab.getReturns(), res.getResult().get().getReturns());

        HashMap<InstructionKey, Instruction> getInstExpected = tab.getInstructions();
        HashMap<InstructionKey, Instruction> getInstActual = res.getResult().get().getInstructions();
        assertEquals(tab.getInstructions().size(), res.getResult().get().getInstructions().size());
        assertEquals("Decoration table instructions", tab.getInstructions(), res.getResult().get().getInstructions());

    }

    @Test
    public void test_model() throws IOException {
        DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "decorationTableExampleComplet_features.json"));
    }

}
