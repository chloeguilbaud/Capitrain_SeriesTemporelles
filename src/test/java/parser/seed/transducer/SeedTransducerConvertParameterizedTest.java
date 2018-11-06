package parser.seed.transducer;

import conf.TestConfiguration;
import model.seed.transducer.SeedTransducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import parser.seed.transducer.error.manager.SeedTransducerParsingErrorType;
import parser.seed.transducer.model.SeedTransducerParsingResult;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SeedTransducerConvertParameterizedTest {

    private final String file;
    private final List<SeedTransducerParsingErrorType> errorTypes;
    private final List<String> errorMsgContents;


    public SeedTransducerConvertParameterizedTest(String file, List<SeedTransducerParsingErrorType> errorTypes, List<String> errorMsgContents) {
        this.file = file;
        this.errorTypes = errorTypes;
        this.errorMsgContents = errorMsgContents;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(
                new Object[] {
                        "convertTest_unknownElement.json",
                        Arrays.asList(SeedTransducerParsingErrorType.UNRECOGNIZED_PROPERTY),
                        Arrays.asList("Unrecognized field \"je suis un intrus\"")
                },
                new Object[] {
                        "convertTest_missingElement.json",
                        Arrays.asList(SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER),
                        Arrays.asList("Expecting \"init_state\"")
                },
                new Object[] {
                        "convertTest_missingSDName.json",
                        Arrays.asList(SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER),
                        Arrays.asList("\"name\"")
                },
                new Object[] {
                        "convertTest_missingSTInitState.json",
                        SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                        "Init state: TODO \\nStates: TODO"
                },
                new Object[] {
                        "convertTest_initStateNotInStates.json",
                        Arrays.asList(SeedTransducerParsingErrorType.INVALID_INIT_STATE),
                        Arrays.asList("TODO")
                },
                new Object[] {
                        "convertTest_arcFromStateNotInStates.json",
                        Arrays.asList(SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC),
                        Arrays.asList("TODO")
                },
                new Object[] {
                        "convertTest_arcToStateNotInStates.json",
                        Arrays.asList(SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC),
                        Arrays.asList("TODO")
                },
                new Object[] {
                        "convertTest_arcInvalidSemanticLetter.json",
                        Arrays.asList(SeedTransducerParsingErrorType.INVALID_ARC_SEMANTIC_LETTER),
                        Arrays.asList("TODO")
                },
                new Object[] {
                        "convertTest_arcInvalidOperator.json",
                        Arrays.asList(SeedTransducerParsingErrorType.INVALID_ARC_OPERATOR),
                        Arrays.asList("TODO")
                },
                new Object[] {
                        "convertTest_arcToAndFromStateNotInStates.json",
                        Arrays.asList(SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC,
                                SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC),
                        Arrays.asList("TODO")
                },
                new Object[] {
                        "convertTest_missingArcsAndStates.json",
                        Arrays.asList(SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                                SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER),
                        Arrays.asList("Init state: TODO \\nStates: TODO")
                }
        );
    }


    @Test
    public void checkHasError() throws IOException {

        File jsonFile = new File(TestConfiguration.TEST_FILE_PATH_SEED_TRANSDUCER_PARSER.getValue() + file);
        SeedTransducerParsingResult res = SeedTransducerConverter.convert(jsonFile);

        System.out.println(res.getSeedTransducer());

        assertFalse("No parsing because errors", res.getSeedTransducer().isPresent());
        assertTrue("Parsing KO so errors", res.hasErrors());
        assertEquals("Error amount checking", this.errorTypes.size(), res.getParsingErrors().size());

        for(int i = 0; i < errorTypes.size(); i++) {
            assertEquals("Error checking n°" + i, errorTypes.get(i), res.getParsingErrors().get(i).getErrorType());
            assertTrue("Error message n°" + i, res.getParsingErrors().get(i).getErrorMsg().contains(errorMsgContents.get(i)));
        }

    }



}
