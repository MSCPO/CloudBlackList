package cn.etstmc.cloudblacklist.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public class Json {
    public static Map<String, Object> decodeJson (String json) {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Object>>() {
        }.getType();
        return gson.fromJson(json, mapType);
    }
}
