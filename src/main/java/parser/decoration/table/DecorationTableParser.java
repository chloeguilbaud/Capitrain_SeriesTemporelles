package parser.decoration.table;

import java.io.File;
import java.io.IOException;

class DecorationTableParser {

    static DecorationTableParsingResult parse(File jsonFile) throws IOException {
        return DecorationTableConverter.convert(jsonFile);
    }

}
