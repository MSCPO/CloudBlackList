package cn.etstmc.cloudblacklist.utils;

import java.util.Map;

public class ArgumentUtils {
    public static void checkArgument (boolean condition, String msg) throws IllegalArgumentException {
        if (!condition) throw new IllegalArgumentException(msg);
    }

    public static <T> void checkArgument (String s, Map<String, ?> map, Class<T> clazz) throws IllegalArgumentException {
        checkArgument(map.containsKey(s) && clazz.isInstance(map.get(s)), "参数异常：在字典中没有找到匹配的项！");
    }

    public static void checkArgument (String s, Map<String, ?> map) throws IllegalArgumentException {
        checkArgument(map.containsKey(s), "参数异常：在字典中没有找到匹配的项！");
    }
}
