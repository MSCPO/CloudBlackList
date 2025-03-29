package cn.etstmc.cloudblacklist.network.server.packets;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.network.packettyps.HandShakePacketType;
import cn.etstmc.cloudblacklist.utils.ArgumentUtils;
import cn.etstmc.cloudblacklist.utils.Json;

import java.util.HashMap;
import java.util.Map;

import static cn.etstmc.cloudblacklist.network.PacketManager.gson;

public class ClientBoundHandShakePacket extends Packet {
    boolean isSuccess;
    String dbsVersion;

    public ClientBoundHandShakePacket(boolean isSuccess, String databaseServerVersion) {
        super(HandShakePacketType.type, 1, body(isSuccess, databaseServerVersion));
        this.isSuccess = isSuccess;
        this.dbsVersion = databaseServerVersion;
    }

    private static String body (boolean isSuccess, String databaseServerVersion) {
        Map<String, Object> data = new HashMap<>();
        data.put("IsSuccess", isSuccess);
        data.put("DataBaseServerVersion", databaseServerVersion);
        return gson.toJson(data);
    }

    @packet
    public ClientBoundHandShakePacket (String body) {
        super(HandShakePacketType.type, 1, body);
        Map<String, Object> data = Json.decodeJson(body);
        if (data.isEmpty()) return;
        ArgumentUtils.checkArgument(data.containsKey("IsSuccess") && data.get("IsSuccess") instanceof Boolean,
                "解码数据包时发现不完整的数据包");
        ArgumentUtils.checkArgument(data.containsKey("DataBaseServerVersion") && data.get("DataBaseServerVersion") instanceof String,
                "解码数据包时发现不完整的数据包");
        this.isSuccess = (Boolean) data.get("IsSuccess");
        this.dbsVersion = (String) data.get("DataBaseServerVersion");
    }

    public boolean isSuccess () {
        return isSuccess;
    }

    public String getDbsVersion () {
        return dbsVersion;
    }
}
