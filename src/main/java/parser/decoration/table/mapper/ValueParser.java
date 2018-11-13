package parser.decoration.table.mapper;

public class ValueParser {

    private DecorationTableMapper function;
    private VariableParser variable;

    public DecorationTableMapper getFunction() {
        return function;
    }

    public VariableParser getVariable() {
        return variable;
    }

    @Override
    public String toString() {
        return "ValuePOJO{" +
                "function=" + function +
                ", variable=" + variable +
                '}';
    }
}
