package model.decoration.table.element;

import java.util.Objects;

/**
 * Implentation of a product between two {@link Element}
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class Product extends Operation {

    public Product(Element left, Element right) {
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
        Product product = (Product) o;
        return Objects.equals(this.getLeft(), product.getLeft()) &&
                Objects.equals(this.getRight(), product.getRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getLeft(), this.getRight());
    }
}
