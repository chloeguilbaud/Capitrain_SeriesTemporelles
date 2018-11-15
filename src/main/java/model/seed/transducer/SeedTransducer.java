package model.seed.transducer;

import java.util.*;

/**
 * Java representation of a Seed Transducer. It is mainly a list of {@link State},
 * with a list of {@link Arc} and an after value
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class SeedTransducer {

    /**
     * Name of the {@link SeedTransducer}
     */
    private String name;
    /**
     * After's value of the {@link SeedTransducer}
     */
    private int after;
    /**
     * Initial {@link State} of the {@link SeedTransducer}
     */
    private State initState;
    /**
     * List of the {@link State} ordered by their names
     */
    private HashMap<String, State> states;
    /**
     * List of the {@link Arc} of the {@link SeedTransducer}
     */
    private HashSet<Arc> arcs;

    /**
     * Constructor
     * @param name Name of the {@link SeedTransducer}
     * @param after after's value of the {@link SeedTransducer}
     */
    public SeedTransducer(String name, int after) {
        this.name = name;
        this.after = after;
        this.states = new HashMap<>();
        this.arcs = new HashSet<>();
    }

    // TODO: To delete after full refactoring
    public SeedTransducer(String name) {
        this.name = name;
        this.states = new HashMap<>();
        this.arcs = new HashSet<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAfter(int after) {
        this.after = after;
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

    public void addState(State state) {
        this.states.put(state.getName(), state);
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

    public String getName() {
        return name;
    }

    public int getAfter() {
        return after;
    }

    public State getInitState() {
        return initState;
    }

    public HashMap<String, State> getStates() {
        return states;
    }

    public HashSet<Arc> getArcs() {
        return arcs;
    }

}
