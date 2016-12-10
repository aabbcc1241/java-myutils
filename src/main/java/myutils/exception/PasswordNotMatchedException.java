package myutils.exception;

@SuppressWarnings({"WeakerAccess", "UnusedDeclaration"})
public class PasswordNotMatchedException extends Exception {
  private final String message;

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
