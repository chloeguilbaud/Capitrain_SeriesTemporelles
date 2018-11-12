package model.decoration.table;

import model.seed.transducer.ArcSemanticLetter;

import java.util.Optional;

public class InstructionKey {

    private ArcSemanticLetter arcSemanticLetter;
    private Optional<Integer> after;

    InstructionKey(ArcSemanticLetter arcSemanticLetter, Integer after) {
        this.arcSemanticLetter = arcSemanticLetter;
        this.after = Optional.of(after);
    }

    InstructionKey(ArcSemanticLetter arcSemanticLetter) {
        this.arcSemanticLetter = arcSemanticLetter;
        this.after = Optional.empty();
    }

    public ArcSemanticLetter getArcSemanticLetter() {
        return this.arcSemanticLetter;
    }

    public Optional<Integer> getAfter() {
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
