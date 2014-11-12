package myutils;

public class BlankOrNullException extends Exception {
	public String message;

	public BlankOrNullException(String msg) {
		message = msg;
	}
}
