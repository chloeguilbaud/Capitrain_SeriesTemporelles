package parser.decoration.table.errors;

import model.seed.transducer.ArcSemanticLetter;

/**
 * Decoration table parsing error type enumeration.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public enum DecorationTableParsingErrorType {

    UNKNOWN_ERROR("Unknown JSON parsing error\n%s"),
    UNRECOGNIZED_PROPERTY("Unexpected JSON element in file\n%s"),
    JSON_MAPPING_EXCEPTION("One of the JSON element has not the right type\n%s"),
    FILE_NOT_FOUND("File not found\n%s"),

    INITIALISATION_VALUE_FUNCTION_MISSING_NAME("Function missing name in %s n°%s"),
    FUNCTION_UNEXPECTED_PARAMETER_IN_INITIALISATION("Functions in register or returns should not have parameters, in function %s"),

    INITIALISATION_REGISTER_VALUE_VARIABLE_UNEXPECTED_INDEX("Declaration in initialisation section should not have index field for variable %s"),
    INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME("No name given to variable in register n°%s"),
    MISSING_REGISTER_NAME("Expected register name in register n°%s"),
    MISSING_REGISTER_VALUE("Expected register value in register n°%s"),
    BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE("Both function and variable declaration in register value n°%s"),

    INITIALISATION_RETURN_VARIABLE_MISSING_NAME("No name given in return n°%s"),
    INITIALISATION_RETURN_VARIABLE_MISSING_INDEX("No index given in return n°%s for variable %s"),
    INITIALISATION_RETURN_VARIABLE_MISSING_VALUE("No value given in return n°%s"),
    BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE("Both function and variable declaration in return statement value n°%s"),

    INSTRUCTION_MISSING_SEMANTIC_LETTER("Semantic letter missing in table element n°%s"),
    INSTRUCTION_INVALID_SEMANTIC_LETTER("Invalid given semantic letter in table line n°%s" +
            "\n- Expected: " + ArcSemanticLetter.valuesAsList() +
            "\n- Actual: %s"),
    BOTH_FUNCTION_AND_VARIABLE_IN_VALUE("Both function and variable declaration in value in %s for semantic letter \"%s\""),

    FUNCTION_NAME_WHEN_ERROR("Error in function JSON definition"),
    FUNCTION_MISSING_NAME("Function element in JSON, missing name for semantic letter \"%s\" in %s"),
    FUNCTION_INVALID_PARAMETER_TYPE("Invalid parameter type given to function %s in semantic letter \"%s\" in %s"),
    FUNCTION_PARAMETER_MISSING_VALUE_IN_OPERATION("A parameter given to function %s is missing a value on the side of an operation (-, +, / or x) " +
            "for semantic letter \"%s\" (given parameter: %s)"),

    VARIABLE_NAME_WHEN_ERROR("Error in variable JSON definition"),
    VARIABLE_MISSING_NAME("Missing \"name\" in \"variable\" in JSON " +
            "in %s n°%s for semantic letter \"%s\""),
    VARIABLE_VALUE_MISSING_NAME("Missing \"value\" field in JSON in %s at semantic letter \"%s\""),
    VARIABLE_VALUE_MISSING("\"value\" field missing in JSON for semantic letter in %s for semantic letter \"%s\""),
    VARIABLE_INVALID_INDEX("Invalid index given for variable %s for semantic letter \"%s\" in %s");

    private String label;

    DecorationTableParsingErrorType(String lab) {
        this.label = lab;
    }

    public String getLabel() {
        return label;
    }


}
