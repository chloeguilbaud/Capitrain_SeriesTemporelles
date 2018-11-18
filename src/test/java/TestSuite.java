import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import parser.decoration.table.DecorationTableTestSuite;
import parser.seed.transducer.SeedTransducerTestSuite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        DecorationTableTestSuite.class,
        SeedTransducerTestSuite.class,
})

public class TestSuite {


}
