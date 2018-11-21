package parser.seed.transducer.json;

import java.util.Arrays;
import java.util.List;

/**
 * Seed transducer enumeration listing the seed transducer JSON file's elements.
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public enum SeedTransducerJSONElements {

    SEED_TEMPLATE("seed_template"),
    SEED_TEMPLATE_NAME("name"),
    SEED_TEMPLATE_INIT_STATE("init_state"),
    SEED_TEMPLATE_STATES("states"),
    SEED_TEMPLATE_ARCS("arcs"),

    SEED_TEMPLATE_ARC_FROM("from"),
    SEED_TEMPLATE_ARC_TO("to"),
    SEED_TEMPLATE_ARC_OPERATOR("operator"),
    SEED_TEMPLATE_ARC_LETTER("letter");

    private String label;

    SeedTransducerJSONElements(String lab) {
        this.label = lab;
    }

    public static SeedTransducerJSONElements fromLabel(String lab) {
        return valuesAsList().stream().filter(m -> m.getLabel().equalsIgnoreCase(lab)).findAny().orElse(null);
    }

    public static List<SeedTransducerJSONElements> valuesAsList() {
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
