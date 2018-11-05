package model.decoration.table;

import org.junit.Test;

import model.element.Affectation;
import model.element.IndexedVariable;
import model.element.Integer;
import model.element.Sum;
import model.element.Variable;
import model.seed.transducer.SemanticLetter;

public class DecorationTableTest {

    @Test
    public void initTest() {
        System.out.println("Test");
    }

    /**
     * Case test for footprit_constraint
     */
    public static void main(String[] args) {

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
    }

}
