package parser.seed.transducer.error.manager;

public enum SeedTransducerParsingErrorType {

    UNKNOWN_ERROR("Erreur de parsing iconnue"),
    UNRECOGNIZED_PROPERTY_IN_JSON;

    private String label;

    SeedTransducerParsingErrorType(String lab) {
        this.label = lab;
    }

    SeedTransducerParsingErrorType() { label = ""; }

    public String getLabel() {
        return label;
    }
}
