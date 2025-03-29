package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.network.client.packets.ServerBoundHandShakePacket;
import cn.etstmc.cloudblacklist.network.packetlisteners.clientbound.HandShakePacketListener;
import cn.etstmc.cloudblacklist.network.packettyps.DataBasePacketType;
import cn.etstmc.cloudblacklist.network.packettyps.HandShakePacketType;

public class NetworkInit {
    public static void init () {
        //
        Kernel.packetManager.registerType(new HandShakePacketType(), HandShakePacketType.type);
        Kernel.packetManager.registerType(new DataBasePacketType(), DataBasePacketType.type);
        //
        Register.registerPacket(HandShakePacketType.class, ServerBoundHandShakePacket.class, 0);
        //
        Kernel.networkManager.getClient().registerPacketListener(HandShakePacketType.class, new HandShakePacketListener());
        //
    }
}
