package myutils.exception;

public class BlankOrNullException extends Exception {
	public String message;

	public BlankOrNullException(String msg) {
		message = msg;
	}

	@Override
	public String toString() {
		return message;
	}
}
