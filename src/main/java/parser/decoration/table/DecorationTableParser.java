package parser.decoration.table;

import parser.decoration.table.process.DecorationTableConverter;
import parser.decoration.table.process.DecorationTableParsingResult;
import model.decoration.table.DecorationTable;

import java.io.File;

/**
 * Utils class and entry point to the {@link DecorationTable} parser extracting data from the given JSON file.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class DecorationTableParser {

    public static DecorationTableParsingResult parse(String jsonFilePath) {
        return parse(new File(jsonFilePath));
    }

    public static DecorationTableParsingResult parse(File jsonFile) {
        return DecorationTableConverter.convert(jsonFile);
    }

}
