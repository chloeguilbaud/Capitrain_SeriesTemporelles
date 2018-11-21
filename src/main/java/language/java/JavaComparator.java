package language.java;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import model.seed.transducer.ArcOperator;

/**
 * Java generation of a Comparator
 * (eg. equals, less than, greater than, less or equals, greater or equals)
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public enum JavaComparator {

    // Equals
    EQ("==", ArcOperator.EQ),
    // Less than
    LT("<", ArcOperator.LT),
    // Greater than
    GT(">", ArcOperator.GT),
    // Less or equals
    LEQ("<=", ArcOperator.LEQ),
    // Greater or equals
    GEQ(">=", ArcOperator.GEQ);

    /**
     * Label of the operator
     */
    private String label;
    /**
     * Related model operator
     */
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
