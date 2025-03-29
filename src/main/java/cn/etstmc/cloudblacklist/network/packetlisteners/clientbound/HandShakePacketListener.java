package cn.etstmc.cloudblacklist.network.packetlisteners.clientbound;

import cn.etstmc.cloudblacklist.api.network.PacketListener;
import cn.etstmc.cloudblacklist.network.server.packets.ClientBoundHandShakePacket;
import io.netty.channel.ChannelHandlerContext;

import static cn.etstmc.cloudblacklist.Kernel.logger;

public class HandShakePacketListener extends PacketListener<ClientBoundHandShakePacket> {
    @Override
    public void onPacket(ClientBoundHandShakePacket packet, ChannelHandlerContext ctx) {
        if (!packet.isSuccess()) {
            logger.warning("与数据库服务器握手失败！");
            return;
        }
        logger.info("与数据库服务器握手成功，数据库服务器版本：" + packet.getDbsVersion());
    }
}
