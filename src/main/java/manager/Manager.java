package manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import generator.GeneratorAvailableLanguages;
import generator.GeneratorManager;
import generator.error.GeneratorResult;
import manager.model.ManagerResult;
import model.decoration.table.DecorationTable;
import model.seed.transducer.SeedTransducer;
import parser.decoration.table.DecorationTableParser;
import parser.decoration.table.process.DecorationTableParsingResult;
import parser.seed.transducer.SeedTransducerParser;
import parser.seed.transducer.process.SeedTransducerParsingResult;

/**
 * Manager of the whole soft.
 * Handle the main process using parser and generator
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class Manager {

    /**
     * Entry point of the program enabling code generation from seed transducer and decoration table.
     * @param seedTransducerFilePath Path to the seed transducer JSON file
     * @param decorationTableFilePath Path to the decoration table JSON file
     */
    public static ManagerResult process(
            String seedTransducerFilePath, String decorationTableFilePath,
            String generateCodeTargetFolder, GeneratorAvailableLanguages languages) {

        // Parse
        DecorationTableParsingResult decorationTableParsingResult = DecorationTableParser.parse(decorationTableFilePath);
        SeedTransducerParsingResult seedTransducerParsingResult = SeedTransducerParser.parse(seedTransducerFilePath);

        // Generate code
        if (!(decorationTableParsingResult.hasErrors() && seedTransducerParsingResult.hasErrors())) {
            SeedTransducer seedTransducer = seedTransducerParsingResult.getResult().get();
            DecorationTable decorationTable = decorationTableParsingResult.getResult().get();
            GeneratorResult generatorResult = GeneratorManager.generateCode(languages, seedTransducer, decorationTable);
            ManagerResult managerResult = new ManagerResult(decorationTableParsingResult, seedTransducerParsingResult, generatorResult);

            String filename = seedTransducer.getName().substring(0, 1).toUpperCase()
                                + seedTransducer.getName().substring(1)
                                + "_" + decorationTable.getName()
                                + ".java";

            writeGenerationInFolder(generateCodeTargetFolder + "/" + filename, managerResult);

            return managerResult;
        } else {
            return new ManagerResult(decorationTableParsingResult, seedTransducerParsingResult);
        }
    }

    /**
     * Write the code in the folder
     * @param generateCodeTargetFolder
     * @param managerResult
     */
    private static void writeGenerationInFolder(String generateCodeTargetFolder, ManagerResult managerResult) {
        try {
            PrintWriter writer = new PrintWriter(new File(generateCodeTargetFolder));
            writer.println(managerResult.getGeneratorResult().getResult().toString());
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
            String seedTransducerFilePath, String decorationTableFilePath, GeneratorAvailableLanguages languages) {
        // TODO - logs

        ManagerResult managerResult = process(seedTransducerFilePath, decorationTableFilePath, "src/test/java/generated/", languages);

        return managerResult;

    }
}
