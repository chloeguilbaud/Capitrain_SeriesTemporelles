package parser.seed.transducer.model;

import parser.seed.transducer.error.manager.SeedTransducerParsingError;
import model.seed.transducer.SeedTransducer;

import java.util.List;

public class SeedTransducerParsingResult {

    private SeedTransducer seedTransducer;
    private List<SeedTransducerParsingError> parsingErrors;

    public SeedTransducerParsingResult(SeedTransducer seedTransducer, List<SeedTransducerParsingError> parsingErrors) {
        this.seedTransducer = seedTransducer;
        this.parsingErrors = parsingErrors;
    }

    public SeedTransducer getSeedTransducer() {
        return seedTransducer;
    }

    public void setSeedTransducer(SeedTransducer seedTransducer) {
        this.seedTransducer = seedTransducer;
    }

    public List<SeedTransducerParsingError> getParsingErrors() {
        return parsingErrors;
    }

    public void setParsingErrors(List<SeedTransducerParsingError> parsingErrors) {
        this.parsingErrors = parsingErrors;
    }

    public boolean hasErrors() {
        return this.parsingErrors.size() > 0;
    }



}
