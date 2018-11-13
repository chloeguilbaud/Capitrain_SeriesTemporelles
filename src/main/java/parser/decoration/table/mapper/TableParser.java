package parser.decoration.table.mapper;

import java.util.List;

public class TableParser {

    private String letter;
    private int after;
    private List<GuardParser> guards;
    private List<UpdateParser> updates;

    public String getLetter() {
        return letter;
    }

    public int getAfter() {
        return after;
    }

    public List<GuardParser> getGuards() {
        return guards;
    }

    public List<UpdateParser> getUpdates() {
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
