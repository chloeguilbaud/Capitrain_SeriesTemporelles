package model.decoration.table;

import model.seed.transducer.ArcSemanticLetter;

import java.util.Objects;
import java.util.Optional;

/**
 * Key for {@link java.util.HashMap}, array an instruction
 * by it's {@link ArcSemanticLetter} and ({@link Optional})
 * an After value
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class InstructionKey {

    /**
     * Semantic letter related to the {@link Instruction}
     */
    private ArcSemanticLetter arcSemanticLetter;
    /**
     * After value related to the {@link Instruction}
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionKey that = (InstructionKey) o;
        return arcSemanticLetter == that.arcSemanticLetter &&
                Objects.equals(after, that.after);
    }
}
