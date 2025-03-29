package cn.etstmc.cloudblacklist.network.client;

import cn.etstmc.cloudblacklist.api.network.client.ClientNetworkManager;
import cn.etstmc.cloudblacklist.utils.ExceptionUtils;

import static cn.etstmc.cloudblacklist.Kernel.logger;

public class ClientNMangerInstant implements ClientNetworkManager {
    private final ClientSocket socket;

    public ClientNMangerInstant (String host, int port) {
        socket = new ClientSocket(host, port);
        new Thread(() -> {
            try {
                Thread.currentThread().setName("CBL-Client");
                socket.start();
            } catch (Exception e) {
                logger.warning("连接到数据库服务器时发生异常：\n" + ExceptionUtils.getStackTrace(e));
            }
        }).start();
    }

    @Override
    public ClientSocket getSocket() {
        return socket;
    }

    @Override
    public TCPClient getClient() {
        return socket.getClient();
    }
}
