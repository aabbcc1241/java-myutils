package myutils.debug;

import org.lwjgl.Sys;
import org.lwjgl.system.windows.DISPLAY_DEVICE;

import java.io.PrintStream;
import java.util.Calendar;

/**
 * Created by beenotung on 15/5/2015.
 */
public class Debug {
  public static final int ERROR = 0x01;
  public static final int WARNING = 0x02;
  public static final int INFO = 0x03;
  public static final int DEBUG = 0x04;
  public static final int RELEASE = INFO;
  public static final int DEV = ERROR | WARNING | INFO | DEBUG;
  public static int Display_Mask = DEV;
  public static final ErrorPair ERROR_PAIR_FILE_READ = ErrorPair.newErrorPair("Failed to read from the file.");
  public static final ErrorPair ERROR_PAIR_FILE_WRITE = ErrorPair.newErrorPair("Failed to save to the file.");
  public static final ErrorPair ERROR_PAIR_FILE_DOWNLOAD = ErrorPair.newErrorPair("Failed to download the file.");

//  public static String MODE_DEBUG = "Debug Mode";
//  public static String MODE_RELEASE = "Release Mode";
//  public static String MODE = MODE_DEBUG;

  public static void logd(Object tag, Object o) {
    log(System.out, DEBUG, tag, o);
  }

  public static void loge(Object tag, Object o) {
    log(System.err, ERROR, tag, o);
  }

  public static void logw(Object tag, Object o) {
    log(System.out, WARNING, tag, o);
  }

  public static void logi(Object tag, Object o) {
    log(System.out, INFO, tag, o);
  }

  private static void log(PrintStream printStream, int mask, Object tag, Object o) {
    if ((mask & Display_Mask) != 0)
      printStream.println(Calendar.getInstance().toInstant().toString()
          + ":\t" + tag.toString()
          + ":\t" + o.toString());
  }
}
