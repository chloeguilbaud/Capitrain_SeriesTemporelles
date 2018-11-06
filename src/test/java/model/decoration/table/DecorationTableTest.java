package model.decoration.table;

import org.junit.Test;

import model.element.Affectation;
import model.element.IndexedVariable;
import model.element.Integer;
import model.element.Sum;
import model.element.Variable;
import model.seed.transducer.Arc;
import model.seed.transducer.Operator;
import model.seed.transducer.SeedTransducer;
import model.seed.transducer.SemanticLetter;
import model.seed.transducer.State;

public class DecorationTableTest {

    @Test
    public void initTest() {
        System.out.println("Test");
    }

    static DecorationTable initDecorationTable() {
        // Variable name declaration
        String p_indentifier = "p";
        String C_identifier = "C";

        // Decoration table init
        DecorationTable footprint = new DecorationTable();
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
        footprint.addInstruction(SemanticLetter.OUT, -1, out);
        footprint.addInstruction(SemanticLetter.OUT_AFTER, -1, out);
        footprint.addInstruction(SemanticLetter.OUT_RESET, -1, out);
        footprint.addInstruction(SemanticLetter.MAYBE_AFTER, -1, maybe);
        footprint.addInstruction(SemanticLetter.MAYBE_BEFORE, -1, maybe);
        footprint.addInstruction(SemanticLetter.FOUND, -1, found);
        footprint.addInstruction(SemanticLetter.FOUND_END, -1, found);
        footprint.addInstruction(SemanticLetter.IN, -1, in);

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
        dd.setOperator(Operator.GEQ);
        dd.setSemanticLetter(SemanticLetter.OUT);

        Arc dr = new Arc();
        dr.setFrom(d);
        dr.setTo(d);
        dr.setOperator(Operator.LT);
        dr.setSemanticLetter(SemanticLetter.OUT);

        Arc rr = new Arc();
        rr.setFrom(d);
        rr.setTo(d);
        rr.setOperator(Operator.LEQ);
        rr.setSemanticLetter(SemanticLetter.MAYBE_BEFORE);

        Arc rt = new Arc();
        rt.setFrom(d);
        rt.setTo(d);
        rt.setOperator(Operator.GT);
        rt.setSemanticLetter(SemanticLetter.FOUND);

        Arc tr = new Arc();
        tr.setFrom(d);
        tr.setTo(d);
        tr.setOperator(Operator.LT);
        tr.setSemanticLetter(SemanticLetter.OUT_AFTER);

        Arc tt1 = new Arc();
        tt1.setFrom(d);
        tt1.setTo(d);
        tt1.setOperator(Operator.GT);
        tt1.setSemanticLetter(SemanticLetter.IN);

        Arc tt2 = new Arc();
        tt2.setFrom(d);
        tt2.setTo(d);
        tt2.setOperator(Operator.EQ);
        tt2.setSemanticLetter(SemanticLetter.MAYBE_AFTER);

        peak.addState("d", d);
        peak.addState("r", r);
        peak.addState("t", t);

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

    /**
     * Case test for footprit_constraint
     */
    public static void main(String[] args) {
        // Seed transducer init
        SeedTransducer peak = initTransducer();
        System.out.println(peak);

        // Decoration table init
        DecorationTable footprint = initDecorationTable();
        System.out.println(footprint);
    }

}
