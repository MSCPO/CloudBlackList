package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.PacketType;

public class Register {
    public static void registerPacket (Class<? extends PacketType> type, Class<? extends Packet> packet, int id) {
        Kernel.packetManager.getPacketType(type).registerPacket(id, packet);
    }

    public static void init () {

    }
}
