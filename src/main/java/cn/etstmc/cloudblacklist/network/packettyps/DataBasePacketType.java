package cn.etstmc.cloudblacklist.network.packettyps;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.PacketType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataBasePacketType extends PacketType {
    private final Map<Integer, Class<? extends Packet>> types;
    public static final int type = 1;

    public DataBasePacketType() {
        this.types = new ConcurrentHashMap<>();
    }

    @Override
    public Class<? extends Packet> getPacket(int subtype) {
        return types.get(subtype);
    }

    @Override
    public void registerPacket(int id, Class<? extends Packet> packet) {
        types.put(id, packet);
    }

    public Map<Integer, Class<? extends Packet>> getTypes() {
        return types;
    }
}
