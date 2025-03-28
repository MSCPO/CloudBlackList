package cn.etstmc.cloudblacklist.network.client;

import cn.etstmc.cloudblacklist.network.Packet;
import cn.etstmc.cloudblacklist.utils.ExceptionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import static cn.etstmc.cloudblacklist.Kernel.logger;

public class TCPClient extends SimpleChannelInboundHandler<Packet> {
    private ChannelHandlerContext SERVER_CHANNEL;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SERVER_CHANNEL = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("数据库网络连接发生异常（" + ctx.channel().remoteAddress() + "）：\n" + ExceptionUtils.getStackTrace(cause));
        ctx.close();
    }

    public ChannelHandlerContext getSERVER_CHANNEL() {
        return SERVER_CHANNEL;
    }

    public void setSERVER_CHANNEL(ChannelHandlerContext SERVER_CHANNEL) {
        this.SERVER_CHANNEL = SERVER_CHANNEL;
    }
}
