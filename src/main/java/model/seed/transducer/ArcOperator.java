package model.seed.transducer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Operators Enumeration that characterise an {@link Arc} in a {@link SeedTransducer}
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public enum ArcOperator {

    // Equals
    EQ("eq"),
    // Less than
    LT("lt"),
    // Greater than
    GT("gt"),
    // Less or equals than
    LEQ("leq"),
    // Greater or equals
    GEQ("geq");

    private String label;

    ArcOperator(String lab) {
        this.label = lab;
    }

    public static Optional<ArcOperator> fromLabel(String lab) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null));
    }

    public static List<ArcOperator> valuesAsList() {
        return Arrays.asList(values());
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
