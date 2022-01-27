package com.minecraftheads.leathercolorizer.handlers;

import com.minecraftheads.leathercolorizer.data.DyeColorMapping;
import com.minecraftheads.leathercolorizer.data.InventoryMapping;
import com.minecraftheads.leathercolorizer.data.LanguageMapping;
import com.minecraftheads.leathercolorizer.utils.ColorChanger;
import com.minecraftheads.leathercolorizer.utils.InventoryCreatorBridge;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ClickHandler {

    /**
     * Handle clicks within the GUI
     *
     * @param e           InventoryClickEvent
     * @param clickedItem ItemStack
     */
    public static void handleInventoryClickGUI(InventoryClickEvent e, ItemStack clickedItem) {
        // Get actual color
        ItemStack leatherHelmet = e.getView().getTopInventory().getItem(0);
        LeatherArmorMeta meta = (LeatherArmorMeta) leatherHelmet.getItemMeta();
        Color actualColor = meta.getColor();

        // check what is clicked and perform action
        handleClick(e);
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
        SelectionHandler.setColor(player, meta.getColor());

        // Open new INV with ripped color
        new InventoryCreatorBridge(player);
    }


    /**
     * Check what item is clicked and perform action depending on that
     *
     * @param e InventoryClickEvent
     */
    private static void handleClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        Color col = ((LeatherArmorMeta) e.getInventory().getItem(0).getItemMeta()).getColor();

        try {
            InventoryMapping im = InventoryMapping.getBySlot(e.getSlot());
            switch (im.getAction()) {
                // generate random color
                case ("randomColor"):
                    Random obj = new Random();
                    SelectionHandler.setColor(player, Color.fromRGB(obj.nextInt(0xffffff + 1)));
                    new InventoryCreatorBridge(player);
                    break;
                // Close inventory and send message to player how to use custom color codes
                case ("hexCommand"):
                    player.closeInventory();
                    player.sendMessage(LanguageMapping.ERROR_INVALID_COLOR.getStringWithPrefix());
                    break;
                case ("decrease_hue"):
                    ColorChanger.DECREASE_HUE.apply(player);
                    new InventoryCreatorBridge(player);
                    break;
                case ("increase_hue"):
                    ColorChanger.INCREASE_HUE.apply(player);
                    new InventoryCreatorBridge(player);
                    break;
                case ("decrease_saturation"):
                    ColorChanger.INCREASE_SATURATION.apply(player);
                    new InventoryCreatorBridge(player);
                    break;
                case ("increase_saturation"):
                    ColorChanger.DECREASE_SATURATION.apply(player);
                    new InventoryCreatorBridge(player);
                    break;
                case ("decrease_brightness"):
                    ColorChanger.DECREASE_BRIGHTNESS.apply(player);
                    new InventoryCreatorBridge(player);
                    break;
                case ("increase_brightness"):
                    ColorChanger.INCREASE_BRIGHTNESS.apply(player);
                    new InventoryCreatorBridge(player);
                    break;
                case ("resetColor"):
                    SelectionHandler.setColor(player, DyeColorMapping.DEFAULT.getColor());
                    new InventoryCreatorBridge(player);
                    break;
                case ("dye"):
                    // Mix Colors if the color is not the default one
                    Color newColor;
                    try {
                        newColor = DyeColorMapping.getColorByMat(im.getMaterial());
                    } catch (NullPointerException ignored) {
                        newColor = DyeColorMapping.DEFAULT.getColor();
                    }
                    SelectionHandler.setColor(player, newColor);
                    if (!col.equals(DyeColorMapping.DEFAULT.getColor())) {
                        SelectionHandler.setColor(player, newColor.mixColors(col));
                    }
                    new InventoryCreatorBridge(player);
                    break;
                case ("armor"):
                    // Clean Armor
                    if (col.equals(DyeColorMapping.DEFAULT.getColor())) {
                        cleanArmor(player, clickedItem);
                    }
                    // Colorize Armor
                    else {
                        colorizeArmor(player, clickedItem);
                    }
                    break;
            }
        } catch (NullPointerException ignored) {

        }

        // Open Watermark
        if (clickedItem.getType().equals(Material.PUFFERFISH)) {
            player.closeInventory();
            player.sendMessage("§6[§4LC§6] §aGet decoration heads and more plugins for detailed decoration at §3www.minecraft-heads.com");
        }
    }

    /**
     * Colorize Armor
     *
     * @param player      Player
     * @param clickedItem ItemStack
     */
    private static void colorizeArmor(Player player, ItemStack clickedItem) {
        HashMap<Integer, ? extends ItemStack> items = player.getInventory().all(clickedItem.getType());
        boolean noItemsColorized = true;

        // Check all the found armor items
        for (Map.Entry<Integer, ? extends ItemStack> entry : items.entrySet()) {
            Integer slot = entry.getKey();
            ItemStack item = entry.getValue();

            // COLOR: Check if item has the default color
            LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
            if (!itemMeta.getColor().equals(DyeColorMapping.DEFAULT.getColor())) continue;
            Color color = ((LeatherArmorMeta) clickedItem.getItemMeta()).getColor();

            // ENCHANTMENTS: Check the item Enchantments
            if (!Bukkit.getPluginManager().getPlugin("LeatherColorizer").getConfig().getBoolean("allowEnchantedItems"))
                if (itemMeta.hasEnchants()) continue;

            // DAMAGE: Check the item damage
            Damageable itemDamage = (Damageable) item.getItemMeta();
            if (!Bukkit.getPluginManager().getPlugin("LeatherColorizer").getConfig().getBoolean("allowDamagedItems"))
                if (itemDamage.hasDamage()) continue;

            // Colorize the item
            ItemStack newItem = modifyItem(item, color);

            player.getInventory().clear(slot);
            player.getInventory().setItem(slot, newItem);
            noItemsColorized = false;
            break;
        }

        // Message if no items were colorized
        if (noItemsColorized) player.sendMessage(LanguageMapping.ERROR_ITEM_MISSING.getStringWithPrefix());
    }

    /**
     * Clean Armor
     *
     * @param player      Player
     * @param clickedItem ItemStack
     */
    private static void cleanArmor(Player player, ItemStack clickedItem) {
        HashMap<Integer, ? extends ItemStack> items = player.getInventory().all(clickedItem.getType());
        boolean noArmorCleaned = true;

        // Check all the found armor items
        for (Map.Entry<Integer, ? extends ItemStack> entry : items.entrySet()) {
            Integer slot = entry.getKey();
            ItemStack item = entry.getValue();

            // COLOR: Item has default color already - nothing to undye
            LeatherArmorMeta itemMeta = (LeatherArmorMeta) item.getItemMeta();
            if (itemMeta.getColor().equals(DyeColorMapping.DEFAULT.getColor())) continue;

            // ENCHANTMENTS: Check the item Enchantments
            if (!Bukkit.getPluginManager().getPlugin("LeatherColorizer").getConfig().getBoolean("allowEnchantedItems"))
                if (itemMeta.hasEnchants()) continue;

            // DAMAGE:
            if (!Bukkit.getPluginManager().getPlugin("LeatherColorizer").getConfig().getBoolean("allowDamagedItems"))
                if (((Damageable) item.getItemMeta()).hasDamage()) continue;

            // Clean Item
            ItemStack newItem = modifyItem(item, DyeColorMapping.DEFAULT.getColor());

            player.getInventory().clear(slot);
            player.getInventory().setItem(slot, newItem);
            noArmorCleaned = false;
            break;
        }

        // Message if no armor was cleaned
        if (noArmorCleaned) player.sendMessage(LanguageMapping.ERROR_ITEM_MISSING.getStringWithPrefix());
    }

    /**
     * Modify Item
     * (No wrong title, no hidden info etc)
     *
     * @param item  ItemStack
     * @param color Color
     */
    private static ItemStack modifyItem(ItemStack item, Color color) {
        // LeatherArmorMeta: Color and Enchantments
        LeatherArmorMeta colorMeta = (LeatherArmorMeta) item.getItemMeta();
        colorMeta.setColor(color);
        item.setItemMeta(colorMeta);

        // ItemMeta: Title, Lore, Enchantments
        ItemMeta itemMeta = item.getItemMeta();

        // Title
        if (!Bukkit.getPluginManager().getPlugin("LeatherColorizer").getConfig().getBoolean("keepTitle"))
            itemMeta.setDisplayName("");

        // Lore
        if (!Bukkit.getPluginManager().getPlugin("LeatherColorizer").getConfig().getBoolean("keepLore"))
            itemMeta.getLore().clear();

        // Enchantments
        if (!Bukkit.getPluginManager().getPlugin("LeatherColorizer").getConfig().getBoolean("keepEnchantments")) {
            for (Map.Entry<Enchantment, Integer> entry : colorMeta.getEnchants().entrySet()) {
                Enchantment enchantment = entry.getKey();
                colorMeta.removeEnchant(enchantment);
            }
        }

        item.setItemMeta(itemMeta);

        return item;
    }
}
