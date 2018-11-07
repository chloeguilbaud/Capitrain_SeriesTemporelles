package parser.seed.transducer.error.manager;

/**
 * Seed transducer parsing error representation containing error type ({@link SeedTransducerParsingErrorType})
 * and error message.
 * @author Chloé GUILBAUD & Maël MAINCHAIN
 */
public class SeedTransducerParsingError {

    private SeedTransducerParsingErrorType errorType;
    private String errorMsg;

    public SeedTransducerParsingError(SeedTransducerParsingErrorType errorType, String errorMsg) {
        this.errorType = errorType;
        this.errorMsg = errorMsg;
    }

    public SeedTransducerParsingErrorType getErrorType() {
        return errorType;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    @Override
    public String toString() {
        return "SeedTransducerParsingError{" +
                "errorType=" + errorType +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
