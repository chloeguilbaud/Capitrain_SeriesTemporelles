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

    // TODO: gérer les afters
    public void appendCode(String indent, StringBuffer buffer) {
        buffer.append(indent + "\n");
        this.instructions.forEach((key, value) -> {
            buffer.append(indent + "\n");
            buffer.append(indent + JavaSemanticLetter.fromSemanticLetter(key.getArcSemanticLetter()).get().getLabel() + "() {\n");
            value.getGuards().forEach((variable, instruction) -> {
                (new JavaGuard(instruction)).appendCode(indent + "\t", buffer);
            });


            this.registers.forEach((variable, init) -> {
                buffer.append(indent + "\tthis.results.get(\"" + variable + "\")[i] = this.registers.get(\"" + variable + "\");\n");
            });
            buffer.append(indent + "}\n");
        });
    }
}