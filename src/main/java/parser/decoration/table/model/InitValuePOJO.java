package parser.decoration.table.model;

public class InitValuePOJO {

    public String function;
    public String variable;

    public String getFunction() {
        return function;
    }

    public String getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return "InitValuePOJO{" +
                "function='" + function + '\'' +
                ", variable='" + variable + '\'' +
                '}';
    }
}
