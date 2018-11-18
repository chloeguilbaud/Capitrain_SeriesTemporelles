package parser.decoration.table.model;

/**
 * POJO used for value representation from Decoration table JSON file. Enables easy mapping.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
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
