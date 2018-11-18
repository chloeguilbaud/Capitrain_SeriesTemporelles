package language.java;

import java.util.HashMap;

import model.decoration.table.DecorationTable;
import model.decoration.table.Instruction;
import model.decoration.table.InstructionKey;
import model.decoration.table.element.Element;
import model.seed.transducer.SeedTransducer;

/**
 * Java class for generating Decoration Table's java code
 * Reflect the model's {@link DecorationTable} class
 * Call appendCode(String, Stringbuffer) to generate code
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class JavaDecorationTable {

    /**
     * After's value of {@link SeedTransducer}
     */
    private int after;
    /**
     * Registers of {@link DecorationTable}
     */
    private HashMap<String, Element> registers;
    /**
     * Instructions of {@link DecorationTable}
     */
    private HashMap<InstructionKey, Instruction> instructions;

    /**
     * Constructor
     * @param decorationTable   Related {@link DecorationTable} to generate in java code
     * @param after             After value of the {@link SeedTransducer}
     */
    public JavaDecorationTable(DecorationTable decorationTable, int after) {
        this.after = after;
        this.registers = decorationTable.getRegisters();
        this.instructions = decorationTable.getInstructions();
    }

    /**
     * Generate java code of the object
     * @param indent {@link String}: Base indentation to have cleaner code
     * @param buffer {@link StringBuffer}: Append code to this buffer
     */
    public void appendCode(String indent, StringBuffer buffer) {
        buffer.append(indent + "\n");
        // For each instruction
        this.instructions.forEach((key, value) -> {
            // If the after of the instruction is the one needed by the SeedTransducer
            if (!key.getAfter().isPresent() || key.getAfter().get() == this.after) {
                // Add a function to the code corresponding to the SemanticLetter
                buffer.append(indent + "\n");
                buffer.append(indent + "private void " + JavaSemanticLetter.fromSemanticLetter(key.getArcSemanticLetter()).get().getLabel() + "() {\n");
                // Declaration of a local i to put in lambda functions in order to execute them after
                buffer.append(indent + "\tint i = this.i;\n");
                // Including all registers as local variables
                this.registers.forEach((register, init) -> {
                    buffer.append(indent + "\tint " + register + " = this.registers.get(\"" + register + "\");\n");
                });
                // Adding all guards instructions to do for this SemanticLetter
                value.getGuards().forEach((variable, instruction) -> {
                    (new JavaGuard(instruction)).appendCode(indent + "\t", buffer);
                });
                // Adding all updates instructions to do for this SemanticLetter
                value.getUpdates().forEach((registre, instruction) -> {
                    (new JavaUpdate(instruction)).appendCode(indent + "\t", buffer);
                });
                // Save all new values for each registers
                this.registers.forEach((variable, init) -> {
                    buffer.append(indent + "\tthis.results.get(\"" + variable + "\").set(i, this.registers.get(\"" + variable + "\"));\n");
                });
                // Close function
                buffer.append(indent + "}\n");
            }
        });
    }
}