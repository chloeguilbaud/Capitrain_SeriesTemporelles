package generator;

import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

/**
 * Interface of a Language.
 * This interface is required for every file that assume to be a Language generator of code
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public abstract class LanguageGenerator {

    /**
     * Function that generate the code
     * @param seedTransducer    The {@link SeedTransducer}
     * @param decorationTable   The {@link DecorationTable}
     * @return a {@link StringBuffer} of the whole generated code
     */
    protected abstract StringBuffer generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable);

}
