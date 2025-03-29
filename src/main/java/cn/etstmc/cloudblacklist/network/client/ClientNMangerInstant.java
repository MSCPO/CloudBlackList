package cn.etstmc.cloudblacklist.network.client;

import cn.etstmc.cloudblacklist.api.network.client.ClientNetworkManager;

public class ClientNMangerInstant implements ClientNetworkManager {
    private final ClientSocket socket;

    public ClientNMangerInstant (String host, int port) {
        socket = new ClientSocket(host, port);
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
