package com.minecraftheads.leathercoloriserpro.listeners;

import com.minecraftheads.leathercoloriserpro.handlers.InventoryHandler;
import com.minecraftheads.leathercoloriserpro.handlers.LanguageHandler;
import com.minecraftheads.leathercoloriserpro.handlers.SelectionHandler;
import com.minecraftheads.leathercoloriserpro.utils.InventoryCreator;
import com.minecraftheads.leathercoloriserpro.utils.ItemCreator;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.Colorable;

import java.util.Objects;
import java.util.Random;

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

        // Verify current item is not null
        ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;

        // Get Player
        Player player = (Player) e.getWhoClicked();

        // Get actual color
        ItemStack leatherHelmet = e.getView().getTopInventory().getItem(36);
        LeatherArmorMeta meta = (LeatherArmorMeta) leatherHelmet.getItemMeta();
        Color actualColor = meta.getColor();

        // check what is clicked and perform action
        handleClick(player, clickedItem, actualColor);

    }

    /**
     * Cancel dragging in LCP inventory
     *
     * @param e InventoryDragEvent
     */
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (InventoryHandler.getInventory().contains(e.getView().getTopInventory()))
            e.setCancelled(true);
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
     * @param actualColor Color
     */
    private void handleClick(Player player, ItemStack clickedItem, Color actualColor) {
        // close inventory and send message to player how to use custom color codes
        if (clickedItem.getType().toString().equals("NAME_TAG")) {
            player.closeInventory();
            player.sendMessage(LanguageHandler.getMessage("error_invalid_color"));
        }
        // close inventory and send message to player how to use custom color codes
        else if (clickedItem.getType().toString().equals("TARGET")) {
            // create the inventory for choosing the color
            Random obj = new Random();
            InventoryCreator inv = new InventoryCreator();
            inv.initializeColoredArmor(Color.fromRGB(obj.nextInt(0xffffff + 1)));
            inv.openInventory(player);
        }
        // reset color
        else if (clickedItem.getType().toString().equals("WATER_BUCKET")) {
            InventoryCreator inv = new InventoryCreator();
            inv.initializeUncoloredArmor();
            inv.openInventory(player);
        }
        // Player chooses armor piece
        else if (clickedItem.getType().toString().startsWith("LEATHER_")) {
            // TODO: Clean armor
            // DEFAULT COLOR:
            if(actualColor.equals(Color.fromRGB(0xA06540))) {
                player.sendMessage("REMOVE COLOR");
                ItemStack[] items = player.getInventory().getContents();
                for (int i = 0; i < items.length; i++) {
                    if(items[i].getType() == clickedItem.getType()) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) items[i].getItemMeta();
                        if(!meta.getColor().equals(Color.fromRGB(0xA06540))) {
                            player.getInventory().clear(i);
                            player.getInventory().addItem(new ItemStack(clickedItem.getType(), 1));
                            break;
                        }
                    }
                }

            }
            else {
                player.sendMessage("COLOR");
                if (checkRequirement(player, clickedItem)) {
                    giveItem(player, clickedItem);
                }
            }


        }
        // Choose color you want to have
        else if (clickedItem.getType().toString().endsWith("_DYE")) {

            // Mix Colors if the color is not the default one
            Color newColor = getColorFromDye(clickedItem.getType());
            if(!actualColor.equals(Color.fromRGB(0xA06540))) {
                newColor = newColor.mixColors(actualColor);
            }

            // create the inventory for choosing the color
            InventoryCreator inv = new InventoryCreator();
            inv.initializeColoredArmor(newColor);
            inv.openInventory(player);
        }
        // Player cancels the LCP -> data cleanup
        // TODO: open url to minecraft-heads
        else if (clickedItem.getType().equals(Material.PUFFERFISH)) {
            player.closeInventory();
            player.sendMessage(LanguageHandler.getMessage("hardcoded URLs... no language handler!"));
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
        if (player.getInventory().contains(new ItemStack(item.getType(), 1)))
            return true;

        player.sendMessage(LanguageHandler.getMessage("error_item_missing"));
        return false;
    }

    /**
     * generate colored armor piece, add it to inventory and remove raw (uncolored) armor
     *
     * @param player Player
     * @param item ItemStack
     */
    private void giveItem(Player player, ItemStack item) {
        // search for the first item in the inventory of the player which is the base item of the colored one

        // TODO: CLEAN ITEMS!
        player.getInventory().clear(player.getInventory().first(new ItemStack(item.getType(), 1)));
        player.getInventory().addItem(item);

        // remove the color in the SelectionHandler
        try {
            SelectionHandler.removeColor(player);
        } catch (NullPointerException ignored) {}
    }

    private Color getColorFromDye(Material mat) {
        switch (mat) {
            case BLACK_DYE:
                return DyeColor.BLACK.getColor();
            case BLUE_DYE:
                return DyeColor.BLUE.getColor();
            case BROWN_DYE:
                return DyeColor.BROWN.getColor();
            case CYAN_DYE:
                return DyeColor.CYAN.getColor();
            case GRAY_DYE:
                return DyeColor.GRAY.getColor();
            case GREEN_DYE:
                return DyeColor.GREEN.getColor();
            case LIGHT_BLUE_DYE:
                return DyeColor.LIGHT_BLUE.getColor();
            case LIGHT_GRAY_DYE:
                return DyeColor.LIGHT_GRAY.getColor();
            case LIME_DYE:
                return DyeColor.LIME.getColor();
            case MAGENTA_DYE:
                return DyeColor.MAGENTA.getColor();
            case ORANGE_DYE:
                return DyeColor.ORANGE.getColor();
            case PINK_DYE:
                return DyeColor.PINK.getColor();
            case PURPLE_DYE:
                return DyeColor.PURPLE.getColor();
            case RED_DYE:
                return DyeColor.RED.getColor();
            case WHITE_DYE:
                return DyeColor.WHITE.getColor();
            case YELLOW_DYE:
                return DyeColor.YELLOW.getColor();
        }
        return DyeColor.BROWN.getColor();
    }
}