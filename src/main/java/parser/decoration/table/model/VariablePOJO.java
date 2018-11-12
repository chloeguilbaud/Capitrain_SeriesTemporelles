package parser.decoration.table.model;

public class VariablePOJO {

    public String name;
    public String index;

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "VariablePOJO{" +
                "name='" + name + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
