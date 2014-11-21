package myutils.exception;

public class PasswordNotMatchedException extends Exception {
	public String message;

	public PasswordNotMatchedException() {
		message = "Password not matched please type again";
	}

	public PasswordNotMatchedException(String msg) {
		message = msg;
	}

	@Override
	public String toString() {
		return message;
	}
}
