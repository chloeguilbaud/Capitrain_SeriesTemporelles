package model.decoration.table.element;

public class IndexedVariable extends Variable {

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
}
