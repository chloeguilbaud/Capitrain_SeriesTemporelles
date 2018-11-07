package parser.seed.transducer.error.manager;

import parser.seed.transducer.json.SeedTransducerJSONElements;

/**
 * Seed transducer parsing error type enumeration.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public enum SeedTransducerParsingErrorType {

    UNKNOWN_ERROR("Erreur de parsing iconnue"),
    UNRECOGNIZED_PROPERTY("Unexpected JSON element in file"),
    JSON_MAPPING_EXCEPTION("One of the JSON element has not the right type"),
    FILE_NOT_FOUND("File not found"),

    MISSING_PROPERTY_IN_SEED_TRANSDUCER("Expecting seed transducer element but missing in JSON file"),
    MISSING_PROPERTY_IN_ARC("Expecting elements in arc field : "
            + SeedTransducerJSONElements.SEED_TEMPLATE_ARC_FROM + ", " + SeedTransducerJSONElements.SEED_TEMPLATE_ARC_TO + ", "
            + SeedTransducerJSONElements.SEED_TEMPLATE_ARC_OPERATOR + ", " + SeedTransducerJSONElements.SEED_TEMPLATE_ARC_LETTER),

    INVALID_INIT_STATE("The given init state for the transducer is not registered in the state list"),
    INVALID_FROM_STATE_IN_ARC("The given state \"from\" of an arc is not registered in the state list"),
    INVALID_TO_STATE_IN_ARC("The given state \"to\" of an arc is not registered in the state list"),

    INVALID_ARC_SEMANTIC_LETTER("The given semantic letter in arc is invalid"),
    INVALID_ARC_OPERATOR("The given operator in arc is invalid");

    private String label;

    SeedTransducerParsingErrorType(String lab) {
        this.label = lab;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

}
