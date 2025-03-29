package cn.etstmc.cloudblacklist.api.network.client;

import cn.etstmc.cloudblacklist.network.client.ClientSocket;
import cn.etstmc.cloudblacklist.network.client.TCPClient;

public interface ClientNetworkManager {
    ClientSocket getSocket ();
    TCPClient getClient ();
}
