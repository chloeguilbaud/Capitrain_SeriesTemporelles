package parser.decoration.table.mapper;

public class UpdateParser {

    private String variable;
    private ValueParser value;

    public String getVariable() {
        return variable;
    }

    public ValueParser getValue() {
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
