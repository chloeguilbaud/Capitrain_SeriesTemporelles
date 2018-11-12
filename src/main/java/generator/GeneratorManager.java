package generator;

import language.java.JavaGenerator;
import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.element.Affectation;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.Integer;
import model.decoration.table.element.Sum;
import model.decoration.table.element.Variable;
import model.seed.transducer.*;
import model.seed.transducer.ArcOperator;

public class GeneratorManager {

    public static void main(String[] args) {
        JavaGenerator javacode = new JavaGenerator();
        System.out.println(javacode.generateCode(initTransducer(), initDecorationTable()).toString());
    }

    static DecorationTable initDecorationTable() {
        // Variable name declaration
        String p_indentifier = "p";
        String C_identifier = "C";

        // Decoration table init
        DecorationTable footprint = new DecorationTable("footprint");
        footprint.addRegister(C_identifier, new Integer(0));
        footprint.addReturns(p_indentifier, new Integer(0));

        // Variable declaration
        Variable C = new Variable(C_identifier);
        IndexedVariable p_i = new IndexedVariable(p_indentifier, 0);
        IndexedVariable p_1 = new IndexedVariable(p_indentifier, 1);

        // Functions declaration

        // Operations declaration
        Sum C_plus_1 = new Sum(C, new Integer(1));

        // Affectation declaration
        Affectation p_i_equals_0 = new Affectation(p_i, new Integer(0));
        Affectation p_i_equals_p_1 = new Affectation(p_i, p_1);
        Affectation p_i_equals_C_plus_1 = new Affectation(p_i, C_plus_1);
        Affectation C_equals_C_plus_1 = new Affectation(C, C_plus_1);
        Affectation p_i_equals_C = new Affectation(p_i, C);

        // Instructions declaration
        Instruction out = new Instruction();
        out.addGuard(p_indentifier, p_i_equals_0);
        Instruction maybe = new Instruction();
        maybe.addGuard(p_indentifier, p_i_equals_p_1);
        Instruction found = new Instruction();
        found.addGuard(p_indentifier, p_i_equals_C_plus_1);
        found.addUpdate(C_identifier, C_equals_C_plus_1);
        Instruction in = new Instruction();
        in.addGuard(p_indentifier, p_i_equals_C);

        // Adding instructions to table
        footprint.addInstruction(ArcSemanticLetter.OUT, -1, out);
        footprint.addInstruction(ArcSemanticLetter.OUT_AFTER, -1, out);
        footprint.addInstruction(ArcSemanticLetter.OUT_RESET, -1, out);
        footprint.addInstruction(ArcSemanticLetter.MAYBE_AFTER, -1, maybe);
        footprint.addInstruction(ArcSemanticLetter.MAYBE_BEFORE, -1, maybe);
        footprint.addInstruction(ArcSemanticLetter.FOUND, -1, found);
        footprint.addInstruction(ArcSemanticLetter.FOUND_END, -1, found);
        footprint.addInstruction(ArcSemanticLetter.IN, -1, in);

       return footprint;
    }

    static SeedTransducer initTransducer() {
        SeedTransducer peak = new SeedTransducer("peak");

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
