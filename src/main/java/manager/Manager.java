package manager;

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

        // TODO - logs
        ManagerResult managerResult = process(seedTransducerFilePath, decorationTableFilePath, languages);

        writeGenerationInFolder(generateCodeTargetFolder, managerResult);

        return managerResult;
    }

    private static void writeGenerationInFolder(String generateCodeTargetFolder, ManagerResult managerResult) {
        // TODO
        throw new RuntimeException("not implemented yet");
    }

    /**
     * Entry point of the program enabling code generation from seed transducer and decoration table.
     * @param seedTransducerFilePath Path to the seed transducer JSON file
     * @param decorationTableFilePath Path to the decoration table JSON file
     */
    public static ManagerResult process(
            String seedTransducerFilePath, String decorationTableFilePath, GeneratorAvailableLanguages languages) {

        // TODO - logs

        // Parse
        DecorationTableParsingResult decorationTableParsingResult = DecorationTableParser.parse(decorationTableFilePath);
        SeedTransducerParsingResult seedTransducerParsingResult = SeedTransducerParser.parse(seedTransducerFilePath);

        // Generate code
        if (!(decorationTableParsingResult.hasErrors() && seedTransducerParsingResult.hasErrors())) {
            SeedTransducer seedTransducer = seedTransducerParsingResult.getResult().get();
            DecorationTable decorationTable = decorationTableParsingResult.getResult().get();
            GeneratorResult generatorResult = GeneratorManager.generateCode(languages, seedTransducer, decorationTable);
            return new ManagerResult(decorationTableParsingResult, seedTransducerParsingResult, generatorResult);
        } else {
            return new ManagerResult(decorationTableParsingResult, seedTransducerParsingResult);
        }

    }
}
