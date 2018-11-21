package parser.decoration.table.model;

import java.util.List;

/**
 * POJO used for Decoration Table JSON file representation. Enables easy mapping.
 * @author Chloe GUILBAUD &amp Maël MAINCHAIN
 */
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
