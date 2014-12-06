package myutils.exception;

public class BlankOrNullException extends Exception {
    private final String message;

    public BlankOrNullException(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}
