package model.seed.transducer;

import java.util.*;

/**
 * Java representation of a Seed Transducer. It is mainly a list of {@link State},
 * with a list of {@link Arc} and before/after value
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class SeedTransducer {

    /**
     * Name of the {@link SeedTransducer}
     */
    private String name;
    /**
     * Before's value of the {@link SeedTransducer}
     */
    private int before;
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
     * @param before bafore's value of the {@link SeedTransducer}
     * @param after after's value of the {@link SeedTransducer}
     */
    public SeedTransducer(String name, int before, int after) {
        this.name = name;
        this.before = before;
        this.after = after;
        this.states = new HashMap<>();
        this.arcs = new HashSet<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBefore(int before) {
        this.before = before;
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

    public int getBefore() {
        return before;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeedTransducer that = (SeedTransducer) o;
        return before == that.before &&
                after == that.after &&
                Objects.equals(name, that.name) &&
                Objects.equals(initState, that.initState) &&
                Objects.equals(states, that.states) &&
                Objects.equals(arcs, that.arcs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, before, after, initState, states, arcs);
    }
}
