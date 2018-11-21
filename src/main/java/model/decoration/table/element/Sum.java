package model.decoration.table.element;

import java.util.Objects;

/**
 * Implentation of a sum between two {@link Element}
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class Sum extends Operation {

    public Sum(Element left, Element right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + this.getLeft() + "+" + this.getRight() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sum sum = (Sum) o;
        return Objects.equals(this.getLeft(), sum.getLeft()) &&
                Objects.equals(this.getRight(), sum.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getLeft(), this.getRight());
    }
}
