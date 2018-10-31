package model.seed.transducer;

import java.util.Arrays;
import java.util.List;

public enum Comparator {

    EQ("Equal"),
    LT("Littler than"),
    GT("Greater than"),
    LEQ("Littler than or equal"),
    GEQ("Greater than or equal");

    private String label;

    Comparator(String lab) {
        this.label = lab;
    }

    public static Comparator fromLabel(String lab) {
        return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
    }

    public static List<Comparator> valuesAsList() {
        return Arrays.asList(values());
    }

    public String getLabel() {
        return label;
    }

}
