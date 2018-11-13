package parser.decoration.table.model;

import java.util.List;

public class InitialisationPOJO {

    private List<RegistersPOJO> registers;
    private List<InitVariablesPOJO> variables;

    public List<RegistersPOJO> getRegisters() {
        return registers;
    }

    public List<InitVariablesPOJO> getVariables() {
        return variables;
    }

    @Override
    public String toString() {
        return "InitialisationPOJO{" +
                "registers=" + registers +
                ", variables=" + variables +
                '}';
    }
}
