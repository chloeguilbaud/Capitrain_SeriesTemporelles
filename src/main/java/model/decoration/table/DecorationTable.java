package model.decoration.table;

import java.util.HashMap;

import model.element.Element;
import model.seed.transducer.SemanticLetter;

public class DecorationTable {

    private HashMap<String, Element> registers;
    private HashMap<String, Element> returns;
    private HashMap<InstructionKey, Instruction> instructions;

    public DecorationTable() {}

    public void addRegister(String key, Element register) {
        this.registers.put(key, register);
    }

    public void addReturns(String key, Element returnElement) {
        this.returns.put(key, returnElement);
    }

    public void addInstruction(SemanticLetter letter, int after, Instruction instruction) {
        this.instructions.put(new InstructionKey(letter, after), instruction);
    }

    public Element getRegister(String key) {
        return this.registers.get(key);
    }

    public Element getReturn(String key) {
        return this.returns.get(key);
    }

    public Instruction getInstruction(SemanticLetter letter, int after) {
        return this.instructions.get(new InstructionKey(letter, after));
    }
}
