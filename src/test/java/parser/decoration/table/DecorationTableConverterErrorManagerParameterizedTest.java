package parser.decoration.table;

import conf.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parser.decoration.table.errors.DecorationTableParsingErrorType;
import parser.decoration.table.process.DecorationTableConverter;
import parser.decoration.table.process.DecorationTableParsingResult;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class DecorationTableConverterErrorManagerParameterizedTest {


    private final String file;
    private final List<DecorationTableParsingErrorType> errorTypes;
    private final List<String> errorMsgContents;
    private int errorAmount;
    private boolean majorParsingErrors;


    public DecorationTableConverterErrorManagerParameterizedTest(String file, List<DecorationTableParsingErrorType> errorTypes,
                                                                 List<String> errorMsgContents, int errorAmount, boolean majorParsingErrors) {
        this.file = file;
        this.errorTypes = errorTypes;
        this.errorMsgContents = errorMsgContents;
        this.errorAmount = errorAmount;
        this.majorParsingErrors = majorParsingErrors;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[] {
                        "convertTest_unrecognizedProperty.json",
                        Collections.singletonList(DecorationTableParsingErrorType.UNRECOGNIZED_PROPERTY),
                        Collections.singletonList("Unrecognized field \"je suis un intrus\""),
                        1, true
                },
                new Object[] {
                        "convertTest_wrongDecorationTableNameType.json",
                        Collections.singletonList(DecorationTableParsingErrorType.JSON_MAPPING_EXCEPTION),
                        Collections.singletonList("line: 2, column: 11] (through reference chain: parser.decoration.table.model.DecorationTablePOJO[\"name\"]"),
                        1, true
                },
                new Object[] {
                        "convertTest_fileNotFound.json",
                        Collections.singletonList(DecorationTableParsingErrorType.FILE_NOT_FOUND),
                        Collections.singletonList("Le fichier spécifié est introuvable"),
                        1, true
                },
                new Object[] {
                        "convertTest_initialisationRegisterVariableUnexpectedIndex.json",
                        Collections.singletonList(DecorationTableParsingErrorType.INITIALISATION_REGISTER_VALUE_VARIABLE_UNEXPECTED_INDEX),
                        Collections.singletonList("Declaration in initialisation section should not have index field for variable id"),
                        1, false
                },
                new Object[] {
                        "convertTest_initialisationRegisterValueVariableMissingName.json",
                        Collections.singletonList(DecorationTableParsingErrorType.INITIALISATION_REGISTER_VALUE_VARIABLE_MISSING_NAME),
                        Collections.singletonList("variable in register n°1"),
                        1, false
                },
                new Object[] {
                        "convertTest_initialisationReturnValueVariableMissingName.json",
                        Collections.singletonList(DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_NAME),
                        Collections.singletonList("No name given in return n°1"),
                        1, false
                },
                new Object[] {
                        "convertTest_initialisationReturnVariableMissingValue.json",
                        Collections.singletonList(DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_VALUE),
                        Collections.singletonList("No value given in return n°1"),
                        1, false
                },
                new Object[] {
                        "convertTest_initialisationReturnVariableMissingIndex.json",
                        Collections.singletonList(DecorationTableParsingErrorType.INITIALISATION_RETURN_VARIABLE_MISSING_INDEX),
                        Collections.singletonList("No index given in return n°2 for variable e"),
                        1, false
                },
                new Object[] {
                        "convertTest_missingRegisterName.json",
                        Collections.singletonList(DecorationTableParsingErrorType.MISSING_REGISTER_NAME),
                        Collections.singletonList("Expected register name in register n°1"),
                        1, false
                },
                new Object[] {
                        "convertTest_missingRegisterValue.json",
                        Arrays.asList(DecorationTableParsingErrorType.MISSING_REGISTER_VALUE, DecorationTableParsingErrorType.MISSING_REGISTER_VALUE),
                        Arrays.asList("Expected register value in register n°1", "Expected register value in register n°2"),
                        2, false
                },
                new Object[] {
                        "convertTest_bothRegisterFunctionAndVariableInValue.json",
                        Collections.singletonList(DecorationTableParsingErrorType.BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE),
                        Collections.singletonList("Both function and variable declaration in register value n°2"),
                        1, false
                },
                new Object[] {
                        "convertTest_bothRegisterAndReturnFunctionAndVariableInValue.json",
                        Arrays.asList(
                                DecorationTableParsingErrorType.BOTH_REGISTER_FUNCTION_AND_VARIABLE_IN_VALUE,
                                DecorationTableParsingErrorType.BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE,
                                DecorationTableParsingErrorType.BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE),
                        Arrays.asList(
                                "Both function and variable declaration in register value n°1",
                                "Both function and variable declaration in return statement value n°1",
                                "Both function and variable declaration in return statement value n°2"),
                        3, false
                },
                new Object[] {
                        "convertTest_initialisationFunctionMissingName.json",
                        Arrays.asList(
                                DecorationTableParsingErrorType.INITIALISATION_VALUE_FUNCTION_MISSING_NAME,
                                DecorationTableParsingErrorType.INITIALISATION_VALUE_FUNCTION_MISSING_NAME,
                                DecorationTableParsingErrorType.INITIALISATION_VALUE_FUNCTION_MISSING_NAME),
                        Arrays.asList(
                                "Function missing name in REGISTER n°1",
                                "Function missing name in REGISTER n°2",
                                "Function missing name in RETURN n°1"),
                        3, false
                },
                new Object[] {
                        "convertTest_functionMissingName.json",
                        Collections.singletonList(DecorationTableParsingErrorType.FUNCTION_MISSING_NAME),
                        Collections.singletonList("Function element in JSON, missing name for semantic letter \"found(end)\" in UPDATE"),
                        1, false
                },
                new Object[] {
                        "convertTest_functionInvalidParameterType.json",
                        Collections.singletonList(DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE),
                        Collections.singletonList("Invalid parameter type given to function delta in semantic letter \"found(end)\" in GUARD"),
                        1, false
                },
                new Object[] {
                        "convertTest_bothReturnFunctionAndVariableInValue.json",
                        Collections.singletonList(DecorationTableParsingErrorType.BOTH_RETURN_FUNCTION_AND_VARIABLE_IN_VALUE),
                        Collections.singletonList("Both function and variable declaration in return statement value n°2"),
                        1, false
                },
                new Object[] {
                        "convertTest_bothFunctionAndVariableInValue.json",
                        Collections.singletonList(DecorationTableParsingErrorType.BOTH_FUNCTION_AND_VARIABLE_IN_VALUE),
                        Collections.singletonList("Both function and variable declaration in value in GUARD for semantic letter \"in\""),
                        1, false
                },
                new Object[] {
                        "convertTest_instructionMissingSemanticLetter.json",
                        Collections.singletonList(DecorationTableParsingErrorType.INSTRUCTION_MISSING_SEMANTIC_LETTER),
                        Collections.singletonList("Semantic letter missing in table element n°12"),
                        1, false
                },
                new Object[] {
                        "convertTest_instructionInvalidSemanticLetter.json",
                        Collections.singletonList(DecorationTableParsingErrorType.INSTRUCTION_INVALID_SEMANTIC_LETTER),
                        Collections.singletonList("Invalid given semantic letter in table line n°11" +
                                "\n- Expected: [found, found(end), maybe(before), maybe(after), out(reset), in, out(after), out]" +
                                "\n- Actual: invalidSemanticLetter"),
                        1, false
                },
                new Object[] {
                        "convertTest_variableValueMissing.json",
                        Collections.singletonList(DecorationTableParsingErrorType.VARIABLE_VALUE_MISSING),
                        Collections.singletonList("\"value\" field missing in JSON for semantic letter in UPDATE for semantic letter \"found(end)\""),
                        1, false
                },
                new Object[] {
                        "convertTest_variableValueMissingName.json",
                        Collections.singletonList(DecorationTableParsingErrorType.VARIABLE_VALUE_MISSING_NAME),
                        Collections.singletonList("Missing \"value\" field in JSON in GUARD at semantic letter \"in\""),
                        1, false // in GUARD 2
                },
                new Object[] {
                        "convertTest_variableMissingName.json",
                        Collections.singletonList(DecorationTableParsingErrorType.VARIABLE_MISSING_NAME),
                        Collections.singletonList("Missing \"name\" in \"variable\" in JSON in GUARD n°1 for semantic letter \"in\""),
                        1, false
                },
                new Object[] {
                        "convertTest_variableInvalidIndex.json",
                        Arrays.asList(
                                DecorationTableParsingErrorType.VARIABLE_INVALID_INDEX,
                                DecorationTableParsingErrorType.VARIABLE_INVALID_INDEX),
                        Arrays.asList(
                                "Invalid index given for variable f for semantic letter \"in\" in GUARD",
                                "Invalid index given for variable e for semantic letter \"in\" in GUARD"),
                        2, false
                },
                new Object[] {
                        "convertTest_functionInvalidParameterTypeFunction.json",
                        Collections.singletonList(DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE),
                        Collections.singletonList("Invalid parameter type given to function phi in semantic letter \"found(end)\" in GUARD"),
                        1, false
                },
                new Object[] {
                        "convertTest_functionParameterAsFunction.json",
                        Collections.singletonList(DecorationTableParsingErrorType.FUNCTION_INVALID_PARAMETER_TYPE),
                        Collections.singletonList("Invalid parameter type given to function delta in semantic letter \"maybe(after)\" in UPDATE"),
                        1, false
                },
                new Object[] {
                        "convertTest_functionParameterMissingValueInOperation.json",
                        Collections.singletonList(DecorationTableParsingErrorType.FUNCTION_PARAMETER_MISSING_VALUE_IN_OPERATION),
                        Collections.singletonList("A parameter given to function UPDATE is missing a value on the side of an operation (-, +, / or x) for semantic letter \"maybe(before)\" (given parameter: i+)"),
                        1, false
                }
        );
    }

    // TODO - given semantic letter has to be valid and not given before

    /**
     * FUNCTION_PARAMETER_MISSING_VALUE_IN_OPERATION
     * TODO
     * ,
     *                 new Object[] {
     *                         "convertTest_invalidVariableName_variableNameWhenError.json",
     *                         Collections.singletonList(DecorationTableParsingErrorType.VARIABLE_NAME_WHEN_ERROR),
     *                         Collections.singletonList("TODO"),
     *                         1
     *                 },
     *
     *
     *                 ,
     *                 new Object[] {
     *                         "convertTest_functionMissingName_functionNameError.json",
     *                         Collections.singletonList(DecorationTableParsingErrorType.FUNCTION_NAME_WHEN_ERROR),
     *                         Collections.singletonList("TODO"),
     *                         1
     *                 },
     */

    /*
    TODO - parsing empty si erreur
     */

    @Test
    public void checkHasError() {

        System.out.println("************** " + file + " **************");

        File jsonFile = new File(TestConfiguration.TEST_FILE_PATH_DECORATION_TABLE_PARSER.getValue() + file);
        DecorationTableParsingResult res = DecorationTableConverter.convert(jsonFile);

        System.out.println("Parsing result: " + res);
        assertEquals("No parsing because parse errors", !this.majorParsingErrors, res.getResult().isPresent());
        assertTrue("Parsing KO so errors", res.hasErrors());
        assertEquals("Error amount checking", this.errorAmount, res.getParsingErrors().size());

        for(int i = 0; i < errorTypes.size(); i++) {
            assertEquals("Error checking n°" + i, errorTypes.get(i), res.getParsingErrors().get(i).getErrorType());
            assertTrue("Error message n°" + i, res.getParsingErrors().get(i).getErrorMsg().contains(errorMsgContents.get(i)));
        }

    }

}
