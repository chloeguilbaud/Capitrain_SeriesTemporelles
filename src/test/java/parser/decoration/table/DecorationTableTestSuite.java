package parser.decoration.table;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import parser.decoration.table.process.DecorationTableConverter;
import parser.seed.transducer.SeedTransducerConverterErrorManagerParameterizedTest;
import parser.seed.transducer.SeedTransducerConverterTest;
import parser.seed.transducer.SeedTransducerParserTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        DecorationTableParserTest.class,
        DecorationTableConverterTest.class,
        DecorationTableConverterErrorManagerParameterizedTest.class
})

public class DecorationTableTestSuite {


}
