package parser;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import parser.decoration.table.DecorationTableParserTestSuite;
import parser.seed.transducer.SeedTransducerParserTestSuite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        DecorationTableParserTestSuite.class,
        SeedTransducerParserTestSuite.class,
})

public class ParserTestSuite {
}
