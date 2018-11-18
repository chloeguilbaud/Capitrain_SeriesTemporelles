package parser.decoration.table;


import utils.TestConfiguration;
import org.junit.Test;
import parser.decoration.table.process.DecorationTableParsingResult;

import java.io.File;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class DecorationTableConverterTest {

    @Test
    public void convertTest_negativeVariableIndex() {

        DecorationTableParsingResult res = DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "decorationTableExampleComplete_feature.json"));

        assertFalse("Parsing OK so no errors", res.hasErrors());
        assertTrue("Decoration table is present so no errors", res.getResult().isPresent());
        assertEquals(12, res.getResult().get().getInstructions().size());

    }

    // TODO - given semantic letter has to be valid and not given before
    // TODO - given function names have to be known by the programme
    // TODO - unique semantic letter definition

}
