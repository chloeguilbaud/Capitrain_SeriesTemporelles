package parser.decoration.table.errors;

public enum DecorationTableParsingErrorType {

    // TODO - utliser {} pour valeur msg à compléter

    UNKNOWN_ERROR("Unknown JSON parsing error"),
    UNRECOGNIZED_PROPERTY("Unexpected JSON element in file"),
    JSON_MAPPING_EXCEPTION("One of the JSON element has not the right type"),
    FILE_NOT_FOUND("File not found"),

    INITIALISATION_REGISTER_VARIABLE_UNEXPECTED_INDEX("Declaration in initialisation section should not have index field for variable"),
    INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME("No name given to variable in register n°"),

    INITIALISATION_RETURN_VALUE_VARIABLE_MISSING_NAME("No name given to variable in return n°"),
    INITIALISATION_RETURN_VARIABLE_MISSING_INDEX("No index given to variable in return n°"),

    MISSING_REGISTER_NAME("Expected register name in register n°"),
    MISSING_REGISTER_VALUE("Expected register value in register n°"),
    BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE("Both function and variable in register value n°"),
    FUNCTION_UNEXPECTED_PARAMETTER_IN_INITIALISATION("Functions in register or returns should not have parameters, in function"),

    VARIABLE_NAME_WHEN_ERROR("Error in variable JSON definition"),

    FUNCTION_MISSING_NAME("Function element in JSON, missing name"),
    FUNCTION_NAME_WHEN_ERROR("Error in function JSON definition"),
    FUNCTION_INVALID_PARAMETER_TYPE("Invalid parameter type given to function"),

    MISSING_RETURN_NAME("Return element in JSON, missing name, in return n°"),
    MISSING_RETURN_INDEX("Return element in JSON, missing index, in return n°"),
    MISSING_RETURN_VALUE("Expected return value in return n°"),
    BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE("Both function and variable in return statement value n°"),

    INSTRUCTION_MISSING_SEMANTIC_LETTER("Semantic letter missing"),
    INSTRUCTION_INVALID_SEMANTIC_LETTER("Invalid given semantic letter"),

    VARIABLE_VALUE_MISSING("\"value\" field missing in JSON for semantic letter"),
    VARIABLE_VALUE_MISSING_NAME("Missing \"value\" field in JSON"),
    VARIABLE_MISSING_NAME("Missing \"name\" in \"variable\" in JSON"),
    VARIABLE_VALUE_UNEXPECTED_INDEX("Index is not allowed for this variable value"),
    VARIABLE_INVALID_INDEX("Invalid index given for variable");

    private String label;

    DecorationTableParsingErrorType(String lab) {
        this.label = lab;
    }

    public String getLabel() {
        return label;
    }


}
