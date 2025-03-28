package cn.etstmc.cloudblacklist.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.StandardCharsets;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(packet.getType());
        byteBuf.writeInt(packet.getSubType());
        String body = packet.getBody();
        if (body != null) {
            byte[] bodyBytes = body.getBytes(StandardCharsets.UTF_8);
            byteBuf.writeShort(bodyBytes.length);
            byteBuf.writeBytes(bodyBytes);
        } else {
            byteBuf.writeShort(0);
        }
    }
}
