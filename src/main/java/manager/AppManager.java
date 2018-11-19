package manager;

import generator.Generator;
import generator.GeneratorManager;

/**
 * Manager of the whole soft.
 * Handle the main process using parser and generator
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class AppManager {

    /**
     * Entry point of the program
     * @param args
     */
    public static void main(String[] args) {
        Generator generator = new GeneratorManager();

        // TODO: get transducer and table from file
        // SeedTransducer seedTransducer = initTransducer();
        // DecorationTable decorationTable = initDecorationTable();

        // StringBuffer javaCode = generator.generateCode(Generator.JAVA, seedTransducer, decorationTable);

        // System.out.println(javaCode.toString());
    }
}
