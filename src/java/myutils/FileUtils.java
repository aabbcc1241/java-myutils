package myutils;

import myutils.google.GoogleUtils;

import javax.servlet.http.Cookie;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
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

    public static String downloadFile(String url, String filename) throws IOException {
        URL website = new URL(url);
        ReadableByteChannel readableByteChannel = Channels.newChannel(website.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        fileOutputStream.close();
        return filename;
    }

    public static void createDir(String path) {
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }


    public static void deleteFile(String pathname) {
        File file = new File(pathname);
        if (file.exists())
            file.delete();
    }
}
