package model.decoration.table;

import model.seed.transducer.SemanticLetter;

public class InstructionKey {

    private SemanticLetter semanticLetter;
    private Integer after;

    public InstructionKey(SemanticLetter semanticLetter, int after) {
        this.semanticLetter = semanticLetter;
        this.after = new Integer(after);
    }

    @Override
    public int hashCode() {
        return semanticLetter.toString().hashCode() + after.hashCode();
    }
}
