package myutils.exception;

public class BlankOrNullException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String message;

    public BlankOrNullException(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}
