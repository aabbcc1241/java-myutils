package myutils.debug;

/**
 * Created by beenotung on 6/14/15.
 */
public class ErrorPair {
  public int errorId;
  public String errorMessage;
  private static int count = 0;

  public synchronized static ErrorPair newErrorPair(String errorMessage) {
    return new ErrorPair(++count, errorMessage);
  }

  public ErrorPair(int errorId, String errorMessage) {
    this.errorId = errorId;
    this.errorMessage = errorMessage;
  }

  public static int errorCodeLength = 3;
  private static final String BASE = "Error Code: ";

  public static String errorCode(int code) {
    String CODE = String.valueOf(code);
    while (CODE.length() < errorCodeLength)
      CODE = "0" + CODE;
    return "(" + BASE + CODE + ")";
  }

  public static String generalErrorMessage(int errorId) {
    return "Please retry, or refer to the user manual " + errorCode(errorId);
  }
}
