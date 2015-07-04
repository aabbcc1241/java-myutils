package myutils.debug;

import org.lwjgl.Sys;

import java.io.PrintStream;
import java.util.Calendar;

/**
 * Created by beenotung on 15/5/2015.
 */
public class Debug {
    public static final int RELEASE = 1;
    public static final int DEBUG = 2;
    public static int DISPLAY_LEVEL = DEBUG;
    public static final ErrorPair ERROR_PAIR_FILE_READ = ErrorPair.newErrorPair("Failed to read from the file.");
    public static final ErrorPair ERROR_PAIR_FILE_WRITE = ErrorPair.newErrorPair("Failed to save to the file.");
    public static final ErrorPair ERROR_PAIR_FILE_DOWNLOAD = ErrorPair.newErrorPair("Failed to download the file.");

    public static String MODE_DEBUG = "Debug Mode";
    public static String MODE_RELEASE = "Release Mode";
    public static String MODE = MODE_DEBUG;

    public static void logd(Object tag, Object o) {
        if (DISPLAY_LEVEL >= DEBUG)
            log(System.out, tag, o);
    }

    public static void loge(Object tag, Object o) {
        if (DISPLAY_LEVEL >= DEBUG)
            log(System.err, tag, o);
    }

    public static void logw(Object tag, Object o) {
        if (DISPLAY_LEVEL >= RELEASE) {
            log(System.out, tag, o);
        }
    }

    public static void log(PrintStream printStream, Object tag, Object o) {
        printStream.println(Calendar.getInstance().toInstant().toString()
                + ":\t" + tag.toString()
                + ":\t" + o.toString());
    }
}
