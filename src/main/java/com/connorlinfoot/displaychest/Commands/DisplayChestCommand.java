package com.connorlinfoot.displaychest.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DisplayChestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command must be ran as a player");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("displaychest.edit")) {
            player.sendMessage(ChatColor.RED + "You do not have the correct permission to use this command");
            return false;
        }

        ItemStack itemStack = new ItemStack(Material.CHEST);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.WHITE + "<-> DisplayChest <->");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Place down the chest to create a display chest.");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        player.getInventory().addItem(itemStack);
        player.sendMessage(ChatColor.GREEN + "You have been given a DisplayChest");
        return true;
    }

}
