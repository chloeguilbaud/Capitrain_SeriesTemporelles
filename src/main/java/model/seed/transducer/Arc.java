package model.seed.transducer;

import java.util.Objects;

public class Arc {

    private State from;
    private State to;
    private ArcOperator arcOperator;
    private ArcSemanticLetter arcSemanticLetter;

    public Arc(State from, State to, ArcOperator arcOperator, ArcSemanticLetter arcSemanticLetter) {
        this.from = from;
        this.to = to;
        this.arcOperator = arcOperator;
        this.arcSemanticLetter = arcSemanticLetter;
    }

    public Arc() { }

    public void setFrom(State from) {
        this.from = from;
    }

    public State getFrom(){
        return this.from;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public State getTo() {
        return this.to;
    }

    public void setArcOperator(ArcOperator arcOperator) {
        this.arcOperator = arcOperator;
    }

    public ArcOperator getArcOperator() {
        return this.arcOperator;
    }

    public void setArcSemanticLetter(ArcSemanticLetter arcSemanticLetter) {
        this.arcSemanticLetter = arcSemanticLetter;
    }

    public ArcSemanticLetter getArcSemanticLetter() {
        return this.arcSemanticLetter;
    }

    @Override
    public String toString() {
        return "Arc{" +
                "from=" + from +
                ", to=" + to +
                ", operator=" + arcOperator +
                ", semanticLetter=" + arcSemanticLetter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arc arc = (Arc) o;
        return Objects.equals(from, arc.from) &&
                Objects.equals(to, arc.to) &&
                arcOperator == arc.arcOperator &&
                arcSemanticLetter == arc.arcSemanticLetter;
    }

}
