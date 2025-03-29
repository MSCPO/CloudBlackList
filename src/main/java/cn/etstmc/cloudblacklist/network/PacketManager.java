package cn.etstmc.cloudblacklist.network;

import cn.etstmc.cloudblacklist.utils.ExceptionUtils;
import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.etstmc.cloudblacklist.Kernel.logger;

public class PacketManager {
    private final Map<Integer, PacketType> types;
    private final Map<String, PacketType> stringTypes;
    public static final Gson gson = new Gson();

    public PacketManager () {
        types = new ConcurrentHashMap<>();
        stringTypes = new ConcurrentHashMap<>();
    }

    public void registerType (PacketType type, int id) {
        types.put(id, type);
        stringTypes.put(type.getClass().getSimpleName(), type);
    }

    public PacketType getPacketType (int id) {
        return types.get(id);
    }

    public PacketType getPacketType (Class<? extends PacketType> clazz) {
        return stringTypes.get(clazz.getSimpleName());
    }

    public <P extends Packet> P getPacket (Class<P> clazz, String body) {
        if (clazz == null) return null;
        try {
            return clazz.getDeclaredConstructor(String.class).newInstance(body);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.warning("收到无法解析的网络数据包：\n" + ExceptionUtils.getStackTrace(e.getCause()));
            return null;
        }
    }
}
