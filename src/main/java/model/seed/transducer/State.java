package model.seed.transducer;

import java.util.Objects;

/**
 * Details of a state
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class State {

    /**
     * Name of the {@link State}
     */
    private String name;

    public State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "State{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(name, state.name);
    }

}
