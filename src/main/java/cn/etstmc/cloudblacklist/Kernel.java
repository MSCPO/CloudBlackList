package cn.etstmc.cloudblacklist;

import cn.etstmc.cloudblacklist.api.network.client.ClientNetworkManager;
import cn.etstmc.cloudblacklist.network.PacketManager;
import cn.etstmc.cloudblacklist.network.client.ClientNMangerInstant;
import io.netty.channel.ChannelHandlerContext;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

public class Kernel extends JavaPlugin {
    public static Plugin plugin;
    public static Server server;
    public static Logger logger;
    public static ClientNetworkManager networkManager;
    public static ChannelHandlerContext SERVER_CHANNEL;
    public static PluginManager pluginManager;
    public static File dataFolder;
    public static YamlConfiguration config;
    public static PacketManager packetManager;

    @Override
    public void onEnable () {
        long start = System.currentTimeMillis();
        logger = getLogger();
        logger.info("正在加载插件Kernel Class：" + getClass().getName());
        plugin = getPlugin(getClass());
        server = getServer();
        pluginManager = server.getPluginManager();
        dataFolder = getDataFolder();
        saveResource("config.yml", false);
        config = YamlConfiguration.loadConfiguration(new File(dataFolder, "config.yml"));
        networkManager = new ClientNMangerInstant(config.getString("network.host"), config.getInt("network.port"));
        packetManager = new PacketManager();
        //
        Register.init();
        NetworkInit.init();
        //
        logger.info("加载完成，耗时 " + (System.currentTimeMillis() - start) + " ms");
    }

    @Override
    public void onDisable () {
        logger.info("正在卸载插件 MSCPO-CloudBlackList");
        networkManager.getSocket().kill();
    }
}
