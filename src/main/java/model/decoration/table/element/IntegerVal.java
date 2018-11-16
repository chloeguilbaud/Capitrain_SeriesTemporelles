package model.decoration.table.element;

import java.util.Objects;

/**
 * Implementation of an integer as an {@link Element}
 * Basically just an {@link Integer}, but also an {@link Element}
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class IntegerVal extends Element {

    /**
     * Value of the {@link Integer}
     */
    private int value;

    public IntegerVal(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerVal that = (IntegerVal) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {

        return Objects.hash(value);
    }
}
