package utils;

import model.seed.transducer.*;

import java.util.Optional;

public class SeedTransducerMock {

    public static SeedTransducer get() {

        SeedTransducer seed = new SeedTransducer("test_seed", 1, 1);

        seed.setBefore(2);
        seed.setAfter(1);

        seed.addState(new State("s"));
        seed.addState(new State("r"));
        seed.addState(new State("t"));
        seed.addState(new State("u"));
        seed.addState(new State("v"));

        Optional<State> op = seed.getStateFromName("s");
        seed.setInitState(op.get());

        seed.addArc(new Arc(seed.getStateFromName("s").get(), seed.getStateFromName("s").get(), ArcOperator.LEQ, ArcSemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("s").get(), seed.getStateFromName("r").get(), ArcOperator.GT, ArcSemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("r").get(), seed.getStateFromName("s").get(), ArcOperator.LEQ, ArcSemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("r").get(), seed.getStateFromName("t").get(), ArcOperator.GT, ArcSemanticLetter.OUT_AFTER));
        seed.addArc(new Arc(seed.getStateFromName("t").get(), seed.getStateFromName("u").get(), ArcOperator.LT, ArcSemanticLetter.MAYBE_BEFORE));

        seed.addArc(new Arc(seed.getStateFromName("u").get(), seed.getStateFromName("s").get(), ArcOperator.LEQ, ArcSemanticLetter.OUT_RESET));
        seed.addArc(new Arc(seed.getStateFromName("u").get(), seed.getStateFromName("v").get(), ArcOperator.GT, ArcSemanticLetter.MAYBE_BEFORE));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("s").get(), ArcOperator.LEQ, ArcSemanticLetter.OUT_RESET));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), ArcOperator.GT, ArcSemanticLetter.FOUND_END));

        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), ArcOperator.EQ, ArcSemanticLetter.FOUND));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), ArcOperator.GEQ, ArcSemanticLetter.IN));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), ArcOperator.GT, ArcSemanticLetter.MAYBE_AFTER));

        return seed;
    }

    public static SeedTransducer getPeak() {

        SeedTransducer seed = new SeedTransducer("peak", 1, 1);

        seed.setBefore(2);
        seed.setAfter(1);

        seed.addState(new State("s"));
        seed.addState(new State("r"));
        seed.addState(new State("t"));
        seed.addState(new State("u"));
        seed.addState(new State("v"));

        Optional<State> op = seed.getStateFromName("s");
        seed.setInitState(op.get());

        seed.addArc(new Arc(seed.getStateFromName("s").get(), seed.getStateFromName("s").get(), ArcOperator.LEQ, ArcSemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("s").get(), seed.getStateFromName("r").get(), ArcOperator.GT, ArcSemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("r").get(), seed.getStateFromName("s").get(), ArcOperator.LEQ, ArcSemanticLetter.OUT));
        seed.addArc(new Arc(seed.getStateFromName("r").get(), seed.getStateFromName("t").get(), ArcOperator.GT, ArcSemanticLetter.OUT_AFTER));
        seed.addArc(new Arc(seed.getStateFromName("t").get(), seed.getStateFromName("u").get(), ArcOperator.LT, ArcSemanticLetter.MAYBE_BEFORE));

        seed.addArc(new Arc(seed.getStateFromName("u").get(), seed.getStateFromName("s").get(), ArcOperator.LEQ, ArcSemanticLetter.OUT_RESET));
        seed.addArc(new Arc(seed.getStateFromName("u").get(), seed.getStateFromName("v").get(), ArcOperator.GT, ArcSemanticLetter.MAYBE_BEFORE));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("s").get(), ArcOperator.LEQ, ArcSemanticLetter.OUT_RESET));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), ArcOperator.GT, ArcSemanticLetter.FOUND_END));

        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), ArcOperator.EQ, ArcSemanticLetter.FOUND));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), ArcOperator.GEQ, ArcSemanticLetter.IN));
        seed.addArc(new Arc(seed.getStateFromName("v").get(), seed.getStateFromName("t").get(), ArcOperator.GT, ArcSemanticLetter.MAYBE_AFTER));

        return seed;
    }



}
