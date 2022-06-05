package net.avatarverse.avatarversalis.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Listener for handling player related events
 * (Or maybe such listeners are unnecessary here and AV core can handle them instead)
 */

public class UserListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {

    }
    
}
