package myutils;

import myutils.google.GoogleUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Vector;

public class FileUtils {

    public static List<String> readFile(Path path) throws IOException {
        return Files.readAllLines(path, Charset.defaultCharset());
    }

    public static List<String> readFile(URL url) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(
                url.openStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Vector<String> lines = new Vector<String>();
        String line;
        while ((line = bufferedReader.readLine()) != null)
            lines.add(line);
        bufferedReader.close();
        inputStreamReader.close();
        return lines;
    }

    public static String getStringFromUrlPlain(String url)
            throws MalformedURLException, IOException {
        return Utils.StringListToString(FileUtils.readFile(new URL(url)), " ");
    }

    public static String getStringFromUrlGoogleWeb(String url)
            throws MalformedURLException, IOException {
        return Utils.StringListToString(
                GoogleUtils.getContentFromGDoc(new URL(url)), " ");
    }
}
