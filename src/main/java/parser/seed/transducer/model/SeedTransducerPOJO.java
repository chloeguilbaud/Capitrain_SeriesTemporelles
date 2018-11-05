package parser.seed.transducer.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SeedTransducerPOJO {

    public String name;
    public String init_state;
    public ArrayList states;
    public ArrayList arcs;

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
