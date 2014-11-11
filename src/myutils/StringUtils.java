package myutils;

public class StringUtils {
	public static String center(String str, int n) {
		String result = new String(str);
		while (result.length() < n) {
			result = " " + result;
			if (result.length() < n)
				result += " ";
		}
		return result;
	}

	public static boolean isNullOrEmpty(String string) {
		if (string == null)
			return true;
		return (string == "");
	}
}
