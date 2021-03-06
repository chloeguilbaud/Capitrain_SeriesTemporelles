package generator;

import generator.error.GeneratorResult;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

/**
 * Handle all the generators and offers a black box program
 * for every user of the languages generations.
 * Interface of a Generator.
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public abstract class GeneratorManager {

    /**
     * Function that generate the code in the given language
     * @param targetLanguage    The target language
     * @param seedTransducer    The {@link SeedTransducer}
     * @param decorationTable   The {@link DecorationTable}
     * @return a {@link GeneratorResult} of the whole generated code
     */
    public static GeneratorResult generateCode(GeneratorAvailableLanguages targetLanguage, SeedTransducer seedTransducer, DecorationTable decorationTable) {
        return targetLanguage.getLanguageGenerator().generateCode(seedTransducer, decorationTable);
    }
}
