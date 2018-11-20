package generator.error;

public abstract class GeneratorErrorHandler<T> {

    public abstract void manageError(GeneratorResult res, T err, String... msg);

}
