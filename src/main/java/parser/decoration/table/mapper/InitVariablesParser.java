package parser.decoration.table.mapper;

public class InitVariablesParser {

    private String name;
    private String index;
    private ValueParser value;

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public ValueParser getValue() {
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
