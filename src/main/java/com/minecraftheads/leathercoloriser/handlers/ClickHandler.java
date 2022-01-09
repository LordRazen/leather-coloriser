package com.minecraftheads.leathercoloriser.handlers;

import com.minecraftheads.leathercoloriser.utils.ColorChangeType;
import com.minecraftheads.leathercoloriser.utils.ColorUtils;
import com.minecraftheads.leathercoloriser.utils.InventoryCreator;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Arrays;
import java.util.Random;

public class ClickHandler {
    /**
     * Handle clicks within the GUI
     *
     * @param e           InventoryClickEvent
     * @param clickedItem ItemStack
     */
    public static void handleInventoryClickGUI(InventoryClickEvent e, ItemStack clickedItem) {
        // Get Player
        Player player = (Player) e.getWhoClicked();

        // Get actual color
        ItemStack leatherHelmet = e.getView().getTopInventory().getItem(0);
        LeatherArmorMeta meta = (LeatherArmorMeta) leatherHelmet.getItemMeta();
        Color actualColor = meta.getColor();

        // check what is clicked and perform action
        handleClick(player, clickedItem, actualColor);
    }

    /**
     * Handle clicks within the players inventory
     *
     * @param e           InventoryClickEvent
     * @param clickedItem ItemStack
     */
    public static void handleInventoryClickInventory(InventoryClickEvent e, ItemStack clickedItem) {
        // Abort if the clicked item in the players inventory is no colorizable leather item
        Material[] leatherItems = {Material.LEATHER_HELMET,
                Material.LEATHER_CHESTPLATE,
                Material.LEATHER_LEGGINGS,
                Material.LEATHER_BOOTS,
                Material.LEATHER_HORSE_ARMOR};
        if (!Arrays.asList(leatherItems).contains(clickedItem.getType())) return;

        // Get Player
        Player player = (Player) e.getWhoClicked();

        // Rip color from leather item
        LeatherArmorMeta meta = (LeatherArmorMeta) clickedItem.getItemMeta();
        Color rippedColor = meta.getColor();

        // Open new INV with ripped color
        new InventoryCreator(rippedColor).openInventory(player);
    }


    /**
     * check what item is clicked and perform action depending on that
     *
     * @param clickedItem ItemStack
     * @param player      Player
     * @param actualColor Color
     */
    private static void handleClick(Player player, ItemStack clickedItem, Color actualColor) {
        // Close inventory and send message to player how to use custom color codes
        if (clickedItem.getType().toString().equals("NAME_TAG")) {
            player.closeInventory();
            player.sendMessage(LanguageHandler.getMessage("error_invalid_color"));
        }

        // Close inventory and send message to player how to use custom color codes
        else if (clickedItem.getType().toString().equals("TARGET")) {
            Random obj = new Random();
            new InventoryCreator(Color.fromRGB(obj.nextInt(0xffffff + 1))).openInventory(player);
        }

        // Player chooses armor piece
        else if (clickedItem.getType().toString().startsWith("LEATHER_")) {

            // Clean Armor
            if (actualColor.equals(ColorUtils.DEFAULT_COLOR)) {
                ItemStack[] items = player.getInventory().getContents();
                for (int i = 0; i < items.length; i++) {
                    if (items[i] != null && items[i].getType() == clickedItem.getType()) {
                        LeatherArmorMeta meta = (LeatherArmorMeta) items[i].getItemMeta();
                        if (!meta.getColor().equals(ColorUtils.DEFAULT_COLOR)) {
                            player.getInventory().clear(i);
                            player.getInventory().addItem(new ItemStack(clickedItem.getType(), 1));
                            break;
                        }
                    }
                }

            }

            // Colorise Armor
            else {
                if (checkRequirement(player, clickedItem)) {
                    // Search for the first item in the inventory of the player which is the base item of the colored one
                    player.getInventory().clear(player.getInventory().first(new ItemStack(clickedItem.getType(), 1)));
                    player.getInventory().addItem(clickedItem);

                    // TODO: Remove giveItem or create an own method to clean items?
                }
            }
        }

        // Open Watermark
        else if (clickedItem.getType().equals(Material.PUFFERFISH)) {
            player.closeInventory();
            player.sendMessage("§6[§4LCP§6] §aGet more plugins for detailed decoration at\n§3www.minecraft-heads.com");
        }

        // HANDLE COLOR CHANGES

        // Reset color
        else if (clickedItem.getType().toString().equals("WATER_BUCKET")) {
            new InventoryCreator().openInventory(player);
        }

        // Choose color you want to have
        else if (clickedItem.getType().toString().endsWith("_DYE")) {

            // Mix Colors if the color is not the default one
            Color newColor = ColorUtils.getColorFromDye(clickedItem.getType());
            if (!actualColor.equals(ColorUtils.DEFAULT_COLOR)) {
                newColor = newColor.mixColors(actualColor);
            }

            // Create the inventory for choosing the color
            new InventoryCreator(newColor).openInventory(player);
        }

        // Increase Hue
        else if (clickedItem.getType().equals(Material.RED_TERRACOTTA)) {
            new InventoryCreator(ColorUtils.changeColor(actualColor, ColorChangeType.DECREASE_HUE)).openInventory(player);
        }

        // Decrease Hue
        else if (clickedItem.getType().equals(Material.PURPLE_CONCRETE)) {
            new InventoryCreator(ColorUtils.changeColor(actualColor, ColorChangeType.INCREASE_HUE)).openInventory(player);
        }

        // Decrease Saturation
        else if (clickedItem.getType().equals(Material.BLUE_STAINED_GLASS)) {
            new InventoryCreator(ColorUtils.changeColor(actualColor, ColorChangeType.DECREASE_SATURATION)).openInventory(player);
        }

        // Increase Saturation
        else if (clickedItem.getType().equals(Material.BLUE_CONCRETE)) {
            new InventoryCreator(ColorUtils.changeColor(actualColor, ColorChangeType.INCREASE_SATURATION)).openInventory(player);
        }

        // Decrease Brightness
        else if (clickedItem.getType().equals(Material.BLACK_CONCRETE)) {
            new InventoryCreator(ColorUtils.changeColor(actualColor, ColorChangeType.DECREASE_BRIGHTNESS)).openInventory(player);
        }

        // Increase Brightness
        else if (clickedItem.getType().equals(Material.WHITE_CONCRETE)) {
            new InventoryCreator(ColorUtils.changeColor(actualColor, ColorChangeType.INCREASE_BRIGHTNESS)).openInventory(player);
        }
    }

    /**
     * Check if the player has the needed item in his inventory
     *
     * @param player Player
     * @param item   ItemStack
     * @return boolean
     */
    private static boolean checkRequirement(Player player, ItemStack item) {
        if (player.getInventory().contains(new ItemStack(item.getType(), 1)))
            return true;

        player.sendMessage(LanguageHandler.getMessage("error_item_missing"));
        return false;
    }

    /**
     * Generate colored armor piece, add it to inventory and remove raw (uncolored) armor
     *
     * @param player Player
     * @param item   ItemStack
     */
    private static void giveItem(Player player, ItemStack item) {
        // Search for the first item in the inventory of the player which is the base item of the colored one
        player.getInventory().clear(player.getInventory().first(new ItemStack(item.getType(), 1)));
        player.getInventory().addItem(item);

        // Remove the color in the SelectionHandler
        try {
            SelectionHandler.removeColor(player);
        } catch (NullPointerException ignored) {
        }
    }
}
