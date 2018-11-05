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
        System.out.println(this.semanticLetter);
        System.out.println(this.after);
        System.out.println(this.semanticLetter.hashCode());
        return this.semanticLetter.getLabel()
        .hashCode() +
                this.after.hashCode();
    }
}
