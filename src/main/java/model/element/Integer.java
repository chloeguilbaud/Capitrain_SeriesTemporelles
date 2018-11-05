package model.element;

public class Integer extends Element {
    
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
