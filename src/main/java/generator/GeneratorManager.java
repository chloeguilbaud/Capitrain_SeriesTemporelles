package generator;

import language.java.JavaGenerator;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

/**
 * Handle all the generators and offers a black box program
 * for every user of the languages generations
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class GeneratorManager implements Generator {

    /**
     * Function that generate the code in the given language
     * @param language    The target language
     * @param seedTransducer    The {@link SeedTransducer}
     * @param decorationTable   The {@link DecorationTable}
     * @return a {@link StringBuffer} of the whole generated code
     */
    public StringBuffer generateCode(String targetLanguage, SeedTransducer seedTransducer, DecorationTable decorationTable) {
        switch(targetLanguage) {
            case Generator.JAVA:
                Language java = new JavaGenerator();
                return java.generateCode(seedTransducer, decorationTable);
            default:
                // TODO: throw error
                return null;
        }
    }
}
