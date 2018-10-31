package model.seed.transducer;

import java.util.HashMap;

public class SeedTransducer {

    private String name;
    private State initState;
    private HashMap<String, State> states;
    private HashMap<String, Link> arcs;
    private int nodeDistance;
    private String startStateKey;

    public SeedTransducer(String name, State initState, HashMap<String, State> states, HashMap<String, Link> arcs, int nodeDistance, String startStateKey) {
        this.name = name;
        this.initState = initState;
        this.states = states;
        this.arcs = arcs;
        this.nodeDistance = nodeDistance;
        this.startStateKey = startStateKey;
    }

}
