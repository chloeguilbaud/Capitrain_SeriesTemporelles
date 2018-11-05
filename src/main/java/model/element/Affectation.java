package model.element;

public class Affectation {

    private Variable variable;
    private Element value;

    public Affectation(Variable variable, Element value) {
        this.variable = variable;
        this.value = value;
    }

    public Variable getVariable() {
        return this.variable;
    }

    public Element getValue() {
        return this.value;
    }

}