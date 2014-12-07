package myutils.google;

import myutils.FileUtils;
import myutils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
/**
 * @author beenotung
 */
public class GoogleUtils {
    public static List<String> getContentFromGDoc(URL url) throws IOException {
        List<String> lines = new ArrayList<>();
        String content = Utils.StringListToString(FileUtils.readFile(url), " ");
        int a, b;
        a = content.indexOf("-=-=-start-=-=-");
        b = content.indexOf("-=-=-end-=-=-");
        content = content.substring(a, b);
        String line;
        while ((a = content.indexOf("<span>")) >= 0) {
            content = content.substring(a + 6);
            b = content.indexOf("</span>");
            if (b == -1)
                return lines;
            line = content.substring(0, b);
            content = content.substring(b + 1);
            lines.add(line);
        }
        return lines;
    }
}
