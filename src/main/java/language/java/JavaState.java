package language.java;

import java.util.HashSet;

import model.seed.transducer.State;
import model.seed.transducer.Arc;

public class JavaState {

    private String name;
    private HashSet<Arc> exitingArcs;

    public JavaState(State state) {
        this.name = state.getName();
        this.exitingArcs = new HashSet<>();
    }

    public void addExitingArc(Arc arc) {
        this.exitingArcs.add(arc);
    }

    public void appendCode(String indent, StringBuffer buffer) {

        buffer.append("if (currentState.equals(\"" + this.name + "\")) {\n");
        boolean addElse = false;
        for(Arc arc : this.exitingArcs) {
            if (addElse) buffer.append(indent + "\telse\n");
            buffer.append(indent + "\t" + "if (timeSerie[i] "+ JavaComparator.fromOperator(arc.getArcOperator()).get().getLabel() +" timeSerie[i+1]) {\n");
            buffer.append(indent + "\t\t" + JavaSemanticLetter.fromSemanticLetter(arc.getArcSemanticLetter()).get().getLabel() + "();\n");
            buffer.append(indent + "\t\t" + "i++;\n");
            buffer.append(indent + "\t\tcurrentState = \"" + arc.getTo().getName() + "\";\n");
            buffer.append(indent + "\t" + "}\n");
            addElse = true;
        }
        this.exitingArcs.forEach((value) -> {
        });
        buffer.append(indent + "} else ");
    }

    public String getName() {
        return this.name;
    }
}
