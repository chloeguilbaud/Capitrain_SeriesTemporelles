package language.java;

import java.util.HashSet;

import model.seed.transducer.State;
import model.seed.transducer.Arc;

/**
 * Java code of a {@link State}
 * Basically an if statement of the state followed by
 * many instructions depending on the current index value
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class JavaState {

    /**
     * Name of the {@link State}
     */
    private String name;
    /**
     * All exiting arcs of the states
     */
    private HashSet<Arc> exitingArcs;

    /**
     * Constructor
     * @param state Related model's {@link State}
     */
    public JavaState(State state) {
        this.name = state.getName();
        this.exitingArcs = new HashSet<>();
    }

    /**
     * Add an exiting arc to the {@link JavaState}
     * @param arc   Model's {@link Arc}
     */
    public void addExitingArc(Arc arc) {
        this.exitingArcs.add(arc);
    }

    /**
     * Generate java code of the object
     * @param indent {@link String}: Base indentation to have cleaner code
     * @param buffer {@link StringBuffer}: Append code to this buffer
     */
    public void appendCode(String indent, StringBuffer buffer) {
        // If statement to verify if the value of state is right
        buffer.append("if (currentState.equals(\"" + this.name + "\")) {\n");

        // For each exiting arcs, verify values of timeSerie[i] and [i+1] in order to get into the right next JavaState
        boolean addElse = false;
        for(Arc arc : this.exitingArcs) {
            if (addElse) buffer.append(indent + "\telse\n");
            // If statement to verify the condition to the next JavaState
            buffer.append(indent + "\t" + "if (timeSerie[i] "+ JavaComparator.fromOperator(arc.getArcOperator()).get().getLabel() +" timeSerie[i+1]) {\n");
            // Call the SemanticLetter function corresponding to the arc
            buffer.append(indent + "\t\t" + JavaSemanticLetter.fromSemanticLetter(arc.getArcSemanticLetter()).get().getLabel() + "();\n");
            // Increment current index
            buffer.append(indent + "\t\t" + "i++;\n");
            // Change current state
            buffer.append(indent + "\t\tcurrentState = \"" + arc.getTo().getName() + "\";\n");
            // Close if
            buffer.append(indent + "\t" + "}\n");
            addElse = true;
        }
        // Close if and prepare an else for a possible future state condition
        buffer.append(indent + "} else ");
    }

    /**
     * Get the name of the {@link JavaState}
     * @return name of the {@link JavaState}
     */
    public String getName() {
        return this.name;
    }
}
