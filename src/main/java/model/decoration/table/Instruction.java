package model.decoration.table;

import java.util.HashMap;
import java.util.Objects;

import model.decoration.table.element.Element;

/**
 * Instruction of a {@link DecorationTable}
 * Contains many guards and updates (As {@link Element})
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class Instruction {

    /**
     * {@link HashMap} of guards implemented as {@link Element}
     */
    private HashMap<String, Element> guards;
    /**
     * {@link HashMap} of updates implemented as {@link Element}
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return Objects.equals(guards, that.guards) &&
                Objects.equals(updates, that.updates);
    }

    @Override
    public int hashCode() {

        return Objects.hash(guards, updates);
    }
}
