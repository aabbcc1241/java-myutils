package myutils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Vector;

import sun.misc.IOUtils;

import com.sun.corba.se.spi.orbutil.fsm.Input;

public class FileUtils {

	public static List<String> readFile(Path path) throws IOException {
		return Files.readAllLines(path, Charset.defaultCharset());
	}

	public static List<String> readFile(URL url) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		Vector<String> lines = new Vector<String>();
		String line;
		while ((line = bufferedReader.readLine()) != null)
			lines.add(line);
		bufferedReader.close();
		inputStreamReader.close();
		return lines;
	}
}
