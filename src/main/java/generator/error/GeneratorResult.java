package generator.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GeneratorResult {

    private Optional<StringBuffer> result;
    private List<GeneratorError> errors;

    public GeneratorResult() {
        this.result = Optional.empty();
        this.errors = new ArrayList<>();
    }

    public void setResult(Optional<StringBuffer> result) {
        this.result = result;
    }

    public Optional<StringBuffer> getResult() {
        return result;
    }

    public List<GeneratorError> getErrors() {
        return errors;
    }

    public void addError(GeneratorError err) {
        this.errors.add(err);
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }
}
