package parser.decoration.table.mapper;

import java.util.List;

public class InitialisationParser {

    private List<RegistersParser> registers;
    private List<InitVariablesParser> variables;

    public List<RegistersParser> getRegisters() {
        return registers;
    }

    public List<InitVariablesParser> getVariables() {
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
