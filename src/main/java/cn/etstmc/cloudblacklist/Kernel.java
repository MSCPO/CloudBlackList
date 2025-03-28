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
        long start = System.currentTimeMillis();
        logger.info("正在加载插件Kernel Class：" + getClass().getName());
        plugin = getPlugin(getClass());
        server = getServer();
        logger = getLogger();
        networkManager = new ClientNMangerInstant();
        pluginManager = server.getPluginManager();
        logger.info("加载完成，耗时 " + (System.currentTimeMillis() - start) + " ms");
    }

    @Override
    public void onDisable () {
        logger.info("正在卸载插件 MSCPO-CloudBlackList");
    }
}
