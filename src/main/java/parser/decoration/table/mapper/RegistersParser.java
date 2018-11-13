package parser.decoration.table.mapper;

public class RegistersParser {

    private String name;
    private ValueParser value;

    public String getName() {
        return name;
    }

    public ValueParser getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "RegistersPOJO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
