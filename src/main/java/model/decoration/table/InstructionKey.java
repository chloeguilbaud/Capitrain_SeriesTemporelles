package model.decoration.table;

import model.seed.transducer.SemanticLetter;

public class InstructionKey {

    private SemanticLetter semanticLetter;
    private Integer after;

    public InstructionKey(SemanticLetter semanticLetter, int after) {
        this.semanticLetter = semanticLetter;
        this.after = new Integer(after);
    }

    public SemanticLetter getSemanticLetter() {
        return this.semanticLetter;
    }

    public Integer getAfter() {
        return this.after;
    }

    @Override
    public int hashCode() {
        return this.semanticLetter.getLabel().hashCode() + this.after.hashCode();
    }

    @Override
    public String toString() {
        return this.semanticLetter.getLabel() + " || " + this.after;
    }
}
