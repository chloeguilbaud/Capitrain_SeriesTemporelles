package manager;

import generator.Generator;
import generator.GeneratorManager;
import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.element.Affectation;
import model.decoration.table.element.Function;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.IntegerVal;
import model.decoration.table.element.Sum;
import model.decoration.table.element.Variable;
import model.seed.transducer.*;
import model.seed.transducer.ArcOperator;

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

       //  System.out.println(javaCode.toString());
    }
}
