package cn.etstmc.cloudblacklist.network.client.packets;

import cn.etstmc.cloudblacklist.api.minecraftserver.RunMode;
import cn.etstmc.cloudblacklist.api.minecraftserver.ServerType;
import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.packettyps.HandShakePacketType;
import cn.etstmc.cloudblacklist.utils.Json;

import java.util.HashMap;
import java.util.Map;

import static cn.etstmc.cloudblacklist.network.PacketManager.gson;

public class ServerBoundHandShakePacket extends Packet {
    private String serverName, pluginVersion, apiVersion, serverVersion;
    private ServerType type;
    private RunMode mode;

    public ServerBoundHandShakePacket (String serverName, String pluginVersion, String bukkitVersion, String serverVersion, ServerType type, RunMode mode) {
        super (HandShakePacketType.type, 0, body(serverName, pluginVersion, bukkitVersion, serverVersion, type, mode));
        this.serverName = serverName;
        this.pluginVersion = pluginVersion;
        this.apiVersion = bukkitVersion;
        this.serverVersion = serverVersion;
        this.type = type;
        this.mode = mode;
    }

    private static String body (String serverName, String pluginVersion, String bukkitVersion, String serverVersion, ServerType type, RunMode mode) {
        Map<String, Object> data = new HashMap<>();
        data.put("ServerName", serverName);
        data.put("PluginVersion", pluginVersion);
        data.put("BukkitVersion", bukkitVersion);
        data.put("ServerVersion", serverVersion);
        data.put("ServerType", type.toString());
        data.put("RunMode", mode.toString());
        return gson.toJson(data);
    }

    @packet
    public ServerBoundHandShakePacket (String body) {
        super(HandShakePacketType.type, 0, body);
        Json.DecodedJson data = Json.decoded(body);
        if (data == null) return;
        this.serverName = data.getString("ServerName");
        this.pluginVersion = data.getString("PluginVersion");
        this.apiVersion = data.getString("BukkitVersion");
        this.serverVersion = data.getString("ServerVersion");
        this.type = data.getEnum(ServerType.class, "ServerType");
        this.mode = data.getEnum(RunMode.class, "RunMode");
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

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String bukkitVersion) {
        this.apiVersion = bukkitVersion;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public ServerType getServerType() {
        return type;
    }

    public void setType(ServerType type) {
        this.type = type;
    }

    public RunMode getMode() {
        return mode;
    }

    public void setMode(RunMode mode) {
        this.mode = mode;
    }
}
