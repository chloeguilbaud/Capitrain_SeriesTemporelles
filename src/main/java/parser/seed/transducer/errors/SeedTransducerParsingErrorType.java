package parser.seed.transducer.errors;

import parser.seed.transducer.json.SeedTransducerJSONElements;

/**
 * Seed transducer parsing error type enumeration.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public enum SeedTransducerParsingErrorType {

    UNKNOWN_ERROR("Unknown JSON parsing error \n%s"),
    UNRECOGNIZED_PROPERTY("Unexpected JSON element in file \n%s"),
    JSON_MAPPING_EXCEPTION("One of the JSON element has not the right type \n%s"),
    FILE_NOT_FOUND("File not found \n%s"),

    MISSING_PROPERTY_IN_SEED_TRANSDUCER("Expecting seed transducer element %s but missing in JSON file"),
    MISSING_PROPERTY_IN_ARC("Expecting elements in arc field : "
            + SeedTransducerJSONElements.SEED_TEMPLATE_ARC_FROM + ", " + SeedTransducerJSONElements.SEED_TEMPLATE_ARC_TO + ", "
            + SeedTransducerJSONElements.SEED_TEMPLATE_ARC_OPERATOR + ", " + SeedTransducerJSONElements.SEED_TEMPLATE_ARC_LETTER
            + "\nActual: %s"),
    MISSING_PROPERTY_AFTER("Missing seed transducer after field in JSON in %s"),
    MISSING_PROPERTY_BEFORE("Missing seed transducer before field in JSON in %s"),

    INVALID_INIT_STATE("The given init state for the transducer is not registered in the state list" +
            "\nInit state: %s" +
            "\nStates: %s"),
    INVALID_FROM_STATE_IN_ARC("The given state \"from\" of an arc is not registered in the state list" +
            "\nArc: %s" +
            "\nSeed transducer states: %s"),
    INVALID_TO_STATE_IN_ARC("The given state \"to\" of an arc is not registered in the state list" +
            "\nArc: %s" +
            "\nSeed transducer states: %s"),

    INVALID_ARC_SEMANTIC_LETTER("The given semantic letter in arc is invalid" +
            "\nArc: %s" +
            "\nValid semantic letters: %s"),
    INVALID_ARC_OPERATOR("The given operator in arc is invalid" +
            "\nArc: %s" +
            "\nValid operators: %s");

    private String label;

    SeedTransducerParsingErrorType(String lab) {
        this.label = lab;
    }

    public String getLabel() {
        return label;
    }


}
