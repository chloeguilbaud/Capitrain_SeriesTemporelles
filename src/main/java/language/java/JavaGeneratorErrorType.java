package language.java;

import generator.error.GeneratorError;
import generator.error.GeneratorResult;

public enum JavaGeneratorErrorType {

    UNKNOWN_ERROR("Unknown JSON parsing error\n%s");

    private String label;

    JavaGeneratorErrorType(String lab) {
        this.label = lab;
    }

    public String getLabel() {
        return label;
    }

    public static void manageError(GeneratorResult res, JavaGeneratorErrorType err, Object...msg) {
        res.addError(new GeneratorError<JavaGeneratorErrorType>(err, String.format(err.getLabel(), msg)));
    }

}
