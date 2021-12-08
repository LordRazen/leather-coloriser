package com.minecraftheads.leathercoloriserpro.listeners;

import com.minecraftheads.leathercoloriserpro.handlers.InventoryHandler;
import com.minecraftheads.leathercoloriserpro.handlers.SelectionHandler;
import com.minecraftheads.leathercoloriserpro.utils.InventoryCreator;
import com.minecraftheads.leathercoloriserpro.utils.ItemCreator;
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

    /**
     * Check for clicks on items
     *
     * @param e InventoryClickEvent
     */
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

        // If item is a piece or Armor save it in the SelectionHandler
        if (Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName().startsWith("Leather")) {
            SelectionHandler.addItem(player, clickedItem);

            // create the inventory for choosing the color
            InventoryCreator inv = new InventoryCreator();
            inv.initializeColor();
            inv.openInventory(player);
            return;

        // Choose color you want to have
        } else if (Objects.requireNonNull(clickedItem.getItemMeta()).getDisplayName().startsWith("Dye")) {
            // TODO: select correct Color.

            // Create a new item which can be given to the player
            ItemStack item = ItemCreator.createItem(SelectionHandler.getItem((Player) e.getWhoClicked()),
                    Objects.requireNonNull(clickedItem.getData()).getItemType());
            player.getInventory().addItem(item);

            // remove the old item in the SelectionHandler
            try {
                SelectionHandler.removeItem((Player) e.getWhoClicked());
            } catch (NullPointerException ignored) {}
            return;
        }
        // Player cancels the LCP -> data cleanup
        if (clickedItem.getType().equals(Material.BARRIER)) {
            e.getWhoClicked().closeInventory();
            try {
                SelectionHandler.removeItem((Player) e.getWhoClicked());
            } catch (NullPointerException ignored) {}
        }

    }

    /**
     * Cancel dragging in LCP inventory
     *
     * @param e InventoryDragEvent
     */
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (InventoryHandler.getInventory().contains(e.getView().getTopInventory())) e.setCancelled(true);
    }

    /**
     * Remove inventory from inventoryHandler when a inventory is closed
     *
     * @param e InventoryCloseEvent
     */
    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {
        // TODO: cleanup of old selections when player leave? could result in memoryleak if server runs a long time
        if (InventoryHandler.getInventory().contains(e.getView().getTopInventory())) {
            InventoryHandler.removeInventory(e.getView().getTopInventory());
        }
    }
}