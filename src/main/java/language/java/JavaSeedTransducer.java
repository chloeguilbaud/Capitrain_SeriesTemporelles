package language.java;

import java.util.HashMap;

import model.seed.transducer.SeedTransducer;

public class JavaSeedTransducer {

    private HashMap<String, JavaState> states;

    public JavaSeedTransducer(SeedTransducer seedTransducer) {
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
            buffer.append(indent).append("\n");
            value.appendCode(indent, buffer);
        });
    }
}
