package parser.decoration.table.model;

public class InitVariablesPOJO {

    private String name;
    private String index;
    private InitValuePOJO value;

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public InitValuePOJO getValue() {
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
