package parser.decoration.table.model;

public class RegistersPOJO {

    private String name;
    private FunctionPOJO function;

    public String getName() {
        return name;
    }

    public FunctionPOJO getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "RegistersPOJO{" +
                "name='" + name + '\'' +
                ", function=" + function +
                '}';
    }
}
