package parser.seed.transducer;

import utils.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parser.seed.transducer.errors.SeedTransducerParsingErrorType;
import parser.seed.transducer.process.SeedTransducerParsingResult;
import parser.seed.transducer.process.SeedTransducerConverter;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SeedTransducerConverterErrorManagerParameterizedTest {

    private final String file;
    private final List<SeedTransducerParsingErrorType> errorTypes;
    private final List<String> errorMsgContents;
    private int errorAmount;


    public SeedTransducerConverterErrorManagerParameterizedTest(String file, List<SeedTransducerParsingErrorType> errorTypes, List<String> errorMsgContents, int errorAmount) {
        this.file = file;
        this.errorTypes = errorTypes;
        this.errorMsgContents = errorMsgContents;
        this.errorAmount = errorAmount;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[] {
                        "convertTest_unknownElement.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.UNRECOGNIZED_PROPERTY),
                        Collections.singletonList("Unrecognized field \"je suis un intrus\""),
                        1
                },
                new Object[] {
                        "convertTest_missingElementInSeedTransducer.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER),
                        Collections.singletonList("Expecting seed transducer element \"init_state\" but missing in JSON file"),
                        1
                },
                new Object[] {
                        "convertTest_missingSTName.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER),
                        Collections.singletonList("\"name\""),
                        1
                },
                new Object[] {
                        "convertTest_missingSTBefore.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.MISSING_PROPERTY_BEFORE),
                        Collections.singletonList("before"),
                        1
                },
                new Object[] {
                        "convertTest_missingSTAfter.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.MISSING_PROPERTY_AFTER),
                        Collections.singletonList("after"),
                        1
                },
                new Object[] {
                        "convertTest_missingSTInitState.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER),
                        Collections.singletonList("init_state"),
                        1
                },
                new Object[] {
                        "convertTest_initStateNotInStates.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.INVALID_INIT_STATE),
                        Collections.singletonList("Init state: a\nStates: [s, r, t, u, v]"),
                        1
                },
                new Object[] {
                        "convertTest_arcFromStateNotInStates.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC),
                        Collections.singletonList("Arc: {from=a, to=s, operator=leq, letter=out}\n" +
                                "Seed transducer states: [s, r, t, u, v]"),
                        1
                },
                new Object[] {
                        "convertTest_arcToStateNotInStates.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC),
                        Collections.singletonList("Arc: {from=t, to=b, operator=lt, letter=maybe(before)}\n" +
                                "Seed transducer states: [s, r, t, u, v]"),
                        1
                },
                new Object[] {
                        "convertTest_arcInvalidSemanticLetter.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.INVALID_ARC_SEMANTIC_LETTER),
                        Collections.singletonList("Arc: {from=r, to=s, operator=leq, letter=hello world}\n" +
                                "Valid semantic letters: [found, found(end), maybe(before), maybe(after), out(reset), in, out(after), out]"),
                        1
                },
                new Object[] {
                        "convertTest_arcInvalidOperator.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.INVALID_ARC_OPERATOR),
                        Collections.singletonList("Arc: {from=t, to=u, operator=op, letter=maybe(before)}\n" +
                                "Valid operators: [eq, lt, gt, leq, geq]"),
                        1
                },
                new Object[] {
                        "convertTest_arcToAndFromStateNotInStates.json",
                        Arrays.asList(SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC,
                                SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC),
                        Arrays.asList("Arc: {from=a, to=b, operator=gt, letter=maybe(before)}\n" +
                                "Seed transducer states: [s, r, t, u, v]",
                                "The given state \"to\" of an arc is not registered in the state list\n" +
                                        "Arc: {from=a, to=b, operator=gt, letter=maybe(before)}\n" +
                                        "Seed transducer states: [s, r, t, u, v]"),
                        2
                },
                new Object[] {
                        "convertTest_missingArcs.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER),
                        Collections.singletonList("\"arcs\""),
                        1
                },
                new Object[] {
                        "convertTest_missingArcsAndStates.json",
                        Arrays.asList(SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                                SeedTransducerParsingErrorType.INVALID_INIT_STATE,
                                SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER),
                        Arrays.asList("\"states\"",
                                "Init state: s\nStates: null",
                                "\"arcs\""),
                        3
                },
                new Object[] {
                        "convertTest_elementWrongType.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.JSON_MAPPING_EXCEPTION),
                        Collections.singletonList("\"init_state\""),
                        1
                },
                new Object[] {
                        "convertTest_fileNotFound.json",
                        Collections.singletonList(SeedTransducerParsingErrorType.FILE_NOT_FOUND),
                        Collections.singletonList("Le fichier spécifié est introuvable"),
                        1
                }
        );
    }


    @Test
    public void checkHasError() {

        System.out.println("************** " + file + " **************");

        File jsonFile = new File(TestConfiguration.TEST_FILE_PATH_SEED_TRANSDUCER_PARSER.getValue() + file);
        SeedTransducerParsingResult res = SeedTransducerConverter.convert(jsonFile);

        System.out.println(res);
        assertFalse("No parsing because errors", res.getResult().isPresent());
        assertTrue("Parsing KO so errors", res.hasErrors());
        assertEquals("Error amount checking", this.errorAmount, res.getParsingErrors().size());

        for(int i = 0; i < errorTypes.size(); i++) {
            assertEquals("Error checking n°" + i, errorTypes.get(i), res.getParsingErrors().get(i).getErrorType());
            assertTrue("Error message n°" + i, res.getParsingErrors().get(i).getErrorMsg().contains(errorMsgContents.get(i)));
            assertTrue("Error message n°" + i, !res.getParsingErrors().get(i).getErrorMsg().contains("%s"));
        }

    }



}
