package parser.seed.transducer;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.seed.transducer.*;
import parser.seed.transducer.error.manager.SeedTransducerParsingError;
import parser.seed.transducer.error.manager.SeedTransducerParsingErrorType;
import parser.seed.transducer.model.SeedTransducerPOJO;
import parser.seed.transducer.model.SeedTransducerParsingResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Optional;

import static parser.seed.transducer.model.SeedTransducerJSONElements.*;

public class SeedTransducerConverter {

    public static SeedTransducerParsingResult convert(File jsonFile) {

        SeedTransducerParsingResult res = new SeedTransducerParsingResult();
        ObjectMapper mapper = new ObjectMapper();
        try {
            SeedTransducerPOJO pojo = mapper
                    .readValue(jsonFile, SeedTransducerPOJO.class);
            return process(pojo, res);
        } catch (UnrecognizedPropertyException ex) {
            manageError(res,
                    SeedTransducerParsingErrorType.UNRECOGNIZED_PROPERTY,
                    ex.getMessage());
        } catch (JsonMappingException ex) {
            manageError(res,
                    SeedTransducerParsingErrorType.JSON_MAPPING_EXCEPTION,
                    ex.getMessage());
        } catch (FileNotFoundException ex) {
            manageError(res,
                    SeedTransducerParsingErrorType.FILE_NOT_FOUND,
                    ex.getMessage());
        } catch (IOException ex) {
            manageError(res,
                    SeedTransducerParsingErrorType.UNKNOWN_ERROR,
                    ex.getMessage());
        }

        return res;
    }

    private static SeedTransducerParsingResult process(SeedTransducerPOJO pojo, SeedTransducerParsingResult res) {

        // Seed Transducer initialisation
        String name = pojo.getName();
        if(name == null) {
            manageError(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER.getLabel() + " \"name\"");
        }

        SeedTransducer seed = new SeedTransducer(name);

        // Adding states to seed transducer
        addStatesToSeedTransducer(pojo, res, seed);

        // Setting initial state
        setInitialState(pojo, seed, res);

        // Converting and checking arcs
        checkAndConvertArcs(pojo, seed, res);

        // If their is some errors, then the seed transducer Optional object is set to empty
        if (res.hasErrors()) {
            res.removeSeedTransducer();
        } else {
            res.setSeedTransducer(seed);
        }

        return res;
    }

    private static void addStatesToSeedTransducer(SeedTransducerPOJO pojo, SeedTransducerParsingResult res, SeedTransducer seed) {
        if(pojo.getStates() == null) {
            manageError(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER.getLabel() + " \"states\"");
        } else {
            for (String st : pojo.getStates()) {
                seed.addState(new State(st));
            }
        }
    }

    private static void manageError(SeedTransducerParsingResult res, SeedTransducerParsingErrorType err, String msg) {
        res.addParsingError(new SeedTransducerParsingError(err, msg));
    }


    private static void setInitialState(SeedTransducerPOJO pojo, SeedTransducer seed, SeedTransducerParsingResult res) {
        String initState = pojo.getInit_state();
        if(initState == null) {
            manageError(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER.getLabel() + " \"init_state\"");
        } else {
            Optional<State> initStOp = seed.getStateFromName(initState);
            if (initStOp.isPresent()) {
                seed.setInitState(initStOp.get());
            } else {
                manageError(res, SeedTransducerParsingErrorType.INVALID_INIT_STATE,
                        SeedTransducerParsingErrorType.INVALID_INIT_STATE.getLabel()
                                + "\nInit state: " + pojo.getInit_state()
                                + "\nStates: " + pojo.getStates());
            }
        }
    }

    private static void checkAndConvertArcs(SeedTransducerPOJO pojo, SeedTransducer seed, SeedTransducerParsingResult res) {
        if(pojo.getArcs() == null) {
            manageError(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER.getLabel() + " \"arcs\"");
        } else {
            for (LinkedHashMap mp : pojo.getArcs()) {
                if (mp.values().size() != 4) {
                    manageError(res, SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_ARC,
                            SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_ARC
                                    + "\nActual: " + mp.values());

                } else {

                    // Creating and adding arc object
                    Arc arc = new Arc();

                    setArcFromState(seed, mp, arc, res, pojo);
                    setArcToState(seed, mp, arc, res, pojo);
                    setArcOperatorState(mp, arc, res);
                    setArcSemanticLetter(mp, arc, res);

                    seed.addArc(arc);

                }
            }
        }
    }

    private static void setArcSemanticLetter(LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res) {
        String letter = (String) mp.get(SEED_TEMPLATE_ARC_LETTER.getLabel());
        Optional<ArcSemanticLetter> arcLetterOpt = ArcSemanticLetter.fromLabel(letter);
        if(arcLetterOpt.isPresent()) {
            arc.setArcSemanticLetter(arcLetterOpt.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_ARC_SEMANTIC_LETTER,
                    SeedTransducerParsingErrorType.INVALID_ARC_SEMANTIC_LETTER
                            + "\nArc: " + mp
                            + "\nValid semantic letters: " + ArcSemanticLetter.valuesAsList());
        }

    }

    private static void setArcOperatorState(LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res) {
        String op = (String) mp.get(SEED_TEMPLATE_ARC_OPERATOR.getLabel());
        Optional<ArcOperator> arcOpOpt = ArcOperator.fromLabel(op);
        if(arcOpOpt.isPresent()) {
            arc.setArcOperator(arcOpOpt.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_ARC_OPERATOR,
                    SeedTransducerParsingErrorType.INVALID_ARC_OPERATOR
                            + "\nArc: " + mp
                            + "\nValid operators: " + ArcOperator.valuesAsList());
        }
    }

    private static void setArcFromState(SeedTransducer seed, LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res, SeedTransducerPOJO pojo) {
        String from = (String) mp.get(SEED_TEMPLATE_ARC_FROM.getLabel());
        Optional<State> fromStateOpt = seed.getStateFromName(from);
        if(fromStateOpt.isPresent()) {
            arc.setFrom(fromStateOpt.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC,
                    SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC
                            + "\nArc: " + mp
                            + "\nSeed transducer states: " + pojo.getStates());
        }
    }

    private static void setArcToState(SeedTransducer seed, LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res, SeedTransducerPOJO pojo) {
        String to = (String) mp.get(SEED_TEMPLATE_ARC_TO.getLabel());
        Optional<State> toStateOpt = seed.getStateFromName(to);
        if(toStateOpt.isPresent()) {
            arc.setTo(toStateOpt.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC,
                    SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC
                            + "\nArc: " + mp
                            + "\nSeed transducer states: " + pojo.getStates());
        }
    }

}
