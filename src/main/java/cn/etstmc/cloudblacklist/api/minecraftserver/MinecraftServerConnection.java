package cn.etstmc.cloudblacklist.api.minecraftserver;

import cn.etstmc.cloudblacklist.network.Packet;
import io.netty.channel.ChannelHandlerContext;

public class MinecraftServerConnection {
    private final String name;
    private final ServerType type;
    private final RunMode mode;
    private final String serverVersion;
    private final String pluginVersion;
    private final String apiVersion;
    private final ChannelHandlerContext ctx;

    public MinecraftServerConnection(String name,
                                     ServerType type,
                                     RunMode mode,
                                     String serverVersion,
                                     String pluginVersion,
                                     String apiVersion,
                                     ChannelHandlerContext ctx) {
        this.name = name;
        this.type = type;
        this.mode = mode;
        this.serverVersion = serverVersion;
        this.pluginVersion = pluginVersion;
        this.apiVersion = apiVersion;
        this.ctx = ctx;
    }

    public void sendPacket (Packet packet) {
        ctx.writeAndFlush(packet);
    }

    public String getName() {
        return name;
    }

    public ServerType getType() {
        return type;
    }

    public RunMode getMode() {
        return mode;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public ChannelHandlerContext getCtx () {
        return ctx;
    }
}
