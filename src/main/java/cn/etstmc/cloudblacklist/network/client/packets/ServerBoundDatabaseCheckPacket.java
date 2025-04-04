package cn.etstmc.cloudblacklist.network.client.packets;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.packettyps.DataBasePacketType;
import cn.etstmc.cloudblacklist.utils.Json;

import java.util.HashMap;
import java.util.Map;

import static cn.etstmc.cloudblacklist.network.PacketManager.gson;

public class ServerBoundDatabaseCheckPacket extends Packet {
    private String playerName;
    private String uuid;
    private boolean onlineMode;

    public ServerBoundDatabaseCheckPacket(String playerName, String uuid, boolean onlineMode) {
        super(DataBasePacketType.type, 0, body(playerName, uuid, onlineMode));
        this.playerName = playerName;
        this.uuid = uuid;
        this.onlineMode = onlineMode;
    }

    public static String body (String playerName, String uuid, boolean onlineMode) {
        Map<String, Object> data = new HashMap<>();
        data.put("PlayerName", playerName);
        data.put("PlayerUUID", uuid);
        data.put("OnlineMode", onlineMode);
        return gson.toJson(data);
    }

    public ServerBoundDatabaseCheckPacket (String body) {
        super(DataBasePacketType.type, 0, body);
        Json.DecodedJson decoded = Json.decoded(body);
        if (decoded == null) return;
        this.playerName = decoded.getString("PlayerName");
        this.uuid = decoded.getString("PlayerUUID");
        this.onlineMode = decoded.getBoolean("OnlineMode");
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isOnlineMode() {
        return onlineMode;
    }

    public void setOnlineMode(boolean onlineMode) {
        this.onlineMode = onlineMode;
    }
}
