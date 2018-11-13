package parser.decoration.table.errors;

public enum DecorationTableParsingErrorType {

    UNKNOWN_ERROR("Erreur de parsing iconnue"),
    UNRECOGNIZED_PROPERTY("Unexpected JSON element in file"),
    JSON_MAPPING_EXCEPTION("One of the JSON element has not the right type"),
    FILE_NOT_FOUND("File not found"),

    INITIALISATION_VARIABLE_UNEXPECTED_INDEX("Declaration in initialisation section should not have index field for variable"),

    MISSING_REGISTER_NAME("Expected register name in register n°"),
    MISSING_REGISTER_VALUE("Expected register value in register n°"),
    BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE("Both function and variable in register value n°"),

    VARIABLE_NAME_WHEN_MISSING("Error in variable JSON definition"),

    FUNCTION_MISSING_NAME("Function element in JSON, missing name"),
    FUNCTION_NAME_WHEN_MISSING("Error in function JSON definition"),
    FUNCTION_INVALID_PARAMETER_TYPE("Invalid parameter type given to function"),

    MISSING_RETURN_NAME("Return element in JSON, missing name, in return n°"),
    MISSING_RETURN_INDEX("Return element in JSON, missing index, in return n°"),
    MISSING_RETURN_VALUE("Expected return value in return n°");

    private String label;

    DecorationTableParsingErrorType(String lab) {
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
