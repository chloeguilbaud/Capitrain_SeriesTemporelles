package model.decoration.table.element;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Function extends Element {

    private String name;
    private List<Element> parameters;

    public Function(String name) {
        this.name = name;
        this.parameters = new ArrayList<Element>();
    }

    public Function(String name, List<Element> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public void addParameter(Element e) {
        this.parameters.add(e);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getParameters() {
        return parameters;
    }

    public void setParameters(List<Element> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Objects.equals(name, function.name) &&
                Objects.equals(parameters, function.parameters);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, parameters);
    }
}
