package cn.etstmc.cloudblacklist.utils;

public class ArgumentUtils {
    public static void checkArgument (boolean condition, String msg) throws IllegalArgumentException {
        if (!condition) throw new IllegalArgumentException(msg);
    }
}
