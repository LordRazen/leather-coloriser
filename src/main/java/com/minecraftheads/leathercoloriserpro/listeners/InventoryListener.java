package com.minecraftheads.leathercoloriserpro.listeners;

import com.minecraftheads.leathercoloriserpro.handlers.InventoryHandler;
import com.minecraftheads.leathercoloriserpro.handlers.LanguageHandler;
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
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // set variable player to the one who clicked
        Player player = (Player) e.getWhoClicked();

        // check what is clicked and perform action
        handleClick(player, clickedItem);

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
        // not possible yet, as we do not map players to inventoryHandler
        if (InventoryHandler.getInventory().contains(e.getView().getTopInventory())) {
            InventoryHandler.removeInventory(e.getView().getTopInventory());
        }
    }

    /**
     * check what item is clicked and perform action depending on that
     * @param clickedItem ItemStack
     * @param player Player
     */
    private void handleClick(Player player, ItemStack clickedItem) {
        // close inventory and send message to player how to use custom color codes
        if (clickedItem.getType().toString().equals("NAME_TAG")) {
            player.closeInventory();
            player.sendMessage(LanguageHandler.getMessage("useHexCommand"));
            //player.sendMessage("enter /lcp <HEX Color code>");
        }
        // Player chooses armor piece
        else if (clickedItem.getType().toString().startsWith("LEATHER_")) {
            if (checkRequirement(player, clickedItem)) {
                giveItem(player, clickedItem);
            }
        }
        // Choose color you want to have
        else if (clickedItem.getType().toString().endsWith("_DYE")) {
            // create the inventory for choosing the color
            SelectionHandler.addColor(player, clickedItem.getType());
            InventoryCreator inv = new InventoryCreator();
            inv.initializeItems(clickedItem.getType());
            inv.openInventory(player);
        }
        // Player cancels the LCP -> data cleanup
        else if (clickedItem.getType().equals(Material.BARRIER)) {
            player.closeInventory();
            try {
                SelectionHandler.removeColor(player);
            } catch (NullPointerException ignored) {}
        }
    }

    /**
     *  check if the player has the needed item in his inventory
     *
     * @param player Player
     * @param item ItemStack
     */
    private boolean checkRequirement(Player player, ItemStack item) {
        // check if player has item in inventory
        if (!player.getInventory().contains(new ItemStack(item.getType(), 1))) {
            player.sendMessage(LanguageHandler.getMessage("requiredItemMissing"));
            return false;
        }
        return true;
    }

    /**
     * generate colored armor piece, add it to inventory and remove raw (uncolored) armor
     *
     * @param player Player
     * @param item ItemStack
     */
    private void giveItem(Player player, ItemStack item) {
        // search for the first item in the inventory of the player which is the base item of the colored one
        player.getInventory().clear(player.getInventory().first(new ItemStack(item.getType(), 1)));
        player.getInventory().addItem(item);

        // remove the color in the SelectionHandler
        try {
            SelectionHandler.removeColor(player);
        } catch (NullPointerException ignored) {}
        player.closeInventory();
    }
}