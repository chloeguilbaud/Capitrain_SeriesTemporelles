package parser.decoration.table;

import conf.TestConfiguration;
import model.decoration.table.DecorationTable;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DecorationTableParserTest {

    @Test
    public void testParse() throws IOException {

        DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "decorationTableExampleComplet_features.json"));



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
