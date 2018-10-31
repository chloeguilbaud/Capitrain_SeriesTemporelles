package model.seed.transducer;

import java.util.HashMap;
import java.util.HashSet;

public class SeedTransducer {

    private String name;
    private State initState;
    private HashMap<String, State> states;
    private HashMap<String, Link> links;
    private int nodeDistance;
    private String startStateKey;

    public SeedTransducer(String name, State initState, HashMap<String, State> states, HashMap<String, Link> links, int nodeDistance, String startStateKey) {
        this.name = name;
        this.initState = initState;
        this.states = states;
        this.links = links;
        this.nodeDistance = nodeDistance;
        this.startStateKey = startStateKey;
    }

}
