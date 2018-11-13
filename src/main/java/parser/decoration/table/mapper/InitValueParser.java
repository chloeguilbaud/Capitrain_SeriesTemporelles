package parser.decoration.table.mapper;

public class InitValueParser {

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
