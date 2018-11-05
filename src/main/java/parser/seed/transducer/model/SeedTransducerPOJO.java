package parser.seed.transducer.model;

import model.seed.transducer.State;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SeedTransducerPOJO {

    public String name;
    public String init_state;
    public ArrayList<String> states;
    public ArrayList<LinkedHashMap> arcs;

    @Override
    public String toString() {
        return "SeedTransducerPOJO{" +
                "name='" + name + '\'' +
                ", init_state='" + init_state + '\'' +
                ", states=" + states +
                ", arcs=" + arcs +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getInitState() {
        return init_state;
    }

    public ArrayList<String> getStates() {
        return states;
    }

    public ArrayList<LinkedHashMap> getArcs() {
        return arcs;
    }
}
