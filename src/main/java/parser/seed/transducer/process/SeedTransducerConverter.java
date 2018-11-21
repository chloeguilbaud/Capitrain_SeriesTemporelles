package parser.seed.transducer.process;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import model.seed.transducer.*;
import parser.seed.transducer.errors.SeedTransducerParsingErrorType;
import parser.seed.transducer.model.SeedTransducerPOJO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Optional;

import static parser.seed.transducer.json.SeedTransducerJSONElements.*;
import static parser.seed.transducer.errors.SeedTransducerErrorHandler.handle;

/**
 * Enables Seed Transducer's transformation from JSON to POJO.
 * It is then transformed into a {@link SeedTransducerParsingResult} containing the {@link SeedTransducer}
 * or errors.
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public abstract class SeedTransducerConverter {

    /**
     * Transforms seed transducer JSON file into POJO before creating a {@link SeedTransducerParsingResult}.
     * If a parsing error occurs the result will not contain ant {@link SeedTransducer}. Errors are added to the result.
     * If the error is not a proper parsing error, processing errors can be notified.
     * @param jsonFile - path to the seed transducer JSON file representation
     * @return {@link SeedTransducerParsingResult} containing the {@link SeedTransducer} result or an empty {@link Optional}
     *         object and errors if the parsing did't go through
     */
    public static SeedTransducerParsingResult convert(File jsonFile) {

        SeedTransducerParsingResult res = new SeedTransducerParsingResult();
        ObjectMapper mapper = new ObjectMapper();
        try {
            SeedTransducerPOJO pojo = mapper
                    .readValue(jsonFile, SeedTransducerPOJO.class);
            return process(pojo, res);
        } catch (UnrecognizedPropertyException ex) {
            handle(res,
                    SeedTransducerParsingErrorType.UNRECOGNIZED_PROPERTY,
                    ex.getMessage());
        } catch (JsonMappingException ex) {
            handle(res,
                    SeedTransducerParsingErrorType.JSON_MAPPING_EXCEPTION,
                    ex.getMessage());
        } catch (FileNotFoundException ex) {
            handle(res,
                    SeedTransducerParsingErrorType.FILE_NOT_FOUND,
                    ex.getMessage());
        } catch (IOException ex) {
            handle(res,
                    SeedTransducerParsingErrorType.UNKNOWN_ERROR,
                    ex.getMessage());
        }

        return res;
    }

    /**
     * Process the parsing and converting.
     * @param pojo The mapped {@link SeedTransducerPOJO} related to the Seed Transducer JSON file.
     * @param res The {@link SeedTransducerParsingResult} parsing result
     * @return {@link SeedTransducerParsingResult} containing the {@link SeedTransducer} result or an empty {@link Optional}
     *      object and errors if the parsing did't go through
     */
    private static SeedTransducerParsingResult process(SeedTransducerPOJO pojo, SeedTransducerParsingResult res) {

        // Seed Transducer initialisation
        String name = pojo.getName();
        if(name == null) {
            handle(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER, "\"name\"");
        }

        // Setting before and after states
        int before = getBeforeInSeedTransducer(pojo, res);
        int after = getAfterInSeedTransducer(pojo, res);

        SeedTransducer seed = new SeedTransducer(name, before, after);

        // Adding states to seed transducer
        addStatesToSeedTransducer(pojo, res, seed);

        // Setting initial state
        setInitialState(pojo, seed, res);

        // Converting and checking arcs
        checkAndConvertArcs(pojo, seed, res);

        // If their are some errors, then the seed transducer Optional object is set to empty
        if (res.hasErrors()) {
            res.removeParsingResult();
        } else {
            res.setResult(seed);
        }

        return res;
    }

    /**
     * Gets the seed transducer's before value.
     * @param pojo The mapped {@link SeedTransducerPOJO} extracted from seed transducer JSON file
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @return Seed transducer before value
     */
    private static int getBeforeInSeedTransducer(SeedTransducerPOJO pojo, SeedTransducerParsingResult res) {
        Integer before = pojo.getBefore();
        if (before == null) {
            handle(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_BEFORE,
                     pojo.getName());
        } else {
            return before;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Gets the seed transducer's after value.
     * @param pojo The mapped {@link SeedTransducerPOJO} extracted from seed transducer JSON file
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @return Seed transducer before value
     */
    private static int getAfterInSeedTransducer(SeedTransducerPOJO pojo, SeedTransducerParsingResult res) {
        Integer after = pojo.getAfter();
        if (after == null) {
            handle(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_AFTER,
                    pojo.getName());
        } else {
            return after;
        }
        return Integer.MAX_VALUE;
    }

    /**
     * Adds the parsed seed transducer states to the result object.
     * Adds errors if need to the {@link SeedTransducerParsingResult}
     * @param pojo The mapped {@link SeedTransducerPOJO} extracted from seed transducer JSON file
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @param seed The {@link SeedTransducer} to produce (modified)
     */
    private static void addStatesToSeedTransducer(SeedTransducerPOJO pojo, SeedTransducerParsingResult res, SeedTransducer seed) {
        if(pojo.getStates() == null) {
            handle(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                    "\"states\"");
        } else {
            for (String st : pojo.getStates()) {
                seed.addState(new State(st));
            }
        }
    }

    /**
     * Sets the initial state given from the pojo into the {@link SeedTransducer}.
     * Adds errors if need to the {@link SeedTransducerParsingResult}.
     * @param pojo The mapped {@link SeedTransducerPOJO} extracted from seed transducer JSON file
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @param seed The {@link SeedTransducer} to produce (modified)
     */
    private static void setInitialState(SeedTransducerPOJO pojo, SeedTransducer seed, SeedTransducerParsingResult res) {
        String initState = pojo.getInit_state();
        // Checking if the initial state given in the JSON File
        if(initState == null) {
            handle(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                    "\"init_state\"");
        } else {
            Optional<State> initStOp = seed.getStateFromName(initState);
            if (initStOp.isPresent()) {
                seed.setInitState(initStOp.get());
            } else {
                handle(res, SeedTransducerParsingErrorType.INVALID_INIT_STATE,
                                pojo.getInit_state(), "" + pojo.getStates());
            }
        }
    }

    /**
     * Adds the arcs given from the pojo into the {@link SeedTransducer} if valid.
     * Adds errors if need to the {@link SeedTransducerParsingResult}.
     * @param pojo The mapped {@link SeedTransducerPOJO} extracted from seed transducer JSON file
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @param seed The {@link SeedTransducer} to produce (modified)
     */
    private static void checkAndConvertArcs(SeedTransducerPOJO pojo, SeedTransducer seed, SeedTransducerParsingResult res) {
        // Checking if arcs are given in the JSON File
        if(pojo.getArcs() == null) {
            handle(res,
                    SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_SEED_TRANSDUCER,
                    "\"arcs\"");
        } else {
            for (LinkedHashMap mp : pojo.getArcs()) {
                if (mp.values().size() != 4) {
                    handle(res, SeedTransducerParsingErrorType.MISSING_PROPERTY_IN_ARC,
                                    "" + mp.values());

                } else {

                    // Creating and adding arc object
                    Arc arc = new Arc();

                    setArcFromState(seed, mp, arc, res, pojo);
                    setArcToState(seed, mp, arc, res, pojo);
                    setArcOperator(mp, arc, res);
                    setArcSemanticLetter(mp, arc, res);

                    seed.addArc(arc);

                }
            }
        }
    }

    /**
     * Adds the {@link ArcSemanticLetter} given from the JSON arc representation into the given {@link Arc}.
     * Adds errors if need to the {@link SeedTransducerParsingResult}.
     * @param mp JSON arc representation
     * @param arc {@link Arc} object to be added (modified)
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     */
    private static void setArcSemanticLetter(LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res) {
        String letter = (String) mp.get(SEED_TEMPLATE_ARC_LETTER.getLabel());
        Optional<ArcSemanticLetter> arcLetterOpt = ArcSemanticLetter.fromLabel(letter);
        if(arcLetterOpt.isPresent()) {
            arc.setArcSemanticLetter(arcLetterOpt.get());
        } else {
            handle(res, SeedTransducerParsingErrorType.INVALID_ARC_SEMANTIC_LETTER,
                            mp.toString(), ArcSemanticLetter.valuesAsList().toString());
        }

    }

    /**
     * Adds the {@link ArcOperator} given from the JSON arc representation into the given {@link Arc}.
     * Adds errors if need to the {@link SeedTransducerParsingResult}.
     * @param mp JSON arc representation
     * @param arc {@link Arc} object to be added (modified)
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     */
    private static void setArcOperator(LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res) {
        String op = (String) mp.get(SEED_TEMPLATE_ARC_OPERATOR.getLabel());
        Optional<ArcOperator> arcOpOpt = ArcOperator.fromLabel(op);
        if(arcOpOpt.isPresent()) {
            arc.setArcOperator(arcOpOpt.get());
        } else {
            handle(res, SeedTransducerParsingErrorType.INVALID_ARC_OPERATOR,
                            mp.toString(), ArcOperator.valuesAsList().toString());
        }
    }

     /**
     * Adds the "from" {@link State} given from the JSON arc representation into the given {@link Arc}.
     * Adds errors if need to the {@link SeedTransducerParsingResult}.
     * @param seed {@link SeedTransducer} parse result object (modified)
     * @param mp JSON arc representation
     * @param arc {@link Arc} object to be added (modified)
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @param pojo The {@link SeedTransducerPOJO} mapped from the seed transducer JSON file
     */
    private static void setArcFromState(SeedTransducer seed, LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res, SeedTransducerPOJO pojo) {
        String from = (String) mp.get(SEED_TEMPLATE_ARC_FROM.getLabel());
        Optional<State> fromStateOpt = seed.getStateFromName(from);
        if(fromStateOpt.isPresent()) {
            arc.setFrom(fromStateOpt.get());
        } else {
            handle(res, SeedTransducerParsingErrorType.INVALID_FROM_STATE_IN_ARC,
                    mp.toString(), "" + pojo.getStates());
        }
    }

    /**
     * Adds the "to" {@link State} given from the JSON arc representation into the given {@link Arc}.
     * Adds errors if need to the {@link SeedTransducerParsingResult}.
     * @param seed {@link SeedTransducer} parse result object (modified)
     * @param mp JSON arc representation
     * @param arc {@link Arc} object to be added (modified)
     * @param res The {@link SeedTransducerParsingResult} parsing result object (modified)
     * @param pojo The {@link SeedTransducerPOJO} mapped from the seed transducer JSON file
     */
    private static void setArcToState(SeedTransducer seed, LinkedHashMap mp, Arc arc, SeedTransducerParsingResult res, SeedTransducerPOJO pojo) {
        String to = (String) mp.get(SEED_TEMPLATE_ARC_TO.getLabel());
        Optional<State> toStateOpt = seed.getStateFromName(to);
        if(toStateOpt.isPresent()) {
            arc.setTo(toStateOpt.get());
        } else {
            handle(res, SeedTransducerParsingErrorType.INVALID_TO_STATE_IN_ARC,
                    "" + mp, "" + pojo.getStates());
        }
    }

}
