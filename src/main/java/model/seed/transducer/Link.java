package model.seed.transducer;

public class Link {

    private String name;
    private Comparator comparator;
    private String beginState;
    private String endState;
    private SemanticLetter semanticLetter;

    public Link(String name, Comparator comparator, String beginState, String endState, SemanticLetter semanticLetter) {
        this.name = name;
        this.comparator = comparator;
        this.beginState = beginState;
        this.endState = endState;
        this.semanticLetter = semanticLetter;
    }
}
