package parser.decoration.table.model;

public class UpdatePOJO {

    private String variable;
    private ValuePOJO value;

    public String getVariable() {
        return variable;
    }

    public ValuePOJO getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UpdatePOJO{" +
                "variable='" + variable + '\'' +
                ", value=" + value +
                '}';
    }
}
