package cn.etstmc.cloudblacklist.network;

import cn.etstmc.cloudblacklist.utils.ExceptionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.etstmc.cloudblacklist.Kernel.logger;

public class PacketManager {
    private final Map<Integer, PacketType> types;
    private final Map<String, PacketType> stringTypes;

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

    public <P extends Packet> P getPacket (Class<P> clazz, int type, int subtype, String body) {
        try {
            return clazz.getDeclaredConstructor(int.class, int.class, String.class).newInstance(type, subtype, body);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.warning("收到无法解析的网络数据包：\n" + ExceptionUtils.getStackTrace(e.getCause()));
            return null;
        }
    }
}
