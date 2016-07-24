package myutils.exception;

@SuppressWarnings({"WeakerAccess", "UnusedDeclaration"})
public class DuplicatedException extends Exception {
  private final String message;

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
