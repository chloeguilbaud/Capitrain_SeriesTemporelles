package parser.decoration.table.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DecorationTablePOJO {

    private String name;
    private List<RegistersPOJO> registers;
    private List<ReturnsPOJO> returns;
    private List<TablePOJO> table;

    public String getName() {
        return name;
    }

    public List<RegistersPOJO> getRegisters() {
        return registers;
    }

    public List<ReturnsPOJO> getReturns() {
        return returns;
    }

    public List<TablePOJO> getTable() {
        return table;
    }

    @Override
    public String toString() {
        return "DecorationTablePOJO{" +
                "name='" + name + '\'' +
                ", registers=" + registers +
                ", returns=" + returns +
                ", table=" + table +
                '}';
    }
}
