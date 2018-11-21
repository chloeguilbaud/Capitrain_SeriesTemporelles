package model.decoration.table.element;

import java.util.Objects;

/**
 * Implementation of a Variable as {@link Element}
 * Identified by a name
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class Variable extends Element {

    /**
     * Name of the variable
     */
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
