package parser.seed.transducer;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class POJO {

    public String name;
    public String init_state;
    public LinkedHashMap testlist;
    public ArrayList testTab;


    /*public SeedTransducerPOJO(String init_state, String name) {
        this.name = name;
        this.init_state = init_state;
    }*/

    @Override
    public String toString() {
        return "POJO{" +
                "name='" + name + '\'' +
                ", init_state='" + init_state + '\'' +
                ", testlist=" + testlist +
                ", testTab=" + testTab +
                '}';
    }
}
