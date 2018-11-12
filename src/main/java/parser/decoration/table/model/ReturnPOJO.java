package parser.decoration.table.model;

public class ReturnPOJO {

    public String variable;
    public String index;

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
