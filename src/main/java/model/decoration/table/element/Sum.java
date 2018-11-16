package model.decoration.table.element;

import java.util.Objects;

/**
 * Implentation of a sum between two {@link Element}
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class Sum extends Element {

    /**
     * Left {@link Element} of the sum operation
     */
    private Element leftElement;
    /**
     * Right {@link Element} of the sum operation
     */
    private Element rightElement;

    public Sum(Element left, Element right) {
        this.leftElement = left;
        this.rightElement = right;
    }

    public Element getLeft() {
        return this.leftElement;
    }

    public Element getRight() {
        return this.rightElement;
    }

    @Override
    public String toString() {
        return this.leftElement.toString() + " + " + this.rightElement.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sum sum = (Sum) o;
        return Objects.equals(leftElement, sum.leftElement) &&
                Objects.equals(rightElement, sum.rightElement);
    }

    @Override
    public int hashCode() {

        return Objects.hash(leftElement, rightElement);
    }
}
