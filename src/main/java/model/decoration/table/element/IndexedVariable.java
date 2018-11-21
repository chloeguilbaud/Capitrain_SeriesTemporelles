package model.decoration.table.element;

import java.util.Objects;

/**
 * Implementation of an indexed {@link Variable}
 * An IndexedVariable is also a {@link Variable} and obviously an {@link Element}
 * An IndexedVariable is described by its name and a value representing the distance from
 * the relative index
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class IndexedVariable extends Variable {

    /**
     * Distance of this instance from the relative index
     */
    private int relativeElementDistance;

    public IndexedVariable(String name, int relativeElementDistance) {
        super(name);
        this.relativeElementDistance = relativeElementDistance;
    }

    public int getRelativeElementDistance(){
        return this.relativeElementDistance;
    }

    public void setRelativeElementDistance(int relativeElementDistance) {
        this.relativeElementDistance = relativeElementDistance;
    }

    @Override
    public String toString() {
        if (this.relativeElementDistance > 0) {
            return this.getName() + "(i+" + this.relativeElementDistance + ")";    
        } else if (this.relativeElementDistance < 0) {
            return this.getName() + "(i-" + this.relativeElementDistance + ")";    
        }
        return this.getName() + "(i)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexedVariable that = (IndexedVariable) o;
        return relativeElementDistance == that.relativeElementDistance;
    }

    @Override
    public int hashCode() {

        return Objects.hash(relativeElementDistance);
    }
}
