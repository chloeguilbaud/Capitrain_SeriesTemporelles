package language.java;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import model.seed.transducer.ArcOperator;

public enum JavaComparator {

    EQ("==", ArcOperator.EQ),
    LT("<", ArcOperator.LT),
    GT(">", ArcOperator.GT),
    LEQ("<=", ArcOperator.LEQ),
    GEQ(">=", ArcOperator.GEQ);

    private String label;
    private ArcOperator op;

    JavaComparator(String lab, ArcOperator op) {
        this.label = lab;
        this.op = op;
    }

    public static Optional<JavaComparator> fromLabel(String lab) {
        return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny();
    }

    public static Optional<JavaComparator> fromOperator(ArcOperator op) {
        return valuesAsList().stream().filter(m -> m.getOperator().equals(op)).findAny();
    }

    public static List<JavaComparator> valuesAsList() {
        return Arrays.asList(values());
    }

    public ArcOperator getOperator() {
        return op;
    }

    public String getLabel() {
        return label;
    }

}
