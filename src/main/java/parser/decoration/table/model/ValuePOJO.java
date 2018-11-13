package parser.decoration.table.model;

public class ValuePOJO {

    private FunctionPOJO function;
    private VariablePOJO variable;

    public FunctionPOJO getFunction() {
        return function;
    }

    public VariablePOJO getVariable() {
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
