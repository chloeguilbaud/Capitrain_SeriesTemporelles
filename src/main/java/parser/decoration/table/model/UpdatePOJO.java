package parser.decoration.table.model;

/**
 * POJO used for update representation from Decoration table JSON file. Enables easy mapping.
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
public class UpdatePOJO {

    private String variable;
    private ValuePOJO value;

    public String getVariable() {
        return variable;
    }

    public ValuePOJO getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UpdatePOJO{" +
                "variable='" + variable + '\'' +
                ", value=" + value +
                '}';
    }
}
