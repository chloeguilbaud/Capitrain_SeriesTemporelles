package model.seed.transducer;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum ArcOperator {

    EQ("eq"),
    LT("lt"),
    GT("gt"),
    LEQ("leq"),
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
