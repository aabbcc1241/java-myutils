package myutils;

import myutils.google.GoogleUtils;

import javax.servlet.http.Cookie;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.Vector;

@SuppressWarnings("UnusedDeclaration")
public class FileUtils {
    public static List<String> readFile(Path path) throws IOException {
        return Files.readAllLines(path, Charset.defaultCharset());
    }

    public static List<String> readFile(URL url) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(
                url.openStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Vector<String> lines = new Vector<>();
        String line;
        while ((line = bufferedReader.readLine()) != null)
            lines.add(line);
        bufferedReader.close();
        inputStreamReader.close();
        return lines;
    }

    public static String getStringFromUrlPlain(String url)
            throws IOException {
        return CollectionUtils.StringListToString(FileUtils.readFile(new URL(url)), " ");
    }

    public static String getStringFromUrlGoogleWeb(String url)
            throws IOException {
        return CollectionUtils.StringListToString(
                GoogleUtils.getContentFromGDoc(new URL(url)), " ");
    }

    public static Set<Cookie> readCookie(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Set<Cookie> result = (Set<Cookie>) in.readObject();
        in.close();
        return result;
    }
}
