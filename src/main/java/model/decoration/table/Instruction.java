package model.decoration.table;

import java.util.HashMap;

import model.element.Element;

public class Instruction {

    private HashMap<String, Element> guards;
    private HashMap<String, Element> updates;

    public Instruction() {
        this.guards = new HashMap<>();
        this.updates = new HashMap<>();
    }

    public void addGuard(String variable, Element change) {
        this.guards.put(variable, change);
    }

    public void addUpdate(String register, Element change) {
        this.updates.put(register, change);
    }

    public Element getGuard(String key) {
        return this.guards.get(key);
    }

    public Element getUpdate(String key) {
        return this.updates.get(key);
    }

    public HashMap<String, Element> getGuards() {
        return this.guards;
    }

    public HashMap<String, Element> getUpdates() {
        return this.updates;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        this.guards.forEach((key, value) -> {
            buffer.append(value.toString() + ", ");
        });
        buffer.append(" || ");
        this.updates.forEach((key, value) -> {
            buffer.append(value.toString() + ", ");
        });
        return buffer.toString();
    }
}
