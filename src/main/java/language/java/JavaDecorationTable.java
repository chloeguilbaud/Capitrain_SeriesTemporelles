package language.java;

import java.util.HashMap;

import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.InstructionKey;
import model.element.Element;

public class JavaDecorationTable {

    private HashMap<String, Element> registers;
    private HashMap<String, Element> returns;
    private HashMap<InstructionKey, Instruction> instructions;

    public JavaDecorationTable(DecorationTable decorationTable) {
        this.registers = decorationTable.getRegisters();
        this.returns = decorationTable.getReturns();
        this.instructions = decorationTable.getInstructions();
    }

    public void appendCode(String indent, StringBuffer buffer) {
        buffer.append(indent + "\n");
        this.instructions.forEach((key, value) -> {
            buffer.append(indent + "\n");
            buffer.append(indent + JavaSemanticLetter.fromSemanticLetter(key.getArcSemanticLetter()).get().getLabel() + "() {\n");
            buffer.append(indent + "\t// TODO\n");
            buffer.append(indent + "}\n");
        });
    }
}