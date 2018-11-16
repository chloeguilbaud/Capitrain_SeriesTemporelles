package utils;

import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.element.Affectation;
import model.decoration.table.element.Function;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.Variable;
import model.seed.transducer.ArcSemanticLetter;

public class DecorationTableMock {

    public static DecorationTable getFeatures() {

        // Variable names declaration
        String C_identifier = "C";
        String D_identifier = "D";
        String f_identifier = "f";
        String e_identifier = "e";

        // Variable declaration
        Variable C = new Variable(C_identifier);
        Variable D = new Variable(D_identifier);
        IndexedVariable f_i = new IndexedVariable(f_identifier, 0);
        IndexedVariable e_i = new IndexedVariable(e_identifier, 0);
        IndexedVariable e_1 = new IndexedVariable(e_identifier, 1);

        // Functions declaration
        Function id_f = new Function("idf");
        Function defaut = new Function("default");
        Function defaut_g_f = new Function("default");
        defaut_g_f.addParameter(new Variable("g"));
        defaut_g_f.addParameter(new Variable("f"));
        Function delta_f_i = new Function("delta");
        delta_f_i.addParameter(new Variable("f"));
        delta_f_i.addParameter(new IndexedVariable("i", 0));
        Function delta_f_1 = new Function("delta");
        delta_f_1.addParameter(new Variable("f"));
        delta_f_1.addParameter(new IndexedVariable("i", 1));
        Function phi_f_D_delta_f_i = new Function("phi");
        phi_f_D_delta_f_i.addParameter(new Variable("f"));
        phi_f_D_delta_f_i.addParameter(D);
        phi_f_D_delta_f_i.addParameter(delta_f_i);
        Function phi_f_D_delta_f_1 = new Function("phi");
        phi_f_D_delta_f_1.addParameter(new Variable("f"));
        phi_f_D_delta_f_1.addParameter(D);
        phi_f_D_delta_f_1.addParameter(delta_f_1);
        Function phi_f_phi_f_D_delta_f_i_delta_f_1 = new Function("phi");
        phi_f_phi_f_D_delta_f_i_delta_f_1.addParameter(phi_f_D_delta_f_i);
        phi_f_phi_f_D_delta_f_i_delta_f_1.addParameter(delta_f_1);
        Function phi_f_C_phi_f_D_delta_f_1 = new Function("phi");
        phi_f_C_phi_f_D_delta_f_1.addParameter(C);
        phi_f_C_phi_f_D_delta_f_1.addParameter(phi_f_D_delta_f_1);
        Function phi_f_C_phi_f_D_delta_f_i = new Function("phi");
        phi_f_C_phi_f_D_delta_f_i.addParameter(C);
        phi_f_C_phi_f_D_delta_f_i.addParameter(phi_f_D_delta_f_i);

        // Affectztion declaration
        Affectation f_i_equals_default = new Affectation(f_i, defaut);
        Affectation e_i_equals_default = new Affectation(e_i, defaut);
        Affectation e_i_equals_e_1 = new Affectation(e_i, e_1);
        Affectation e_i_equals_C = new Affectation(e_i, C);
        Affectation f_i_equals_e_i = new Affectation(f_i, e_i);
        Affectation f_i_equals_phi_phi_delta = new Affectation(f_i, phi_f_phi_f_D_delta_f_i_delta_f_1);
        Affectation f_i_equals_phi_D_delta = new Affectation(f_i, phi_f_D_delta_f_1);
        Affectation C_equals_default_g_f = new Affectation(C, defaut_g_f);
        Affectation C_equals_phi_phi_delta = new Affectation(C, phi_f_phi_f_D_delta_f_i_delta_f_1);
        Affectation C_equals_phi_D_delta = new Affectation(C, phi_f_D_delta_f_1);
        Affectation C_equals_phi_C_phi_D_delta_1 = new Affectation(C, phi_f_C_phi_f_D_delta_f_1);
        Affectation C_equals_phi_C_phi_D_delta_i = new Affectation(C, phi_f_C_phi_f_D_delta_f_i);
        Affectation D_equals_id_f = new Affectation(D, id_f);
        Affectation D_equals_phi_D_delta_f_i = new Affectation(D, phi_f_D_delta_f_i);
        Affectation D_equals_phi_D_delta_f_1 = new Affectation(D, phi_f_D_delta_f_1);

        // Instruction declaration
        Instruction out = new Instruction();
        out.addGuard(f_identifier, f_i_equals_default);
        out.addGuard(e_identifier, e_i_equals_default);
        Instruction outr = new Instruction();
        outr.addGuard(f_identifier, f_i_equals_default);
        outr.addGuard(e_identifier, e_i_equals_default);
        outr.addUpdate(D_identifier, D_equals_id_f);
        Instruction outa = new Instruction();
        outa.addGuard(f_identifier, f_i_equals_default);
        outa.addGuard(e_identifier, e_i_equals_C);
        outa.addUpdate(C_identifier, C_equals_default_g_f);
        outa.addUpdate(D_identifier, D_equals_id_f);
        Instruction maybeb = new Instruction();
        maybeb.addGuard(f_identifier, f_i_equals_default);
        maybeb.addGuard(e_identifier, e_i_equals_e_1);
        maybeb.addUpdate(D_identifier, D_equals_phi_D_delta_f_i);
        Instruction maybea0 = new Instruction();
        maybea0.addGuard(f_identifier, f_i_equals_default);
        maybea0.addGuard(e_identifier, e_i_equals_e_1);
        maybea0.addUpdate(D_identifier, D_equals_phi_D_delta_f_1);
        Instruction maybea1 = new Instruction();
        maybea1.addGuard(f_identifier, f_i_equals_default);
        maybea1.addGuard(e_identifier, e_i_equals_e_1);
        maybea1.addUpdate(D_identifier, D_equals_phi_D_delta_f_i);
        Instruction found0 = new Instruction();
        found0.addGuard(f_identifier, f_i_equals_e_i);
        found0.addGuard(e_identifier, e_i_equals_e_1);
        found0.addUpdate(C_identifier, C_equals_phi_phi_delta);
        found0.addUpdate(D_identifier, D_equals_id_f);
        Instruction found1 = new Instruction();
        found1.addGuard(f_identifier, f_i_equals_e_i);
        found1.addGuard(e_identifier, e_i_equals_e_1);
        found1.addUpdate(C_identifier, C_equals_phi_D_delta);
        found1.addUpdate(D_identifier, D_equals_id_f);
        Instruction in0 = new Instruction();
        in0.addGuard(f_identifier, f_i_equals_default);
        in0.addGuard(e_identifier, e_i_equals_e_1);
        in0.addUpdate(C_identifier, C_equals_phi_C_phi_D_delta_1);
        in0.addUpdate(D_identifier, D_equals_id_f);
        Instruction in1 = new Instruction();
        in1.addGuard(f_identifier, f_i_equals_default);
        in1.addGuard(e_identifier, e_i_equals_e_1);
        in1.addUpdate(C_identifier, C_equals_phi_C_phi_D_delta_i);
        in1.addUpdate(D_identifier, D_equals_id_f);
        Instruction founde0 = new Instruction();
        founde0.addGuard(f_identifier, f_i_equals_phi_phi_delta);
        founde0.addGuard(e_identifier, e_i_equals_default);
        founde0.addUpdate(D_identifier, D_equals_id_f);
        Instruction founde1 = new Instruction();
        founde1.addGuard(f_identifier, f_i_equals_phi_D_delta);
        founde1.addGuard(e_identifier, e_i_equals_default);
        founde1.addUpdate(D_identifier, D_equals_id_f);

        // DecorationTable init
        DecorationTable feature = new DecorationTable("feature");
        feature.addRegister(C_identifier, id_f);
        feature.addRegister(D_identifier, id_f);
        feature.addReturn(f_identifier, defaut);
        feature.addReturn(e_identifier, C);
        feature.addInstruction(ArcSemanticLetter.OUT, out);
        feature.addInstruction(ArcSemanticLetter.OUT_RESET, outr);
        feature.addInstruction(ArcSemanticLetter.OUT_AFTER, outa);
        feature.addInstruction(ArcSemanticLetter.MAYBE_BEFORE, maybeb);
        feature.addInstruction(ArcSemanticLetter.MAYBE_AFTER, 0, maybea0);
        feature.addInstruction(ArcSemanticLetter.MAYBE_AFTER, 1, maybea1);
        feature.addInstruction(ArcSemanticLetter.FOUND, 0, found0);
        feature.addInstruction(ArcSemanticLetter.FOUND, 1, found1);
        feature.addInstruction(ArcSemanticLetter.IN, 0, in0);
        feature.addInstruction(ArcSemanticLetter.IN, 1, in1);
        feature.addInstruction(ArcSemanticLetter.FOUND_END, 0, founde0);
        feature.addInstruction(ArcSemanticLetter.FOUND_END, 1, founde1);

        return feature;
    }

}
