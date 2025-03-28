package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.network.client.packets.DataBasePacketType;
import cn.etstmc.cloudblacklist.network.client.packets.HandShakePacketType;

public class NetworkInit {
    public static void init () {
        Kernel.packetManager.registerType(new HandShakePacketType(), 0);
        Kernel.packetManager.registerType(new DataBasePacketType(), 1);
    }
}
