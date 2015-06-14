package myutils.debug;

import java.util.Calendar;

/**
 * Created by hkpu on 15/5/2015.
 */
public class Debug {
    public static final ErrorPair ERROR_PAIR_FILE_READ = ErrorPair.newErrorPair("Failed to read from the file.");
    public static final ErrorPair ERROR_PAIR_FILE_WRITE = ErrorPair.newErrorPair("Failed to save to the file.");
    public static final ErrorPair ERROR_PAIR_FILE_DOWNLOAD = ErrorPair.newErrorPair("Failed to download the file.");

    public static String MODE_DEBUG = "Debug Mode";
    public static String MODE_RELEASE = "Release Mode";
    public static String MODE = MODE_DEBUG;

    public static void println(String msg) {
        if (MODE == MODE_DEBUG)
            System.out.println(msg);
    }

    public static void showMessage(String msg) {
        Debug.println(Calendar.getInstance().toInstant().toString() + "\t" + msg);
    }
}
