package parser.decoration.table.model;

import java.util.List;

/**
 * POJO used for function representation from Decoration table JSON file. Enables easy mapping.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class FunctionPOJO {

    private String name;
    private List<Object> parameters;

    public String getName() {
        return name;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    @Override
    public String toString() {
        return "FunctionPOJO{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                '}';
    }
    
}
