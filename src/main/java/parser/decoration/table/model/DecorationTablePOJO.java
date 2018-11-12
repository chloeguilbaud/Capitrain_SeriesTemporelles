package parser.decoration.table.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DecorationTablePOJO {

    public String name;
    public List<InitialisationPOJO> initialisation;
    public List<TablePOJO> table;
    @JsonProperty("return")
    public List<ReturnPOJO> returnStatement;

    public String getName() {
        return name;
    }

    public List<InitialisationPOJO> getInitialisation() {
        return initialisation;
    }

    public List<TablePOJO> getTable() {
        return table;
    }

    public List<ReturnPOJO> getReturnStatement() {
        return returnStatement;
    }

    @Override
    public String toString() {
        return "DecorationTablePOJO{" +
                "name='" + name + '\'' +
                ", initialisation=" + initialisation +
                ", table=" + table +
                ", returnStatement=" + returnStatement +
                '}';
    }
}
