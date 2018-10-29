package model.seed.transducer;

import java.util.HashSet;

public class SeedTransducer {

    private String name;
    private HashSet<State> states;
    private HashSet<Link> links;
    private int nodeDistance;
    private String startStateKey;

    public SeedTransducer(String name, HashSet<State> states, HashSet<Link> links, int nodeDistance, String startStateKey) {
        this.name = name;
        this.states = states;
        this.links = links;
        this.nodeDistance = nodeDistance;
        this.startStateKey = startStateKey;
    }

}
