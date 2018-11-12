package parser.decoration.table.model;

import java.util.List;

public class FunctionPOJO {

    public String name;
    public List<Object> parameters;

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
