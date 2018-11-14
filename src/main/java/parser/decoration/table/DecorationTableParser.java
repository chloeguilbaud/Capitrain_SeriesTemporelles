package parser.decoration.table;

import parser.decoration.table.process.DecorationTableConverter;
import parser.decoration.table.process.DecorationTableParsingResult;

import java.io.File;
import java.io.IOException;

class DecorationTableParser {

    static DecorationTableParsingResult parse(File jsonFile) throws IOException {
        return DecorationTableConverter.convert(jsonFile);
    }

}
