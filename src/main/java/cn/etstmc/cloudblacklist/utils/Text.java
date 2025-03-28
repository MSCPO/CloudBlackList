package cn.etstmc.cloudblacklist.utils;

public class Text {
    public static String recolor (String toColor) {
        return toColor.replace("&", "§").replace("§§", "&");
    }
}
