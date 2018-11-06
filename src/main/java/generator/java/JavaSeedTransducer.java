package generator.java;

import java.util.HashMap;
import java.util.HashSet;

import model.seed.transducer.SeedTransducer;
import model.seed.transducer.State;

public class JavaSeedTransducer {

    private String name;
    private State initState;
    private HashMap<String, JavaState> states;
    private HashSet<JavaArc> arcs;

    public JavaSeedTransducer(SeedTransducer seedTransducer) {
        this.name = seedTransducer.getName();
        this.initState = seedTransducer.getInitState();
        this.states = new HashMap<>();
        seedTransducer.getStates().forEach((key, value) -> {
            this.states.put(key, new JavaState(value));
        });
        seedTransducer.getArcs().forEach((value) -> {
            this.states.get(value.getFrom().getName()).addExitingArc(value);
        });
    }

    public void appendCode(String indent, StringBuffer buffer) {
        this.states.forEach((key, value) -> {
            buffer.append(indent + "\n");
            value.appendCode(indent, buffer);
        });
    }
}
