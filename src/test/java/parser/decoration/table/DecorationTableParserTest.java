package parser.decoration.table;

import conf.TestConfiguration;
import model.decoration.table.DecorationTable;
import model.decoration.table.element.Element;
import model.decoration.table.element.Function;
import model.decoration.table.element.IndexedVariable;
import model.decoration.table.element.Variable;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DecorationTableParserTest {

    @Test
    public void test() throws IOException {
        DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "testDT.json"));
    }

    @Test
    public void test_model() throws IOException {
        DecorationTableParser.parse(new File(TestConfiguration.TEST_FILE_PATH.getValue() + "decorationTableExampleComplet_features.json"));
    }

    public DecorationTable getFeatures() {
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
        id_f.addParameter(new Variable("f"));
        Function defaut = new Function("default");
        Function delta_f_i = new Function("delta");
        delta_f_i.addParameter(new Variable("f"));
        delta_f_i.addParameter(new IndexedVariable("i", 0));
        Function delta_f_1 = new Function("delta");
        delta_f_1.addParameter(new Variable("f"));
        delta_f_1.addParameter(new IndexedVariable("i", 1));
        Function phi_f_D_delta_f = new Function("phi", null);


        // DecorationTable init
        DecorationTable feature = new DecorationTable("feature");
        feature.addRegister(C_identifier, idf);
        feature.addRegister(D_identifier, idf);
        feature.addReturn(f_identifier, defaut);
        feature.addReturn(e_identifier, defaut);




        return null;
    }

    /**
     * TODO
     * this att or this att
     * parameters typing
     * unexeoected element in json
     * Mappe parser errors
     * InitVaruablesPOJO : var or function
     */
}
