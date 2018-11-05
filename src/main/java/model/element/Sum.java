package model.element;

public class Sum extends Element {

    private Element leftElement;
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

}
