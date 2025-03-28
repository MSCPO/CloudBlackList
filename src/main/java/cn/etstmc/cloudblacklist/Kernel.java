package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.api.network.client.ClientNetworkManager;
import cn.etstmc.cloudblacklist.network.client.ClientNMangerInstant;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Kernel extends JavaPlugin {
    public static Plugin plugin;
    public static Server server;
    public static Logger logger;
    public static ClientNetworkManager networkManager;
    public static PluginManager pluginManager;

    @Override
    public void onEnable () {
        plugin = getPlugin(getClass());
        server = getServer();
        logger = getLogger();
        networkManager = new ClientNMangerInstant();
        pluginManager = server.getPluginManager();
    }

    @Override
    public void onDisable () {

    }
}
