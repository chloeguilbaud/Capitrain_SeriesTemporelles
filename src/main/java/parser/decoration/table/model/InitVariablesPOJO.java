package parser.decoration.table.model;

public class InitVariablesPOJO {

    public String name;
    public String index;
    public InitValue value;

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public InitValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "InitVariablesPOJO{" +
                "name='" + name + '\'' +
                ", index='" + index + '\'' +
                ", value=" + value +
                '}';
    }
}
