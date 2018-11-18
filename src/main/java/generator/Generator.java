package generator;

import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;

/**
 * Interface of a Generator.
 * This interface is required for every file that assume to generate the code
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public interface Generator {

    public static String JAVA = "Java";

    /**
     * Function that generate the code in the given language
     * @param language    The target language
     * @param seedTransducer    The {@link SeedTransducer}
     * @param decorationTable   The {@link DecorationTable}
     * @return a {@link StringBuffer} of the whole generated code
     */
    public StringBuffer generateCode(String language, SeedTransducer seedTransducer, DecorationTable decorationTable);
}
