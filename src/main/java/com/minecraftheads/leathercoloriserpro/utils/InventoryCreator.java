package com.minecraftheads.leathercoloriserpro.utils;

import com.minecraftheads.leathercoloriserpro.handlers.InventoryHandler;
import com.minecraftheads.leathercoloriserpro.handlers.LanguageHandler;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryCreator {
    private Inventory inv;
    private Color color;

    /**
     * Constructor
     */
    public InventoryCreator() {
        this.color = ColorUtils.DEFAULT_COLOR;
        initializeInventory();
    }

    /**
     * Constructor
     *
     * @param color Color
     */
    public InventoryCreator(Color color) {
        this.color = color;
        initializeInventory();
    }

    /**
     * open the inventory with the colored items, color is set via DYE selection
     */
    public void initializeInventory() {
        this.inv = Bukkit.createInventory(null, 54, LanguageHandler.getMessage("title"));
        setLeatherIcons();
        setColorIcons();
        setConfigIcons();
        setWatermark();
    }

    /**
     * Generate Leather Item Icons
     */
    private void setLeatherIcons() {
        this.inv.setItem(0, ItemCreator.createDyedItem(Material.LEATHER_HELMET, "leather_helmet", color));
        this.inv.setItem(1, ItemCreator.createDyedItem(Material.LEATHER_CHESTPLATE, "leather_chestplate", color));
        this.inv.setItem(2, ItemCreator.createDyedItem(Material.LEATHER_LEGGINGS, "leather_leggings", color));
        this.inv.setItem(3, ItemCreator.createDyedItem(Material.LEATHER_BOOTS, "leather_boots", color));
        this.inv.setItem(4, ItemCreator.createDyedItem(Material.LEATHER_HORSE_ARMOR, "leather_horse_armor", color));
    }

    /**
     * Generate Dye Icons
     */
    private void setColorIcons() {
        this.inv.setItem(18, ItemCreator.createItem(Material.YELLOW_DYE, "yellow"));
        this.inv.setItem(19, ItemCreator.createItem(Material.ORANGE_DYE, "orange"));
        this.inv.setItem(20, ItemCreator.createItem(Material.RED_DYE, "red"));
        this.inv.setItem(21, ItemCreator.createItem(Material.BROWN_DYE, "brown"));
        this.inv.setItem(22, ItemCreator.createItem(Material.LIME_DYE, "lime"));
        this.inv.setItem(23, ItemCreator.createItem(Material.GREEN_DYE, "green"));

        this.inv.setItem(27, ItemCreator.createItem(Material.PINK_DYE, "pink"));
        this.inv.setItem(28, ItemCreator.createItem(Material.MAGENTA_DYE, "magenta"));
        this.inv.setItem(29, ItemCreator.createItem(Material.PURPLE_DYE, "purple"));
        this.inv.setItem(30, ItemCreator.createItem(Material.LIGHT_BLUE_DYE, "light_blue"));
        this.inv.setItem(31, ItemCreator.createItem(Material.CYAN_DYE, "cyan"));
        this.inv.setItem(32, ItemCreator.createItem(Material.BLUE_DYE, "blue"));

        this.inv.setItem(37, ItemCreator.createItem(Material.WHITE_DYE, "white"));
        this.inv.setItem(38, ItemCreator.createItem(Material.LIGHT_GRAY_DYE, "light_gray"));
        this.inv.setItem(39, ItemCreator.createItem(Material.GRAY_DYE, "gray"));
        this.inv.setItem(40, ItemCreator.createItem(Material.BLACK_DYE, "black"));
    }

    /**
     * Set Config Icons
     */
    private void setConfigIcons() {
        this.inv.setItem(6, ItemCreator.createItem(Material.WATER_BUCKET, "color_reset"));
        this.inv.setItem(7, ItemCreator.createItem(Material.TARGET, "color_random"));
        this.inv.setItem(8, ItemCreator.createItem(Material.NAME_TAG, "color_string"));

        this.inv.setItem(16, ItemCreator.createItem(Material.PURPLE_CONCRETE, "decrease_hue"));
        this.inv.setItem(17, ItemCreator.createItem(Material.RED_TERRACOTTA, "increase_hue"));
        this.inv.setItem(25, ItemCreator.createItem(Material.BLUE_STAINED_GLASS, "decrease_saturation"));
        this.inv.setItem(26, ItemCreator.createItem(Material.BLUE_CONCRETE, "increase_saturation"));
        this.inv.setItem(34, ItemCreator.createItem(Material.BLACK_CONCRETE, "decrease_brightness"));
        this.inv.setItem(35, ItemCreator.createItem(Material.WHITE_CONCRETE, "increase_brightness"));
    }

    /**
     * Set MCHeads Watermark
     */
    private void setWatermark() {
        ItemStack item = new ItemStack(Material.PUFFERFISH, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("www.minecraft-heads.com");
        item.setItemMeta(meta);
        this.inv.setItem(53, item);
    }

    /**
     * Open the inventory for given player
     *
     * @param player Player
     */
    public void openInventory(Player player) {
        player.openInventory(this.inv);
        InventoryHandler.addInventory(inv);
    }
}