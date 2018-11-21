package model.decoration.table.element;

/**
 * Implentation of an operation between two {@link Element}
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public abstract class Operation extends Element {

    /**
     * Left {@link Element} of the operation
     */
    private Element leftElement;
    /**
     * Right {@link Element} of the operation
     */
    private Element rightElement;

    public Operation(Element left, Element right) {
        this.leftElement = left;
        this.rightElement = right;
    }

    public Element getLeft() {
        return this.leftElement;
    }

    public Element getRight() {
        return this.rightElement;
    }
}
