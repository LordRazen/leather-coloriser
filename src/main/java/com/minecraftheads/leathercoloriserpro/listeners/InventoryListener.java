package com.minecraftheads.leathercoloriserpro.listeners;

import com.minecraftheads.leathercoloriserpro.handlers.InventoryHandler;
import com.minecraftheads.leathercoloriserpro.handlers.SelectionHandler;
import com.minecraftheads.leathercoloriserpro.utils.InventoryCreator;
import com.minecraftheads.leathercoloriserpro.utils.ItemCreator;
import com.minecraftheads.leathercoloriserpro.utils.Logger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InventoryListener implements Listener {
    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        // Check if inventory is a LCP inventory
        if (!InventoryHandler.getInventory().contains(e.getView().getTopInventory())) return;

        // Abort only if the InventoryClick is within the created Inventory
        if (!Objects.equals(e.getClickedInventory(), e.getView().getTopInventory())) return;

        // Item cannot be removed
        e.setCancelled(true);

        // verify current item is not null
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // Using slots click is a best option for your inventory click's
        final Player player = (Player) e.getWhoClicked();

        if (Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName().startsWith("Leather")) {
            SelectionHandler.addItem(player, clickedItem);
            InventoryCreator inv = new InventoryCreator();
            inv.initializeColor();
            inv.openInventory(player);
            return;
        } else if (Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName().startsWith("Dye")) {
            Logger.info(SelectionHandler.getItem((Player) e.getWhoClicked()).toString());
            ItemStack item = ItemCreator.createItem(SelectionHandler.getItem((Player) e.getWhoClicked()),
                    clickedItem.getType());
            player.getInventory().addItem(item);
            return;
        }
        if (clickedItem.getType().equals(Material.BARRIER)) {
            e.getWhoClicked().closeInventory();
        }

    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (InventoryHandler.getInventory().contains(e.getView().getTopInventory())) e.setCancelled(true);
    }

    // Remove inventory from inventoryHandler
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {
        if (InventoryHandler.getInventory().contains(e.getView().getTopInventory())) {
            InventoryHandler.removeInventory(e.getView().getTopInventory());
            try {
                SelectionHandler.removeItem((Player) e.getPlayer());
            } catch (NullPointerException ignored) {}
        }
    }
}