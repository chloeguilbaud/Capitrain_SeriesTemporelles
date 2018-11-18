package parser.decoration.table.model;

/**
 * POJO used for variable representation from Decoration table JSON file. Enables easy mapping.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class VariablePOJO {

    private String name;
    private String index;

    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "VariablePOJO{" +
                "name='" + name + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
