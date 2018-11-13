package parser.common;

public class ParsingError<T> {

    private T errorType;
    private String errorMsg;

    public ParsingError(T errorType, String errorMsg) {
        this.errorType = errorType;
        this.errorMsg = errorMsg;
    }

    public T getErrorType() {
        return errorType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "ParsingError{" +
                "errorType=" + errorType +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
