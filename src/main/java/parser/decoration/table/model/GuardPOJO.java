package parser.decoration.table.model;

public class GuardPOJO {

    private String variable;
    private String index;
    private ValuePOJO value;

    public String getVariable() {
        return variable;
    }

    public String getIndex() {
        return index;
    }

    public ValuePOJO getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "GuardPOJO{" +
                "variable='" + variable + '\'' +
                ", index='" + index + '\'' +
                ", value=" + value +
                '}';
    }
}
