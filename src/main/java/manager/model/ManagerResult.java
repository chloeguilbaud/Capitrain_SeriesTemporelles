package manager.model;

import parser.decoration.table.process.DecorationTableParsingResult;
import parser.seed.transducer.process.SeedTransducerParsingResult;

/**
 * Program result containing the results from the seed transducer and decoration table parsing
 * as well as success state.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class ManagerResult {

    private DecorationTableParsingResult decorationTableParsingResult;
    private SeedTransducerParsingResult seedTransducerParsingResult;
    private boolean processSuccess;

    public ManagerResult(DecorationTableParsingResult decorationTableParsingResult, SeedTransducerParsingResult seedTransducerParsingResult, boolean processSuccess) {
        this.decorationTableParsingResult = decorationTableParsingResult;
        this.seedTransducerParsingResult = seedTransducerParsingResult;
        this.processSuccess = processSuccess;
    }

    public DecorationTableParsingResult getDecorationTableParsingResult() {
        return decorationTableParsingResult;
    }

    public SeedTransducerParsingResult getSeedTransducerParsingResult() {
        return seedTransducerParsingResult;
    }

    public boolean isProcessSuccess() {
        return processSuccess;
    }

    @Override
    public String toString() {
        return "ManagerResult{" +
                "decorationTableParsingResult=" + decorationTableParsingResult +
                ", seedTransducerParsingResult=" + seedTransducerParsingResult +
                ", processSuccess=" + processSuccess +
                '}';
    }
}
