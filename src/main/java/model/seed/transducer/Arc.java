package model.seed.transducer;

import java.util.Objects;

public class Arc {

    private State from;
    private State to;
    private Operator operator;
    private SemanticLetter semanticLetter;

    public Arc(State from, State to, Operator operator, SemanticLetter semanticLetter) {
        this.from = from;
        this.to = to;
        this.operator = operator;
        this.semanticLetter = semanticLetter;
    }

    public Arc() { }

    public void setFrom(State from) {
        this.from = from;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setSemanticLetter(SemanticLetter semanticLetter) {
        this.semanticLetter = semanticLetter;
    }

    @Override
    public String toString() {
        return "Arc{" +
                "from=" + from +
                ", to=" + to +
                ", operator=" + operator +
                ", semanticLetter=" + semanticLetter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arc arc = (Arc) o;
        return Objects.equals(from, arc.from) &&
                Objects.equals(to, arc.to) &&
                operator == arc.operator &&
                semanticLetter == arc.semanticLetter;
    }

}
