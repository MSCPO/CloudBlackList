package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.network.client.packets.ServerBoundHandShakePacket;
import cn.etstmc.cloudblacklist.network.packetlisteners.clientbound.HandShakePacketListener;
import cn.etstmc.cloudblacklist.network.packettyps.DataBasePacketType;
import cn.etstmc.cloudblacklist.network.packettyps.HandShakePacketType;
import cn.etstmc.cloudblacklist.network.server.packets.ClientBoundHandShakePacket;
import io.netty.channel.ChannelHandlerContext;
import org.bukkit.Bukkit;

public class NetworkInit {
    public static void init () {
        //
        Kernel.packetManager.registerType(new HandShakePacketType(), HandShakePacketType.type);
        Kernel.packetManager.registerType(new DataBasePacketType(), DataBasePacketType.type);
        //
        Register.registerPacket(HandShakePacketType.class, ServerBoundHandShakePacket.class, 0);
        Register.registerPacket(HandShakePacketType.class, ClientBoundHandShakePacket.class, 1);
        //
        Kernel.networkManager.getClient().registerPacketListener(HandShakePacketType.class, new HandShakePacketListener());
        //
        new Thread (() -> {
            while (Kernel.networkManager.getClient().getSERVER_CHANNEL() == null) {
                Thread.currentThread().setName("WAITING");
            }
            System.out.println("sending");
            ChannelHandlerContext SC = Kernel.networkManager.getClient().getSERVER_CHANNEL();
            SC.writeAndFlush(new ServerBoundHandShakePacket("TEST",
                    Kernel.plugin.getDescription().getVersion(),
                    Bukkit.getBukkitVersion(),
                    Kernel.server.getVersion()));
        }).start();
    }
}
