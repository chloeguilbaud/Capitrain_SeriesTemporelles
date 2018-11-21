package generator.error;

/**
 * Error Generator object representation registering error type and message.
 * @param <T> The error enumeration type
 * @author Chloe GUILBAUD &amp; Mael MAINCHAIN
 */
public class GeneratorError<T> {

        private T errorType;
        private String errorMsg;

        public GeneratorError(T errorType, String errorMsg) {
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
            return "GeneratorError{" +
                    "errorType=" + errorType +
                    ", errorMsg='" + errorMsg + '\'' +
                    '}';
        }

}
