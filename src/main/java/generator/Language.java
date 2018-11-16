package generator;

import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

/**
 * Interface of a Language.
 * This interface is required for every file that assume to be a Language ganerator of code
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public interface Language {

    /**
     * Function that generate the code
     * @param seedTransducer    The {@link SeedTransducer}
     * @param decorationTable   The {@link DecorationTable}
     * @return a {@link StringBuffer} of the whole generated code
     */
    StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable);

}
