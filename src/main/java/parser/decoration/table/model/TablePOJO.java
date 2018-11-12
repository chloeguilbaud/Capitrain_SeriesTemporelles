package parser.decoration.table.model;

import java.util.List;

public class TablePOJO {

    public String letter;
    public int after;
    public List<GuardPOJO> guards;
    public List<UpdatePOJO> updates;

    public String getLetter() {
        return letter;
    }

    public int getAfter() {
        return after;
    }

    public List<GuardPOJO> getGuards() {
        return guards;
    }

    public List<UpdatePOJO> getUpdates() {
        return updates;
    }

    @Override
    public String toString() {
        return "TablePOJO{" +
                "letter='" + letter + '\'' +
                ", after=" + after +
                ", guards=" + guards +
                ", updates=" + updates +
                '}';
    }
}
