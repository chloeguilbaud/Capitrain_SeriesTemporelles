package parser.seed.transducer.model;

public class SeedTransducerPOJO {

    public String name;
    public String init_state;

    /*public SeedTransducerPOJO(String init_state, String name) {
        this.name = name;
        this.init_state = init_state;
    }*/

    @Override
    public String toString() {
        return "SeedTransducerPOJO{" +
                "name='" + name + '\'' +
                ", init_state='" + init_state + '\'' +
                '}';
    }
}
