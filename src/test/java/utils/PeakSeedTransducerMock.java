package utils;

import model.seed.transducer.*;

public class PeakSeedTransducerMock {

    public static SeedTransducer get() {
        SeedTransducer peak = new SeedTransducer("peak", 1, 0);

        State d = new State("d");
        State r = new State("r");
        State t = new State("t");
        
        Arc dd = new Arc();
        dd.setFrom(d);
        dd.setTo(d);
        dd.setArcOperator(ArcOperator.GEQ);
        dd.setArcSemanticLetter(ArcSemanticLetter.OUT);
        Arc dr = new Arc();
        dr.setFrom(d);
        dr.setTo(r);
        dr.setArcOperator(ArcOperator.LT);
        dr.setArcSemanticLetter(ArcSemanticLetter.OUT);
        Arc rr = new Arc();
        rr.setFrom(r);
        rr.setTo(r);
        rr.setArcOperator(ArcOperator.LEQ);
        rr.setArcSemanticLetter(ArcSemanticLetter.MAYBE_BEFORE);
        Arc rt = new Arc();
        rt.setFrom(r);
        rt.setTo(t);
        rt.setArcOperator(ArcOperator.GT);
        rt.setArcSemanticLetter(ArcSemanticLetter.FOUND);
        Arc tr = new Arc();
        tr.setFrom(t);
        tr.setTo(r);
        tr.setArcOperator(ArcOperator.LT);
        tr.setArcSemanticLetter(ArcSemanticLetter.OUT_AFTER);
        Arc tt1 = new Arc();
        tt1.setFrom(t);
        tt1.setTo(t);
        tt1.setArcOperator(ArcOperator.GT);
        tt1.setArcSemanticLetter(ArcSemanticLetter.IN);
        Arc tt2 = new Arc();
        tt2.setFrom(t);
        tt2.setTo(t);
        tt2.setArcOperator(ArcOperator.EQ);
        tt2.setArcSemanticLetter(ArcSemanticLetter.MAYBE_AFTER);
        peak.addState(d);
        peak.addState(r);
        peak.addState(t);
        peak.setInitState(d);
        peak.addArc(dd);
        peak.addArc(dr);
        peak.addArc(rr);
        peak.addArc(rt);
        peak.addArc(tr);
        peak.addArc(tt1);
        peak.addArc(tt2);

        return peak;
    }



}
