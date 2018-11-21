package manager.model;

import generator.error.GeneratorResult;
import parser.decoration.table.process.DecorationTableParsingResult;
import parser.seed.transducer.process.SeedTransducerParsingResult;

import java.util.Optional;

/**
 * Program result containing the results from the seed transducer and decoration table parsing
 * as well as success state.
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class ManagerResult {

    private DecorationTableParsingResult decorationTableParsingResult;
    private SeedTransducerParsingResult seedTransducerParsingResult;
    private Optional<GeneratorResult> generatorResult;

    public ManagerResult(DecorationTableParsingResult decorationTableParsingResult, SeedTransducerParsingResult seedTransducerParsingResult, GeneratorResult generatorResult) {
        this.decorationTableParsingResult = decorationTableParsingResult;
        this.seedTransducerParsingResult = seedTransducerParsingResult;
        this.generatorResult = Optional.of(generatorResult);
    }

    public ManagerResult(DecorationTableParsingResult decorationTableParsingResult, SeedTransducerParsingResult seedTransducerParsingResult) {
        this.decorationTableParsingResult = decorationTableParsingResult;
        this.seedTransducerParsingResult = seedTransducerParsingResult;
        this.generatorResult = Optional.empty();
    }

    public GeneratorResult getGeneratorResult() {
        return generatorResult.get();
    }

    public DecorationTableParsingResult getDecorationTableParsingResult() {
        return decorationTableParsingResult;
    }

    public SeedTransducerParsingResult getSeedTransducerParsingResult() {
        return seedTransducerParsingResult;
    }

    public boolean parsingSuccess() {
        return !(decorationTableParsingResult.hasErrors() || seedTransducerParsingResult.hasErrors());
    }

    public boolean generationSuccess() {
        return generatorResult.filter(generatorResult1 -> !generatorResult1.hasErrors()).isPresent();
    }

    public boolean processSuccess() {
        return parsingSuccess() && generationSuccess();
    }

    @Override
    public String toString() {
        return "ManagerResult{" +
                "decorationTableParsingResult=" + decorationTableParsingResult +
                ", seedTransducerParsingResult=" + seedTransducerParsingResult +
                ", generatorResult=" + generatorResult +
                '}';
    }
}
