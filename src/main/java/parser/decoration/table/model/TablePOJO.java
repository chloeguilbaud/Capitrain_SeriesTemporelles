package parser.decoration.table.model;

import java.util.List;

public class TablePOJO {

    private String letter;
    private Integer after;
    private List<GuardPOJO> guards;
    private List<UpdatePOJO> updates;

    public String getLetter() {
        return letter;
    }

    public Integer getAfter() {
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
