package generator;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.PrintWriter;

import org.junit.Test;

import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;
import utils.DecorationTableMock;
import utils.PeakSeedTransducerMock;

public class GeneratorManagerTest {

    @Test
    public void javaGenerationPeakFeatureTest() {
        SeedTransducer seed = PeakSeedTransducerMock.get();
        DecorationTable table = DecorationTableMock.getFeatures();
        try {
            PrintWriter writer = new PrintWriter(new File("src/test/java/generated/Peak_feature.java"));
            writer.println(GeneratorManager.generateCode(AvailableLanguages.JAVA, seed, table).toString());
            writer.close();
        } catch (Exception e) {
            assertTrue(e.getMessage(), false);
        }
    }

    @Test
    public void javaGenerationPeakFootprintTest() {
        SeedTransducer seed = PeakSeedTransducerMock.get();
        DecorationTable table = DecorationTableMock.getFootprint();
        try {
            PrintWriter writer = new PrintWriter(new File("src/test/java/generated/Peak_footprint.java"));
            writer.println(GeneratorManager.generateCode(AvailableLanguages.JAVA, seed, table).toString());
            writer.close();
        } catch (Exception e) {
            assertTrue(e.getMessage(), false);
        }
    }
    
}