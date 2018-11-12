package parser.decoration.table.model;

public class ValuePOJO {

    public FunctionPOJO function;
    public VariablePOJO variable;

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
