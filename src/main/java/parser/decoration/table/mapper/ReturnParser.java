package parser.decoration.table.mapper;

public class ReturnParser {

    private String variable;
    private String index;

    public String getVariable() {
        return variable;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "ReturnPOJO{" +
                "variable='" + variable + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
