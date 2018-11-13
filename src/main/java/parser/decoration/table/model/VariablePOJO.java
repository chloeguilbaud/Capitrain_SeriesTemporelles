package parser.decoration.table.model;

public class VariablePOJO {

    private String name;
    private String index;

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
