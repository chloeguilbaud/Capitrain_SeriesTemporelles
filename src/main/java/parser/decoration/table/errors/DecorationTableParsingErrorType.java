package parser.decoration.table.errors;

public class DecorationTableParsingErrorType {

    private String label;

    DecorationTableParsingErrorType(String lab) {
        this.label = lab;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }

}
