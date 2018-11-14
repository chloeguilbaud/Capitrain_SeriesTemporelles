package parser.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParsingResult<T, E> {

    private Optional<T> result;
    private List<E> parsingErrors;


    public ParsingResult() {
        this.result = Optional.empty();
        this.parsingErrors = new ArrayList<>();
    }

    public Optional<T> getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = Optional.of(result);
    }

    public void removeParsingResult() {
        this.result = Optional.empty();
    }

    public List<E> getParsingErrors() {
        return parsingErrors;
    }

    public void addParsingError(E er) {
        this.parsingErrors.add(er);
    }

    public boolean removeParsingError(E er) {
        return this.parsingErrors.remove(er);
    }

    public boolean hasErrors() {
        return this.parsingErrors.size() > 0;
    }

    @Override
    public String toString() {
        return "ParsingResult{" +
                "result=" + result +
                ", parsingErrors=" + parsingErrors +
                '}';
    }
}
