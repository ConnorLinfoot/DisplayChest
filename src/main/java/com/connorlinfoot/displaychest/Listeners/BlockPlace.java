package com.connorlinfoot.displaychest.Listeners;

import com.connorlinfoot.displaychest.DisplayChest;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BlockPlace implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (event.getBlockPlaced().getType() != Material.CHEST) return;

        Player player = event.getPlayer();
        if (!player.hasPermission("displaychest.edit")) return;

        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getItemMeta().getDisplayName() == null || !itemStack.getItemMeta().getDisplayName().contains("<-> DisplayChest <->"))
            return;

        Block block = event.getBlockPlaced();
        Integer ChestX = block.getX();
        Integer ChestY = block.getY();
        Integer ChestZ = block.getZ();
        String chestID = String.valueOf(ChestX + "." + ChestY + "." + ChestZ);
        Plugin plugin = DisplayChest.getInstance();
        FileConfiguration config = plugin.getConfig();
        config.set("Chests." + chestID, true);
        plugin.saveConfig();
        player.sendMessage(ChatColor.GREEN + "Your DisplayChest has been created, right click while holding shift to edit the contents");
    }

}
