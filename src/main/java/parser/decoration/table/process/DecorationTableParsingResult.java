package parser.decoration.table.process;

import model.decoration.table.DecorationTable;
import parser.common.ParsingResult;
import parser.decoration.table.errors.DecorationTableParsingError;

/**
 * Decoration Table parsing result containing the {@link DecorationTable} extracted from the given JSON file.
 * If general error occur during the parsing, the result will contain it. If further error occur they will also
 * be listed. In that cas, the {@link java.util.Optional} {@link DecorationTable} should be empty.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class DecorationTableParsingResult extends ParsingResult<DecorationTable, DecorationTableParsingError> {

    public DecorationTableParsingResult() {
        super();
    }

}
