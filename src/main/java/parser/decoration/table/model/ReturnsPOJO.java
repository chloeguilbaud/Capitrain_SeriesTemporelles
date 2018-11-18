package parser.decoration.table.model;

/**
 * POJO used for return representation from Decoration table JSON file. Enables easy mapping.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class ReturnsPOJO {

    private String name;
    private String index;
    private ValuePOJO value;

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public ValuePOJO getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ReturnsPOJO{" +
                "name='" + name + '\'' +
                ", index='" + index + '\'' +
                ", value=" + value +
                '}';
    }

}
