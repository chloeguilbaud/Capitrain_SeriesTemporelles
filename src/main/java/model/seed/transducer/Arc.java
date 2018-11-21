package model.seed.transducer;

import java.util.Objects;

/**
 * Arc java class representing a state change for a {@link SeedTransducer}
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class Arc {

    /**
     * Starting {@link State}
     */
    private State from;
    /**
     * Ending {@link State}
     */
    private State to;
    /**
     * Corresponding {@link ArcOperator} needed to change state
     */
    private ArcOperator arcOperator;
    /**
     * {@link ArcSemanticLetter} associated to the {@link Arc}
     */
    private ArcSemanticLetter arcSemanticLetter;

    /**
     * Constructor
     * @param from Starting {@link State}
     * @param to Ending {@link State}
     * @param arcOperator Corresponding {@link ArcOperator} needed to change state
     * @param arcSemanticLetter {@link ArcSemanticLetter} associated to the {@link Arc}
     */
    public Arc(State from, State to, ArcOperator arcOperator, ArcSemanticLetter arcSemanticLetter) {
        this.from = from;
        this.to = to;
        this.arcOperator = arcOperator;
        this.arcSemanticLetter = arcSemanticLetter;
    }

    /**
     * Constructor
     */
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
