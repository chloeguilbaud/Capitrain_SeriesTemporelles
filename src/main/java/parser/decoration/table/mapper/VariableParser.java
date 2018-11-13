package parser.decoration.table.mapper;

public class VariableParser {

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
