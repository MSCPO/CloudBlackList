package cn.etstmc.cloudblacklist.network.client.packets;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.client.packets.types.HandShakePacketType;

import java.util.HashMap;
import java.util.Map;

import static cn.etstmc.cloudblacklist.network.PacketManager.gson;

public class ServerBoundHandShakePacket extends Packet {
    public ServerBoundHandShakePacket (String serverName, String pluginVersion, String bukkitVersion, String serverVersion) {
        super (HandShakePacketType.type, 0, body(serverName, pluginVersion, bukkitVersion, serverVersion));
    }

    private static String body (String serverName, String pluginVersion, String bukkitVersion, String serverVersion) {
        Map<String, Object> data = new HashMap<>();
        data.put("ServerName", serverName);
        data.put("PluginVersion", pluginVersion);
        data.put("BukkitVersion", bukkitVersion);
        data.put("ServerVersion", serverVersion);
        return gson.toJson(data);
    }

    @packet
    public ServerBoundHandShakePacket (String body) {
        super(HandShakePacketType.type, 0, body);
    }
}
