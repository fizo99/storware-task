public class FileLoadingException extends RuntimeException {
    FileLoadingException(String msg) {
        super(msg);
    }

    FileLoadingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
