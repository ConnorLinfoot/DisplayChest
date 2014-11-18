package com.connorlinfoot.displaychest.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (PlayerInteract.current.contains(player.getName())) {
            PlayerInteract.current.remove(player.getName());
        }
    }
}
