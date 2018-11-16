package model.decoration.table.element;

import java.util.Objects;

/**
 * Implentation of a product between two {@link Element}
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class Division extends Operation {

    public Division(Element left, Element right) {
        super(left, right);
    }

    @Override
    public String toString() {
        return "(" + this.getLeft() + "*" + this.getRight() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Division division = (Division) o;
        return Objects.equals(this.getLeft(), division.getLeft()) &&
                Objects.equals(this.getRight(), division.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getLeft(), this.getRight());
    }
}
