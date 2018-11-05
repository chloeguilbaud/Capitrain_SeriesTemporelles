package model.seed.transducer;

public class Arc {

    private State from;
    private State to;
    private Operator operator;
    private SemanticLetter semanticLetter;

    public void setFrom(State from) {
        this.from = from;
    }

    public void setTo(State to) {
        this.to = to;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setSemanticLetter(SemanticLetter semanticLetter) {
        this.semanticLetter = semanticLetter;
    }
}
