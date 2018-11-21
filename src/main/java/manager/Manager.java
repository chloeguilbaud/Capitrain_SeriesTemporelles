package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

import generator.GeneratorAvailableLanguages;
import generator.GeneratorManager;
import generator.error.GeneratorResult;
import manager.model.ManagerResult;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;
import parser.decoration.table.DecorationTableParser;
import parser.decoration.table.errors.DecorationTableParsingError;
import parser.decoration.table.process.DecorationTableParsingResult;
import parser.seed.transducer.SeedTransducerParser;
import parser.seed.transducer.process.SeedTransducerParsingResult;

/**
 * Manager of the whole soft.
 * Handle the main process using parser and generator
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class Manager {

    private static Logger logger = Logger.getLogger(Manager.class);

    /**
     * Entry point of the program enabling code generation from seed transducer and decoration table.
     * @param seedTransducerFilePath Path to the seed transducer JSON file
     * @param decorationTableFilePath Path to the decoration table JSON file
     */
    public static ManagerResult process(
            String seedTransducerFilePath, String decorationTableFilePath,
            String generateCodeTargetFolder, GeneratorAvailableLanguages language) {

        ManagerResult managerResult = process(seedTransducerFilePath, decorationTableFilePath, language);
        DecorationTableParsingResult decorationTableParsingResult = managerResult.getDecorationTableParsingResult();
        SeedTransducerParsingResult seedTransducerParsingResult = managerResult.getSeedTransducerParsingResult();

        if (!(decorationTableParsingResult.hasErrors() && seedTransducerParsingResult.hasErrors())) {

            // Getting parsing results
            SeedTransducer seedTransducer = seedTransducerParsingResult.getResult().get();
            DecorationTable decorationTable = decorationTableParsingResult.getResult().get();

            // Writing generation code in file
            String filename = seedTransducer.getName().substring(0, 1).toUpperCase()
                    + seedTransducer.getName().substring(1)
                    + "_" + decorationTable.getName();

            logger.info("Writing generated code in " + generateCodeTargetFolder);
            writeGenerationInFolder(generateCodeTargetFolder + "/" + filename, managerResult);
            logger.info("Generated code written in " + generateCodeTargetFolder);

        }

        return managerResult;

    }

    /**
     * Write the code in the folder
     * @param generateCodeTargetFolder Folder where the generated code will be written
     * @param managerResult Manager result object
     */
    private static void writeGenerationInFolder(String generateCodeTargetFolder, ManagerResult managerResult) {
        try {
            PrintWriter writer = new PrintWriter(new File(generateCodeTargetFolder));
            writer.println(managerResult.getGeneratorResult().toString());
            writer.close();
        } catch(FileNotFoundException e) {
            throw new RuntimeException("Target generation path incorrect");
        }
       
    }

    /**
     * Entry point of the program enabling code generation from seed transducer and decoration table.
     * @param seedTransducerFilePath Path to the seed transducer JSON file
     * @param decorationTableFilePath Path to the decoration table JSON file
     */
    public static ManagerResult process(
            String seedTransducerFilePath, String decorationTableFilePath, GeneratorAvailableLanguages language) {

        logger.info("Programme processing...");

        // Parse
        logger.info("Parsing processing...");

        // Decoration table
        logger.info("Parsing decoration table...");
        DecorationTableParsingResult decorationTableParsingResult = DecorationTableParser.parse(decorationTableFilePath);
        logger.info("Parsing decoration table finished");
        if(decorationTableParsingResult.hasErrors()) {
            logger.error("Parsing decoration table FAILURE");
            for(DecorationTableParsingError decorationTableParsingError : decorationTableParsingResult.getParsingErrors()) {
                logger.error("Decoration table parsing error: " + decorationTableParsingError);
            }
        } else {
            logger.info("Parsing decoration table SUCCESS");
        }

        logger.info("Parsing seed transducer...");
        SeedTransducerParsingResult seedTransducerParsingResult = SeedTransducerParser.parse(seedTransducerFilePath);
        logger.info("Parsing seed transducer finished");
        if(decorationTableParsingResult.hasErrors()) {
            logger.error("Parsing seed transducer FAILURE");
            for(DecorationTableParsingError decorationTableParsingError : decorationTableParsingResult.getParsingErrors()) {
                logger.error("Seed transducer parsing error: " + decorationTableParsingError);
            }
        } else {
            logger.info("Parsing decoration table SUCCESS");
        }

        logger.info("Parsing finished");

        // Generate code
        if (!(decorationTableParsingResult.hasErrors() && seedTransducerParsingResult.hasErrors())) {

            logger.info("Code generation from Decoration table and Seed transducer for \"" + language + "\" starting...");

            // Getting parsing results
            SeedTransducer seedTransducer = seedTransducerParsingResult.getResult().get();
            DecorationTable decorationTable = decorationTableParsingResult.getResult().get();

            // Generation launching
            GeneratorResult generatorResult = GeneratorManager.generateCode(language, seedTransducer, decorationTable);

            logger.info("Code generation from Decoration table and Seed transducer finished");
            if(generatorResult.hasErrors()) {
                logger.error("Code generation FAILURE");
                for(DecorationTableParsingError decorationTableParsingError : decorationTableParsingResult.getParsingErrors()) {
                    logger.error("Code generation error: " + decorationTableParsingError);
                }
            } else {
                logger.info("Code generation SUCCESS");
            }

            logger.info("Code generation finished");

            // Manager result
            return new ManagerResult(decorationTableParsingResult, seedTransducerParsingResult, generatorResult);
        } else {
            return new ManagerResult(decorationTableParsingResult, seedTransducerParsingResult);
        }

    }
}
