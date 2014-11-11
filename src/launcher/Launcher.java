package launcher;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import myutils.FileUtils;
import myutils.Utils;
import myutils.google.GoogleUtils;

public class Launcher {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("main start");

		/** call my class **/
		URL url = new URL(
				"https://docs.google.com/document/d/1_lPh3Nc-Y22zX-D-NebqfzzG_w8wh-GGV_lxsk5D3m8/pub");
		List<String> lines = GoogleUtils.getContentFromGDoc(url);
		for (String line : lines)
			System.out.println(line);

		System.out.println("main end");
	}

}
