package parser.decoration.table.model;

public class InitValuePOJO {

    private String variable;
    private String function;

    public String getVariable() {
        return variable;
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "InitValue{" +
                "variable='" + variable + '\'' +
                ", function='" + function + '\'' +
                '}';
    }
}
