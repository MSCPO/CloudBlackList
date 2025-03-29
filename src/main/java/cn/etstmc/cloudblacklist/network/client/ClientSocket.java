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

    public ClientSocket(String host, int port) {
        this.host = host;
        this.port = port;
    }


    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
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
                            ch.pipeline().addLast(new TCPClient());
                        }
                    });

            // 启动客户端
            ChannelFuture future = bootstrap.connect(host, port).sync();

            // 等待客户端关闭
            future.channel().closeFuture().sync();
        } finally {
            // 优雅关闭线程池
            group.shutdownGracefully();
        }
    }
}
