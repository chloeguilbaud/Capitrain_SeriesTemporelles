package model.decoration.table.element;

import java.util.Objects;

public class Affectation extends Element {

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

    @Override
    public String toString() {
        return this.variable.toString() + " = " + this.value.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Affectation that = (Affectation) o;
        return Objects.equals(variable, that.variable) &&
                Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(variable, value);
    }
}