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

    public static DecodedJson decoded (String json) {
        return json == null ? null : json.isEmpty() ? null : new DecodedJson(decodeJson(json));
    }

    public static class DecodedJson {
        private final Map<String, Object> map;

        public DecodedJson (Map<String, Object> map) {
            this.map = map;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public String getString (String path) {
            ArgumentUtils.checkArgument(path, map, String.class);
            return (String) map.get(path);
        }

        public boolean getBoolean (String path) {
            ArgumentUtils.checkArgument(path, map, boolean.class);
            return (boolean) map.get(path);
        }

        public int getInt (String path) {
            ArgumentUtils.checkArgument(path, map, int.class);
            return (int) map.get(path);
        }

        public float getFloat (String path) {
            ArgumentUtils.checkArgument(path, map, float.class);
            return (float) map.get(path);
        }

        public long getLong (String path) {
            ArgumentUtils.checkArgument(path, map, long.class);
            return (long) map.get(path);
        }

        public <C extends Enum<C>> C getEnum (Class<C> clazz, String path) {
            ArgumentUtils.checkArgument(path, map);
            return C.valueOf(clazz, String.valueOf(map.get(path)));
        }
    }
}
