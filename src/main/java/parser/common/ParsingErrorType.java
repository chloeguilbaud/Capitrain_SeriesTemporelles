package parser.common;


public enum ParsingErrorType {

    UNKNOWN_ERROR("Erreur de parsing iconnue"),
    UNRECOGNIZED_PROPERTY("Unexpected JSON element in file"),
    JSON_MAPPING_EXCEPTION("One of the JSON element has not the right type"),
    FILE_NOT_FOUND("File not found");

    private String label;

    ParsingErrorType(String lab) {
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
