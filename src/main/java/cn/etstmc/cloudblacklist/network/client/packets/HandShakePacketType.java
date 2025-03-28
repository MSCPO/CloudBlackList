package cn.etstmc.cloudblacklist.network.client.packets;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.PacketType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandShakePacketType extends PacketType {
    private final Map<Integer, Class<? extends Packet>> types;

    public HandShakePacketType() {
        this.types = new ConcurrentHashMap<>();
    }

    @Override
    public Class<? extends Packet> getPacketType(int subtype) {
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
