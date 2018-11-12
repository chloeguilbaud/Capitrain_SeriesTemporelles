package parser.decoration.table.model;

public class InitValue {

    public String variable;
    public String function;

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
