package fish.global.util;

public class StringUtil {
    public static String NVL(Object value) {
        return value != null ? value.toString() : null;
    }
}
