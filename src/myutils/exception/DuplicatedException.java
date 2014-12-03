package myutils.exception;

public class DuplicatedException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public String message;

    public DuplicatedException() {
        message = "The record is Duplicated !";
    }

    public DuplicatedException(String msg) {
        message = msg;
    }

    @Override
    public String toString() {
        return message;
    }
}
