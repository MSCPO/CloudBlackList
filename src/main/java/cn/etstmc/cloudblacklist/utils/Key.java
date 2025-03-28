package cn.etstmc.cloudblacklist.utils;


import cn.etstmc.cloudblacklist.Kernel;

public class Key {
    public static String config (String path) {
        return Text.recolor(Kernel.config.getString(path).replace("%msg-prefix%", Kernel.config.getString("msg-prefix")));
    }

    public static String config (String... path) {
        StringBuilder sb = new StringBuilder();
        for (String s : path) sb.append(s).append(".");
        sb.setLength(sb.length() - 1);
        return config(sb.toString());
    }
}
