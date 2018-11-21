import app.AppTest;
import generated.GeneratedPeak_footprintTest;
import generator.GeneratorManagerTest;
import language.java.JavaGeneratorErrorTypesTest;
import manager.ManagerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import parser.ParserTestSuite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ParserTestSuite.class,
        GeneratedPeak_footprintTest.class,
        GeneratorManagerTest.class,
        JavaGeneratorErrorTypesTest.class,
        ManagerTest.class,
        AppTest.class
})

public class TestSuite {


}
