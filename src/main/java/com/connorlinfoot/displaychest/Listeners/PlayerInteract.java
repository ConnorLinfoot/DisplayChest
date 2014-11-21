package com.connorlinfoot.displaychest.Listeners;

import com.connorlinfoot.displaychest.DisplayChest;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class PlayerInteract implements Listener {
    public static ArrayList<String> current = new ArrayList<String>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block == null) return;
        if (block.getType() != Material.CHEST) return;
        Integer ChestX = block.getX();
        Integer ChestY = block.getY();
        Integer ChestZ = block.getZ();
        String chestID = String.valueOf(ChestX + "." + ChestY + "." + ChestZ);
        Plugin plugin = DisplayChest.getInstance();
        FileConfiguration config = plugin.getConfig();
		
		final Player player = event.getPlayer();
        if (!config.isSet("Chests." + chestID)) {
			if (player.isSneaking() && player.hasPermission("displaycheat.edit")){
				/* Turn a nornal chest into a display chest if clicked while sneaking */
				Plugin plugin = DisplayChest.getInstance();
				FileConfiguration config = plugin.getConfig();
				config.set("Chests." + chestID, true);
				plugin.saveConfig();
				player.sendMessage(ChatColor.GREEN + "Your DisplayChest has been created, right click while holding shift to edit the contents");
				event.setCancelled(true);
			}
			return;
		}

        if (!player.hasPermission("displaychest.view") && !player.hasPermission("displaychest.edit")) {
            event.setCancelled(true);
            return;
        }

        if (player.isSneaking() && player.hasPermission("displaychest.edit")) return;
        Chest chest = (Chest) block.getState();
        final Inventory chestInventory = chest.getInventory();

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            public void run() {
                player.openInventory(chestInventory);
                PlayerInteract.current.add(player.getName());
            }
        }, 1L);
    }

}
