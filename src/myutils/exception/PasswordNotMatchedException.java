package myutils.exception;

public class PasswordNotMatchedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
