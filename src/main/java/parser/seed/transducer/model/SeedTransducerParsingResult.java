package parser.seed.transducer.model;

import parser.seed.transducer.error.manager.SeedTransducerParsingError;
import model.seed.transducer.SeedTransducer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeedTransducerParsingResult {

    private Optional<SeedTransducer> seedTransducer;
    private List<SeedTransducerParsingError> parsingErrors;

    public SeedTransducerParsingResult() {
        this.seedTransducer = Optional.empty();
        this.parsingErrors = new ArrayList<>();
    }

    public Optional<SeedTransducer> getSeedTransducer() {
        return seedTransducer;
    }

    public void setSeedTransducer(SeedTransducer seedTransducer) {
        this.seedTransducer = Optional.of(seedTransducer);
    }

    public void removeSeedTransducer() {
        this.seedTransducer = Optional.empty();
    }

    public List<SeedTransducerParsingError> getParsingErrors() {
        return parsingErrors;
    }

    public boolean addParsingError(SeedTransducerParsingError er) {
        return this.parsingErrors.add(er);
    }

    public boolean removeParsingError(SeedTransducerParsingError er) {
        return this.parsingErrors.remove(er);
    }

    public boolean hasErrors() {
        return this.parsingErrors.size() > 0;
    }



}
