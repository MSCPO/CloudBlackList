package cn.etstmc.cloudblacklist.network;

import cn.etstmc.cloudblacklist.Kernel;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() >= 10) {
            int type = in.readInt();
            int subType = in.readInt();
            int bodyLength = in.readShort();
            if (in.readableBytes() >= bodyLength) {
                byte[] bodyBytes = new byte[bodyLength];
                in.readBytes(bodyBytes);
                String body = new String(bodyBytes, StandardCharsets.UTF_8);
                Packet packet = Kernel.packetManager.getPacket(Kernel.packetManager.getPacketType(type).getPacket(subType),
                        body);
                out.add(packet);
            }
        }
    }
}
