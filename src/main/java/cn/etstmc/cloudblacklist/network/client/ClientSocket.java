package cn.etstmc.cloudblacklist.network.client;

import cn.etstmc.cloudblacklist.network.PacketDecoder;
import cn.etstmc.cloudblacklist.network.PacketEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientSocket {
    private final String host;
    private final int port;
    private final TCPClient client;
    private EventLoopGroup group;

    public ClientSocket(String host, int port) {
        this.host = host;
        this.port = port;
        this.client = new TCPClient();
    }


    public void start() throws Exception {
        group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new PacketEncoder());
                            ch.pipeline().addLast(new PacketDecoder());
                            ch.pipeline().addLast(client);
                            Thread.currentThread().setName("BL-Client-Thread");
                        }
                    });
            ChannelFuture future = bootstrap.connect(host, port).sync();
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    public TCPClient getClient() {
        return client;
    }

    public void kill () {
        if (group != null && !group.isShutdown()) group.shutdownGracefully();
    }
}
