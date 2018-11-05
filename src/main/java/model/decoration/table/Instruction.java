package model.decoration.table;

import java.util.HashMap;

import model.element.Element;

public class Instruction {

    private Element guard;
    private HashMap<String, Element> updates;

    public Instruction(Element guard, HashMap<String, Element> updates) {
        this.guard = guard;
        this.updates = updates;
    }

    public Element getGuard() {
        return this.guard;
    }

    public Element getUpdate(String key) {
        return this.updates.get(key);
    }
}
