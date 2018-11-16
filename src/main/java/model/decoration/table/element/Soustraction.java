package model.decoration.table.element;

import java.util.Objects;

/**
 * Implentation of a soustraction between two {@link Element}
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class Soustraction extends Operation {

    public Soustraction(Element left, Element right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + this.getLeft() + "-" + this.getRight() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Soustraction sum = (Soustraction) o;
        return Objects.equals(this.getLeft(), sum.getLeft()) &&
                Objects.equals(this.getRight(), sum.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getLeft(), this.getRight());
    }
}
