package parser.common;

/**
 * Parsing error representation containing the error type and the related message
 * @param <T> The error type enumeration
 */
public abstract class ParsingError<T> {

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
