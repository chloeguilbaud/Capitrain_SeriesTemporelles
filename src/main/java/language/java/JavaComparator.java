package language.java;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import model.seed.transducer.Operator;

public enum JavaComparator {

    EQ("==", Operator.EQ),
    LT("<", Operator.LT),
    GT(">", Operator.GT),
    LEQ("<=", Operator.LEQ),
    GEQ(">=", Operator.GEQ);

    private String label;
    private Operator op;

    JavaComparator(String lab, Operator op) {
        this.label = lab;
        this.op = op;
    }

    public static Optional<JavaComparator> fromLabel(String lab) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null));
    }

    public static Optional<JavaComparator> fromOperator(Operator op) {
        return Optional.ofNullable(valuesAsList().stream().filter(m -> m.getOperator().equals(op)).findAny().orElse(null));
    }

    public static List<JavaComparator> valuesAsList() {
        return Arrays.asList(values());
    }

    public Operator getOperator() {
        return op;
    }

    public String getLabel() {
        return label;
    }

}
