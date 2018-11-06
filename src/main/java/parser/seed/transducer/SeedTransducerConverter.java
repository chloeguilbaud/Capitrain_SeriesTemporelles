package parser.seed.transducer;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.seed.transducer.*;
import parser.seed.transducer.model.SeedTransducerPOJO;
import parser.seed.transducer.model.SeedTransducerParsingResult;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Optional;

import static parser.seed.transducer.model.SeedTransducerJSONElements.*;

public class SeedTransducerConverter {

    public static SeedTransducerParsingResult convert(File jsonFile) throws IOException {

        ObjectMapper mapper = confMapper();
        SeedTransducerPOJO pojo = mapper.readValue(jsonFile, SeedTransducerPOJO.class);

        // TODO - unknown values
        // TODO - id pojo contains null element - error
        // TODO - Mapper configuration and testing

        // Seed Transducer initialisation
        SeedTransducer seed = new SeedTransducer(pojo.getName());

        // Adding states to seed transducer
        for (String st : pojo.getStates()) {
            seed.addState(new State(st));
        }

        // Setting initial state
        setInitialState(pojo, seed);

        // Converting and checking arcs
        checkAndConvertArcs(pojo, seed);

        return new SeedTransducerParsingResult(seed, null);

    }

    private static ObjectMapper confMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    private static void setInitialState(SeedTransducerPOJO pojo, SeedTransducer seed) {
        Optional<State> initStOp = seed.getStateFromName(pojo.getInit_state());
        if(initStOp.isPresent()) {
            seed.setInitState(initStOp.get());
        } else {
            // TODO error
        }
    }

    private static void checkAndConvertArcs(SeedTransducerPOJO pojo, SeedTransducer seed) {
        for(LinkedHashMap mp : pojo.getArcs()) {
            if (mp.values().size() != 4) {
                // TODO - error
            } else {

                Arc arc = new Arc();

                setArcFromState(seed, mp, arc);
                setArcToState(seed, mp, arc);
                setArcOperatorState(mp, arc);
                setArcSemanticLetter(mp, arc);

                seed.addArc(arc);

            }
        }
    }

    private static void setArcSemanticLetter(LinkedHashMap mp, Arc arc) {
        String letter = (String) mp.get(SEED_TEMPLATE_ARC_LETTER.getLabel());
        Optional<SemanticLetter> arcLetterOpt = SemanticLetter.fromLabel(letter);
        if(arcLetterOpt.isPresent()) {
            arc.setSemanticLetter(arcLetterOpt.get());
        } else {
            // TODO - Missing value error
        }

    }

    private static void setArcOperatorState(LinkedHashMap mp, Arc arc) {
        String op = (String) mp.get(SEED_TEMPLATE_ARC_OPERATOR.getLabel());
        Optional<Operator> arcOpOpt = Operator.fromLabel(op);
        if(arcOpOpt.isPresent()) {
            arc.setOperator(arcOpOpt.get());
        } else {
            // TODO - Missing value error
        }
    }

    private static void setArcFromState(SeedTransducer seed, LinkedHashMap mp, Arc arc) {
        String from = (String) mp.get(SEED_TEMPLATE_ARC_FROM.getLabel());
        Optional<State> fromStateOpt = seed.getStateFromName(from);
        if(fromStateOpt.isPresent()) {
            arc.setFrom(fromStateOpt.get());
        } else {
            // TODO - Missing value error
        }
    }

    private static void setArcToState(SeedTransducer seed, LinkedHashMap mp, Arc arc) {
        String to = (String) mp.get(SEED_TEMPLATE_ARC_TO.getLabel());
        Optional<State> toStateOpt = seed.getStateFromName(to);
        if(toStateOpt.isPresent()) {
            arc.setTo(toStateOpt.get());
        } else {
            // TODO - Missing value error
        }
    }

}
