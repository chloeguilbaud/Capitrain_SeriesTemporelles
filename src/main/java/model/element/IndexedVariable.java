package model.element;

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
}
