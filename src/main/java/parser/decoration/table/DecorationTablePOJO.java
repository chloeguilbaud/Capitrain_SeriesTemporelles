package parser.decoration.table;

public class DecorationTablePOJO {

    public String name;
    public InitialisationPOJO initialisation;

    @Override
    public String toString() {
        return "DecorationTablePOJO{" +
                "name='" + name + '\'' +
                ", initialisation=" + initialisation +
                '}';
    }
}
