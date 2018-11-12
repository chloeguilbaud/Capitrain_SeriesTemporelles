package parser.decoration.table;

import conf.TestConfiguration;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class DecorationTableParserTest {

    @Test
    public void test() throws IOException {
        DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "testDT.json"));
    }

}
