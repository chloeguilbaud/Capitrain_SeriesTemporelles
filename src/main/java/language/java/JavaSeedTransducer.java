package language.java;

import java.util.HashMap;

import generator.error.GeneratorResult;
import model.seed.transducer.SeedTransducer;

/**
 * Java code representation of a {@link SeedTransducer}
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class JavaSeedTransducer {

    /**
     * {@link HashMap} of {@link SeedTransducer}'s related states as {@link JavaState}
     */
    private HashMap<String, JavaState> states;

    /**
     * Constructor
     * @param seedTransducer {@link SeedTransducer} to convert into java code
     */
    public JavaSeedTransducer(SeedTransducer seedTransducer) {
        // Translate all States into JavaStates
        this.states = new HashMap<>();
        seedTransducer.getStates().forEach((key, value) -> {
            this.states.put(key, new JavaState(value));
        });
        // Adding all exiting arcs to related states
        seedTransducer.getArcs().forEach((value) -> {
            this.states.get(value.getFrom().getName()).addExitingArc(value);
        });
    }

    /**
     * Generate java code of the object
     * @param indent {@link String}: Base indentation to have cleaner code
     * @param buffer {@link StringBuffer}: Append code to this buffer
     * @param result
     */
    public void appendCode(String indent, StringBuffer buffer, GeneratorResult result) {
        buffer.append(indent);
        this.states.forEach((key, value) -> {
            value.appendCode(indent, buffer);
        });
        buffer.append("{\n");
        buffer.append(indent + "\tbreak;\n");
        buffer.append(indent + "}\n");
    }
}
