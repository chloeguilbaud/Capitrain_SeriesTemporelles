package model.seed.transducer;

import java.util.*;

public class SeedTransducer {

    private String name;
    private State initState;
    private HashMap<String, State> states;
    private HashSet<Arc> arcs;

    public SeedTransducer(String name) {
        this.name = name;
        this.states = new HashMap<>();
        this.arcs = new HashSet<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitState(State initState) {
        this.initState = initState;
    }

    public void setArcs(HashSet<Arc> arcs) {
        this.arcs = arcs;
    }

    public void setStates(HashMap<String, State> states) {
        this.states = states;
    }

    public void addState(String stateName, State state) {
        this.states.put(stateName, state);
    }

    public Optional<State> getStateFromName(String name) {
        return Optional.ofNullable(this.states.get(name));
    }

    public void addArc(Arc arc) {
        this.arcs.add(arc);
    }

    @Override
    public String toString() {
        return "SeedTransducer{" +
                "name='" + name + '\'' +
                ", initState=" + initState +
                ", states=" + states +
                ", arcs=" + arcs +
                '}';
    }
}
