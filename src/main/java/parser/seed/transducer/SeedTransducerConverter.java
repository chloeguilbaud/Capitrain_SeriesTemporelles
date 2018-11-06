package parser.seed.transducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.seed.transducer.*;
import parser.seed.transducer.error.manager.SeedTransducerParsingError;
import parser.seed.transducer.error.manager.SeedTransducerParsingErrorType;
import parser.seed.transducer.model.SeedTransducerPOJO;
import parser.seed.transducer.model.SeedTransducerParsingResult;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Optional;

import static parser.seed.transducer.model.SeedTransducerJSONElements.*;

public class SeedTransducerConverter {

    public static SeedTransducerParsingResult convert(File jsonFile) throws IOException {

        SeedTransducerParsingResult res = new SeedTransducerParsingResult();
        ObjectMapper mapper = new ObjectMapper();
        try {
            SeedTransducerPOJO pojo = mapper
                    .readValue(jsonFile, SeedTransducerPOJO.class);
            return process(pojo, res);
        } catch (UnrecognizedPropertyException ex) {
            SeedTransducerParsingError err = new SeedTransducerParsingError(
                    SeedTransducerParsingErrorType.UNRECOGNIZED_PROPERTY,
                    ex.getMessage());
            res.addParsingError(err);
        }

        // TODO - id pojo contains null element - error

        // TODO - unfound file
        // TODO - mussing element from seed transducer


        // TODO - res.addParsingError(SeedTransducerParsingErrorType.UNKNOWN_ERROR);
        return res;
    }

    private static SeedTransducerParsingResult process(SeedTransducerPOJO pojo, SeedTransducerParsingResult res) {

        // Seed Transducer initialisation
        String name = pojo.getName();
        if(name == null) {
            manageError(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER.getLabel() + "\"name\"");
        }

        SeedTransducer seed = new SeedTransducer(name);

        // Adding states to seed transducer
        for (String st : pojo.getStates()) {
            seed.addState(new State(st));
        }

        // Setting initial state
        setInitialState(pojo, seed, res);

        // Converting and checking arcs
        checkAndConvertArcs(pojo, seed, res);

        res = new SeedTransducerParsingResult();
        res.setSeedTransducer(seed);

        return res;
    }

    private static void manageError(SeedTransducerParsingResult res, SeedTransducerParsingErrorType err, String msg) {
        res.addParsingError(new SeedTransducerParsingError(err, msg));
    }


    private static void setInitialState(SeedTransducerPOJO pojo, SeedTransducer seed, SeedTransducerParsingResult res) {
        Optional<State> initStOp = seed.getStateFromName(pojo.getInit_state());
        if(initStOp.isPresent()) {
            seed.setInitState(initStOp.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_INIT_STATE,
                    SeedTransducerParsingErrorType.INVALID_INIT_STATE.getLabel()
                            + "\nInit state: " + pojo.getInit_state()
                            + "\nStates: " + pojo.getStates());
        }
    }

    private static void checkAndConvertArcs(SeedTransducerPOJO pojo, SeedTransducer seed, SeedTransducerParsingResult res) {
        for(LinkedHashMap mp : pojo.getArcs()) {
            if (mp.values().size() != 4) {
                manageError(res, SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_ARC,
                        SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_ARC
                                + "\nActual: " + mp.values());

            } else {

                Arc arc = new Arc();

                setArcFromState(seed, mp, arc, res);
                setArcToState(seed, mp, arc, res);
                setArcOperatorState(mp, arc, res);
                setArcSemanticLetter(mp, arc, res);

                seed.addArc(arc);

            }
        }
    }

    private static void setArcSemanticLetter(LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res) {
        String letter = (String) mp.get(SEED_TEMPLATE_ARC_LETTER.getLabel());
        Optional<SemanticLetter> arcLetterOpt = SemanticLetter.fromLabel(letter);
        if(arcLetterOpt.isPresent()) {
            arc.setSemanticLetter(arcLetterOpt.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_ARC_SEMANTIC_LETTER,
                    SeedTransducerParsingErrorType.INVALID_ARC_SEMANTIC_LETTER
                            + "Arc: " + arc
                            + "Valid semantic letters: " + SemanticLetter.valuesAsList());
        }

    }

    private static void setArcOperatorState(LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res) {
        String op = (String) mp.get(SEED_TEMPLATE_ARC_OPERATOR.getLabel());
        Optional<Operator> arcOpOpt = Operator.fromLabel(op);
        if(arcOpOpt.isPresent()) {
            arc.setOperator(arcOpOpt.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_ARC_OPERATOR,
                    SeedTransducerParsingErrorType.INVALID_ARC_OPERATOR
                            + "Arc: " + arc
                            + "Valid operators: " + SemanticLetter.valuesAsList());
        }
    }

    private static void setArcFromState(SeedTransducer seed, LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res) {
        String from = (String) mp.get(SEED_TEMPLATE_ARC_FROM.getLabel());
        Optional<State> fromStateOpt = seed.getStateFromName(from);
        if(fromStateOpt.isPresent()) {
            arc.setFrom(fromStateOpt.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC,
                    SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC
                            + "Arc: " + arc
                            + "Seed transducer states: " + SemanticLetter.valuesAsList());
        }
    }

    private static void setArcToState(SeedTransducer seed, LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res) {
        String to = (String) mp.get(SEED_TEMPLATE_ARC_TO.getLabel());
        Optional<State> toStateOpt = seed.getStateFromName(to);
        if(toStateOpt.isPresent()) {
            arc.setTo(toStateOpt.get());
        } else {
            manageError(res, SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC,
                    SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC
                            + "Arc: " + arc
                            + "Seed transducer states: " + SemanticLetter.valuesAsList());
        }
    }

}
