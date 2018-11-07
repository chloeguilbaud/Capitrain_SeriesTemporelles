package parser.seed.transducer.model;

import model.seed.transducer.SeedTransducer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * POJO used for {@link SeedTransducer} JSON file representation. Enables easy mapping.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class SeedTransducerPOJO {

    private String name;
    private String init_state;
    private ArrayList<String> states;
    private ArrayList<LinkedHashMap> arcs;

    public String getName() {
        return name;
    }

    public String getInit_state() {
        return init_state;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public ArrayList<LinkedHashMap> getArcs() {
        return arcs;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInit_state(String init_state) {
        this.init_state = init_state;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

    public void setArcs(ArrayList<LinkedHashMap> arcs) {
        this.arcs = arcs;
    }

    @Override
    public String toString() {
        return "SeedTransducerPOJO{" +
                "name='" + name + '\'' +
                ", init_state='" + init_state + '\'' +
                ", states=" + states +
                ", arcs=" + arcs +
                '}';
    }

}
