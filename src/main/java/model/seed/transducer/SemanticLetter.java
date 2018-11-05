package model.seed.transducer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum SemanticLetter {

    FOUND("found"),
    FOUND_END("found(end)"),
    MAYBE_BEFORE("maybe(before)"),
    MAYBE_AFTER("maybe(after)"),
    OUT_RESET("out(reset)"),
    IN("in"),
    OUT_AFTER("out(after)"),
    OUT("out");

    private String label;

    SemanticLetter(String lab) {
        this.label = lab;
    }

    public static Optional<SemanticLetter> fromLabel(String lab) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null));
    }

    public static List<SemanticLetter> valuesAsList() {
        return Arrays.asList(values());
    }

    public String getLabel() {
        return label;
    }

}
