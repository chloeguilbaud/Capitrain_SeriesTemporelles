package language.java;

import java.util.HashMap;

import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.InstructionKey;
import model.decoration.table.element.Element;

public class JavaDecorationTable {

    private int after;
    private HashMap<String, Element> registers;
    private HashMap<String, Element> returns;
    private HashMap<InstructionKey, Instruction> instructions;

    public JavaDecorationTable(DecorationTable decorationTable, int after) {
        this.after = after;
        this.registers = decorationTable.getRegisters();
        this.returns = decorationTable.getReturns();
        this.instructions = decorationTable.getInstructions();
    }

    public void appendCode(String indent, StringBuffer buffer) {
        buffer.append(indent + "\n");
        this.instructions.forEach((key, value) -> {
            if (!key.getAfter().isPresent() || key.getAfter().get() == this.after) {
                buffer.append(indent + "\n");
                buffer.append(indent + JavaSemanticLetter.fromSemanticLetter(key.getArcSemanticLetter()).get().getLabel() + "() {\n");
                // Append declaration of local variables
                buffer.append(indent + "\tint i = this.i;\n");
                // Including all registers
                this.registers.forEach((register, init) -> {
                    buffer.append(indent + "\tint " + register + " = this.registers.get(\"" + register + "\");\n");
                });
                value.getGuards().forEach((variable, instruction) -> {
                    (new JavaGuard(instruction)).appendCode(indent + "\t", buffer);
                });
                value.getUpdates().forEach((registre, instruction) -> {
                    (new JavaUpdate(instruction)).appendCode(indent + "\t", buffer);
                });         
                this.registers.forEach((variable, init) -> {
                    buffer.append(indent + "\tthis.results.get(\"" + variable + "\")[i] = this.registers.get(\"" + variable + "\");\n");
                });
                buffer.append(indent + "}\n");
            }
        });
    }
}