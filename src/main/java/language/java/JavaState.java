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
        buffer.append(indent + "public void " + this.name + "() {\n");
        this.exitingArcs.forEach((value) -> {
            buffer.append(indent + "\t" + "if (i >= timeSerie.length) return;\n");
            buffer.append(indent + "\t" + "if (timeSerie[i][1] "+ JavaComparator.fromOperator(value.getOperator()).get().getLabel() +" timeSerie[i+1][1]) {\n");
            buffer.append(indent + "\t\t" + value.getSemanticLetter().getLabel() + "();\n");
            buffer.append(indent + "\t\t" + "i++;\n");
            buffer.append(indent + "\t\t" + value.getTo().getName() + "();\n");
            buffer.append(indent + "\t\t" + "return;\n");
            buffer.append(indent + "\t" + "}\n");
        });
        buffer.append(indent + "}\n");
    }

    public String getName() {
        return this.name;
    }
}
