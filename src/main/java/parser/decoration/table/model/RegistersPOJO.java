package parser.decoration.table.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RegistersPOJO {

    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;

    private ValuePOJO value;

    public String getName() {
        return name;
    }

    public ValuePOJO getValue() {
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
