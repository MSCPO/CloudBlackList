package cn.etstmc.cloudblacklist.api.network;

import cn.etstmc.cloudblacklist.network.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.internal.TypeParameterMatcher;

public abstract class PacketListener<P extends Packet> {
    private final TypeParameterMatcher matcher;


    public PacketListener () {
        matcher = TypeParameterMatcher.find(this, PacketListener.class, "P");
    }

    public void checkPacket(Object msg, ChannelHandlerContext ctx) {
        if (matcher.match(msg)) onPacket((P) msg, ctx);
    }

    public abstract void onPacket (P packet, ChannelHandlerContext ctx);
}
