package model.decoration.table;

import model.seed.transducer.ArcSemanticLetter;

public class InstructionKey {

    private ArcSemanticLetter arcSemanticLetter;
    private Integer after;

    public InstructionKey(ArcSemanticLetter arcSemanticLetter, int after) {
        this.arcSemanticLetter = arcSemanticLetter;
        this.after = new Integer(after);
    }

    public ArcSemanticLetter getArcSemanticLetter() {
        return this.arcSemanticLetter;
    }

    public Integer getAfter() {
        return this.after;
    }

    @Override
    public int hashCode() {
        return this.arcSemanticLetter.getLabel().hashCode() + this.after.hashCode();
    }

    @Override
    public String toString() {
        return this.arcSemanticLetter.getLabel() + " || " + this.after;
    }
}
