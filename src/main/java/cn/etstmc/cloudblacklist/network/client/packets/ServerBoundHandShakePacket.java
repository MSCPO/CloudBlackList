package cn.etstmc.cloudblacklist.network.client.packets;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.packettyps.HandShakePacketType;
import cn.etstmc.cloudblacklist.utils.ArgumentUtils;
import cn.etstmc.cloudblacklist.utils.Json;

import java.util.HashMap;
import java.util.Map;

import static cn.etstmc.cloudblacklist.network.PacketManager.gson;

public class ServerBoundHandShakePacket extends Packet {
    private String serverName, pluginVersion, bukkitVersion, serverVersion;

    public ServerBoundHandShakePacket (String serverName, String pluginVersion, String bukkitVersion, String serverVersion) {
        super (HandShakePacketType.type, 0, body(serverName, pluginVersion, bukkitVersion, serverVersion));
        this.serverName = serverName;
        this.pluginVersion = pluginVersion;
        this.bukkitVersion = bukkitVersion;
        this.serverVersion = serverVersion;
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
        Map<String, Object> data = Json.decodeJson(body);
        if (data.isEmpty()) return;
        ArgumentUtils.checkArgument(data.containsKey("ServerName") && data.get("ServerName") instanceof String,
                "解码数据包时发现不完整的数据包");
        ArgumentUtils.checkArgument(data.containsKey("PluginVersion") && data.get("PluginVersion") instanceof String,
                "解码数据包时发现不完整的数据包");
        ArgumentUtils.checkArgument(data.containsKey("BukkitVersion") && data.get("BukkitVersion") instanceof String,
                "解码数据包时发现不完整的数据包");
        ArgumentUtils.checkArgument(data.containsKey("ServerVersion") && data.get("ServerVersion") instanceof String,
                "解码数据包时发现不完整的数据包");
        this.serverName = (String) data.get("ServerName");
        this.pluginVersion = (String) data.get("PluginVersion");
        this.bukkitVersion = (String) data.get("BukkitVersion");
        this.serverVersion = (String) data.get("ServerVersion");
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public void setPluginVersion(String pluginVersion) {
        this.pluginVersion = pluginVersion;
    }

    public String getBukkitVersion() {
        return bukkitVersion;
    }

    public void setBukkitVersion(String bukkitVersion) {
        this.bukkitVersion = bukkitVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }
}
