package language.java;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import model.seed.transducer.SemanticLetter;

public enum JavaSemanticLetter {

    FOUND("found", SemanticLetter.FOUND),
    FOUND_END("found_end", SemanticLetter.FOUND_END),
    MAYBE_BEFORE("maybe_before", SemanticLetter.MAYBE_BEFORE),
    MAYBE_AFTER("maybe_after", SemanticLetter.MAYBE_AFTER),
    OUT_RESET("out_reset", SemanticLetter.OUT_RESET),
    IN("in", SemanticLetter.IN),
    OUT_AFTER("out_after", SemanticLetter.OUT_AFTER),
    OUT("out", SemanticLetter.OUT);

    private String label;
    private SemanticLetter semanticLetter;

    JavaSemanticLetter(String lab, SemanticLetter semanticLetter) {
        this.label = lab;
        this.semanticLetter = semanticLetter;
    }

    public static Optional<JavaSemanticLetter> fromLabel(String lab) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null));
    }

    public static Optional<JavaSemanticLetter> fromSemanticLetter(SemanticLetter semanticLetter) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getSemanticLetter().equals(semanticLetter)).findAny().orElse(null));
    }

    public static List<JavaSemanticLetter> valuesAsList() {
        return Arrays.asList(values());
    }

    public String getLabel() {
        return this.label;
    }

    public SemanticLetter getSemanticLetter() {
        return this.semanticLetter;
    }
}
