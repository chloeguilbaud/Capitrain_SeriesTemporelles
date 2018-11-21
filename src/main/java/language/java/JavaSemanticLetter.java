package language.java;

import model.seed.transducer.ArcSemanticLetter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Java code representation of an {@link ArcSemanticLetter}
 * Convert all letters into java-compatible letters
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public enum JavaSemanticLetter {

    FOUND("found", ArcSemanticLetter.FOUND),
    FOUND_END("found_end", ArcSemanticLetter.FOUND_END),
    MAYBE_BEFORE("maybe_before", ArcSemanticLetter.MAYBE_BEFORE),
    MAYBE_AFTER("maybe_after", ArcSemanticLetter.MAYBE_AFTER),
    OUT_RESET("out_reset", ArcSemanticLetter.OUT_RESET),
    IN("in", ArcSemanticLetter.IN),
    OUT_AFTER("out_after", ArcSemanticLetter.OUT_AFTER),
    OUT("out", ArcSemanticLetter.OUT);

    /**
     * Java name of the {@link JavaSemanticLetter}
     */
    private String label;
    /**
     * Related {@link ArcSemanticLetter}
     */
    private ArcSemanticLetter semanticLetter;

    JavaSemanticLetter(String lab, ArcSemanticLetter semanticLetter) {
        this.label = lab;
        this.semanticLetter = semanticLetter;
    }

    public static Optional<JavaSemanticLetter> fromLabel(String lab) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null));
    }

    public static Optional<JavaSemanticLetter> fromSemanticLetter(ArcSemanticLetter semanticLetter) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getSemanticLetter().equals(semanticLetter)).findAny().orElse(null));
    }

    public static List<JavaSemanticLetter> valuesAsList() {
        return Arrays.asList(values());
    }

    public String getLabel() {
        return this.label;
    }

    public ArcSemanticLetter getSemanticLetter() {
        return this.semanticLetter;
    }
}
