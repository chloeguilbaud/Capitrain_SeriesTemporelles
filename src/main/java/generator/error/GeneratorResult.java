package generator.error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Generation result representation containing generation result and list of errors
 * @author Chloe GUILBAUD &amp; Mael MAINCHAIN
 */
public class GeneratorResult {

    private Optional<StringBuffer> result;
    private List<GeneratorError<GeneratorErrorType>> errors;

    public GeneratorResult() {
        this.result = Optional.empty();
        this.errors = new ArrayList<>();
    }

    public void setResult(Optional<StringBuffer> result) {
        this.result = result;
    }

    public StringBuffer getResult() {
        return result.get();
    }

    public List<GeneratorError<GeneratorErrorType>> getErrors() {
        return errors;
    }

    protected void addError(GeneratorError<GeneratorErrorType> err) {
        this.errors.add(err);
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }
}
