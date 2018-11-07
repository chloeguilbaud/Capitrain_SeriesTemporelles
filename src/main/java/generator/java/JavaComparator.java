package generator.java;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import model.seed.transducer.ArcOperator;

public enum JavaComparator {

    EQ("=="),
    LT("<"),
    GT(">"),
    LEQ("<="),
    GEQ(">=");

    private String label;

    JavaComparator(String lab) {
        this.label = lab;
    }

    public static Optional<JavaComparator> fromLabel(String lab) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null));
    }

    public static List<JavaComparator> valuesAsList() {
        return Arrays.asList(values());
    }

    public static JavaComparator getFromOperator(ArcOperator o) {
        switch(o) {
            case EQ:
                return EQ;
            case LT:
                return LT;
            case GT:
                return GT;
            case LEQ:
                return LEQ;
            case GEQ:
                return GEQ;
            default:
                return EQ;
        }
    }

    public String getLabel() {
        return label;
    }

}
