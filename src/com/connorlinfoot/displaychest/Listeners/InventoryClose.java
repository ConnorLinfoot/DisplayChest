package com.connorlinfoot.displaychest.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player) event.getPlayer();

        if( PlayerInteract.current.contains( player.getName() ) ){
            if( event.getInventory().getTitle().contains("DisplayChest") ) PlayerInteract.current.remove( player.getName() );
        }
    }
}
