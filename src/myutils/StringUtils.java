package myutils;

@SuppressWarnings("UnusedDeclaration")
class StringUtils {
    public static String center(String str, int n) {
        @SuppressWarnings("RedundantStringConstructorCall") String result = new String(str);
        while (result.length() < n) {
            result = " " + result;
            if (result.length() < n)
                result += " ";
        }
        return result;
    }
}
