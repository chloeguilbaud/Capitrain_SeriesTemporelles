package generator;

import org.junit.Test;

import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;
import utils.DecorationTableMock;
import utils.SeedTransducerMock;
import generator.Generator;

public class GeneratorManagerTest {

    @Test
    public void javaGenerationPeakFeatureTest() {
        SeedTransducer seed = SeedTransducerMock.get();
        DecorationTable table = DecorationTableMock.getFeatures();
        GeneratorManager generator = new GeneratorManager();
        System.out.println(generator.generateCode(Generator.JAVA, seed, table).toString());
    }

    @Test
    public void javaGenerationPeakFootprintTest() {
        SeedTransducer seed = SeedTransducerMock.get();
        DecorationTable table = DecorationTableMock.getFootprint();
        GeneratorManager generator = new GeneratorManager();
        System.out.println(generator.generateCode(Generator.JAVA, seed, table).toString());
    }
    
}