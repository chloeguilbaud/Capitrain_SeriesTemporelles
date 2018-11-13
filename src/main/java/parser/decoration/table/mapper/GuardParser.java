package parser.decoration.table.mapper;

public class GuardParser {

    private String variable;
    private String index;
    private ValueParser value;

    public String getVariable() {
        return variable;
    }

    public String getIndex() {
        return index;
    }

    public ValueParser getValue() {
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
