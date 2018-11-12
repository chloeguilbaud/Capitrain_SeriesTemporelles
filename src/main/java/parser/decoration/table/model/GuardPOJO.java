package parser.decoration.table.model;

public class GuardPOJO {

    public String variable;
    public String index;
    public ValuePOJO value;

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
