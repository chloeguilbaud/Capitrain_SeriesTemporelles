package generator.error;

public abstract class GeneratorErrorHandler {

    public static void handle(GeneratorResult res, GeneratorErrorType errType, Object...msg) {
        res.addError(new GeneratorError<GeneratorErrorType>(errType, String.format(errType.getLabel(), msg)));
    }

}
