package fish.common.util;

public class StringUtil {
    public static String NVL(Object value) {
        return value != null ? value.toString() : null;
    }
}
