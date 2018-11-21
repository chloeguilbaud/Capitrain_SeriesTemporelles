package generator;

import generator.error.GeneratorResult;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

/**
 * Interface of a Language.
 * This interface is required for every file that assume to be a Language generator of code
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public abstract class LanguageGenerator {

    /**
     * Function that generate the code
     * @param seedTransducer    The {@link SeedTransducer}
     * @param decorationTable   The {@link DecorationTable}
     * @return a {@link GeneratorResult} of the whole generated code containing the generated code or errors
     */
    protected abstract GeneratorResult generateCode(SeedTransducer seedTransducer, DecorationTable decorationTable);

}
