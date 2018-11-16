package parser.decoration.table;

import conf.TestConfiguration;
import model.decoration.table.DecorationTable;

import model.decoration.table.Instruction;
import model.decoration.table.InstructionKey;
import model.seed.transducer.ArcSemanticLetter;
import org.junit.Test;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import parser.decoration.table.process.DecorationTableParsingResult;
import utils.Comparator;
import utils.DecorationTableMock;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

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

        HashMap<InstructionKey, Instruction> getInstExpected = tab.getInstructions();
        HashMap<InstructionKey, Instruction> getInstActual = res.getResult().get().getInstructions();

        System.out.println("expected: " + getInstExpected.size() + " - " + getInstExpected);
        System.out.println("actual: " + getInstActual.size() + " - " + getInstActual);
        getInstActual.forEach((key, val) -> {
            if (!getInstExpected.containsKey(key)) {
                System.out.println("Actual key: " + key);
                System.out.println("Actual val: " + val);
                System.out.println("Expect val: " + tab.getInstruction(ArcSemanticLetter.MAYBE_AFTER, 1));
            }
        });
//        assertEquals(tab.getInstructions().size(), res.getResult().get().getInstructions().size());
        System.out.println();
        assertEquals("Decoration table instructions", tab.getInstructions(), res.getResult().get().getInstructions());

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
