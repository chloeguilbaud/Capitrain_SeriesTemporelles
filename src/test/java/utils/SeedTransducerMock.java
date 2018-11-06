package utils;

import model.seed.transducer.*;

import java.util.Optional;

public class SeedTransducerMock {

    public static SeedTransducer get() {

        SeedTransducer seed = new SeedTransducer("test_seed");

        seed.addState(new State("s"));
        seed.addState(new State("r"));
        seed.addState(new State("t"));
        seed.addState(new State("u"));
        seed.addState(new State("v"));

        Optional<State> op = seed.getStateFromName("s");
        seed.setInitState(op.get());

        seed.addArc(new Arc(seed.getStateFromName("s").get(), seed.getStateFromName("s").get(), Operator.LEQ, SemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("s").get(), seed.getStateFromName("r").get(), Operator.GT, SemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("r").get(), seed.getStateFromName("s").get(), Operator.LEQ, SemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("r").get(), seed.getStateFromName("t").get(), Operator.GT, SemanticLetter.OUT_AFTER));
        seed.addArc(new Arc(seed.getStateFromName("t").get(), seed.getStateFromName("u").get(), Operator.LT, SemanticLetter.MAYBE_BEFORE));

        seed.addArc(new Arc(seed.getStateFromName("u").get(), seed.getStateFromName("s").get(), Operator.LEQ, SemanticLetter.OUT_RESET));
        seed.addArc(new Arc(seed.getStateFromName("u").get(), seed.getStateFromName("v").get(), Operator.GT, SemanticLetter.MAYBE_BEFORE));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("s").get(), Operator.LEQ, SemanticLetter.OUT_RESET));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), Operator.GT, SemanticLetter.FOUND_END));

        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), Operator.EQ, SemanticLetter.FOUND));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), Operator.GEQ, SemanticLetter.IN));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), Operator.GT, SemanticLetter.MAYBE_AFTER));

        return seed;
    }



}
