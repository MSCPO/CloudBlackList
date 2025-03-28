package cn.etstmc.cloudblacklist.listeners;

import cn.etstmc.cloudblacklist.utils.Key;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEventListeners implements Listener {
    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player p = event.getPlayer();
        p.sendMessage(Key.config("player.join.msg"));
    }
}
