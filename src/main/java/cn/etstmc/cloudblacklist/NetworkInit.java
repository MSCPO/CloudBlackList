package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.network.client.packets.types.DataBasePacketType;
import cn.etstmc.cloudblacklist.network.client.packets.types.HandShakePacketType;

public class NetworkInit {
    public static void init () {
        Kernel.packetManager.registerType(new HandShakePacketType(), HandShakePacketType.type);
        Kernel.packetManager.registerType(new DataBasePacketType(), DataBasePacketType.type);
    }
}
