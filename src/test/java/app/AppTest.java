package app;

import org.junit.Test;
import utils.TestConfiguration;

import static org.junit.Assert.*;

public class AppTest {

    private final String path = TestConfiguration.TEST_FILE_PATH_APP.getValue();

    @Test
    public void appTest_GenerationWithWritingInFile() {

        App.main(new String[] {
                path + "SeedTransducerPeak.json",
                path + "DecorationTableFeature.json",
                "Java"});

    }

    @Test
    public void appTest_GenerationWithoutWritingInFile() {
        App.main(new String[] {
                path + "SeedTransducerPeak.json",
                path + "DecorationTableFeature.json",
                "Java",
                path});
    }

    @Test(expected = RuntimeException.class)
    public void appTest_InvalidParameters() {
        App.main(new String[] {
                path + "SeedTransducerPeak.json"});
    }

}