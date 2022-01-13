package com.minecraftheads.leathercoloriser.listeners;

import com.minecraftheads.leathercoloriser.handlers.ClickHandler;
import com.minecraftheads.pluginUtils.inventory.InventoryStorage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class InventoryListener implements Listener {
    /**
     * Check for clicks on items
     * @param e InventoryClickEvent
     */
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        // Check if inventory is an LCP inventory
        if (!InventoryStorage.getInventory().contains(e.getView().getTopInventory())) return;

        // Verify current item is not null / air
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // Item cannot be removed
        e.setCancelled(true);

        // Check where the click was
        if (Objects.equals(e.getClickedInventory(), e.getView().getTopInventory())) {
            // GUI
            ClickHandler.handleInventoryClickGUI(e, clickedItem);
        }
        else {
            // Inventory
            ClickHandler.handleInventoryClickInventory(e, clickedItem);
        }
    }


    /**
     * Cancel dragging in LCP inventory
     *
     * @param e InventoryDragEvent
     */
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (InventoryStorage.getInventory().contains(e.getView().getTopInventory()))
            e.setCancelled(true);
    }

}