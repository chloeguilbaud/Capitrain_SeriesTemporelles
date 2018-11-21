import generated.GeneratedPeak_footprintTest;
import generator.GeneratorManagerTest;
import language.java.JavaGeneratorErrorTypesTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import parser.ParserTestSuite;
import parser.decoration.table.DecorationTableParserTestSuite;
import parser.seed.transducer.SeedTransducerParserTestSuite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ParserTestSuite.class,
        GeneratedPeak_footprintTest.class,
        GeneratorManagerTest.class,
        JavaGeneratorErrorTypesTest.class,
})

public class TestSuite {


}
