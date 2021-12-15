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
    private final Inventory inv;

    /**
     * Constructor creates a new inventory
     */
    public InventoryCreator() {
        this.inv = Bukkit.createInventory(null, 54, "Leather Coloriser Pro");
    }

    /**
     * create the inventory with the default items for selection (opens with uncolored armor)
     *
     */
    public void initializeUncoloredArmor() {
        setColorIcons();
        this.inv.setItem(36, new ItemStack(Material.LEATHER_HELMET, 1));
        this.inv.setItem(37, new ItemStack(Material.LEATHER_CHESTPLATE, 1));
        this.inv.setItem(38, new ItemStack(Material.LEATHER_LEGGINGS, 1));
        this.inv.setItem(39, new ItemStack(Material.LEATHER_BOOTS, 1));

        this.inv.setItem(41, new ItemStack(Material.LEATHER_HORSE_ARMOR, 1));

       // this.inv.setItem(53, createItem(Material.BARRIER, "Cancel"));
    }


    /**
     * open the inventory with the colored items, color is set via DYE selection
     *
     * @param color Material
     */
    public void initializeColoredArmor(Color color) {
        setColorIcons();
        this.inv.setItem(36, createItem(ItemCreator.createItem(Material.LEATHER_HELMET, color), LanguageHandler.getMessage("leather_helmet")));
        this.inv.setItem(37, createItem(ItemCreator.createItem(Material.LEATHER_CHESTPLATE, color), LanguageHandler.getMessage("leather_chestplate")));
        this.inv.setItem(38, createItem(ItemCreator.createItem(Material.LEATHER_LEGGINGS, color), LanguageHandler.getMessage("leather_leggings")));
        this.inv.setItem(39, createItem(ItemCreator.createItem(Material.LEATHER_BOOTS, color), LanguageHandler.getMessage("leather_boots")));

        this.inv.setItem(41, createItem(ItemCreator.createItem(Material.LEATHER_HORSE_ARMOR, color), LanguageHandler.getMessage("leather_horse_armor")));

        // ToDo: replace with something from minecraft-heads (to open url)
        //this.inv.setItem(53, createItem(Material.BARRIER, "Cancel"));
    }

    /**
     * generates the Dys slots
     */
    private void setColorIcons() {
        this.inv.setItem(0, createItem(Material.YELLOW_DYE, LanguageHandler.getMessage("yellow")));
        this.inv.setItem(1, createItem(Material.ORANGE_DYE, LanguageHandler.getMessage("orange")));
        this.inv.setItem(2, createItem(Material.RED_DYE, LanguageHandler.getMessage("red")));
        this.inv.setItem(3, createItem(Material.BROWN_DYE, LanguageHandler.getMessage("brown")));
        this.inv.setItem(4, createItem(Material.LIME_DYE, LanguageHandler.getMessage("lime")));
        this.inv.setItem(5, createItem(Material.GREEN_DYE, LanguageHandler.getMessage("green")));

        this.inv.setItem(9, createItem(Material.PINK_DYE, LanguageHandler.getMessage("pink")));
        this.inv.setItem(10, createItem(Material.MAGENTA_DYE, LanguageHandler.getMessage("magenta")));
        this.inv.setItem(11, createItem(Material.PURPLE_DYE, LanguageHandler.getMessage("purple")));
        this.inv.setItem(12, createItem(Material.LIGHT_BLUE_DYE, LanguageHandler.getMessage("light_blue")));
        this.inv.setItem(13, createItem(Material.CYAN_DYE, LanguageHandler.getMessage("cyan")));
        this.inv.setItem(14, createItem(Material.BLUE_DYE, LanguageHandler.getMessage("blue")));

        this.inv.setItem(19, createItem(Material.WHITE_DYE, LanguageHandler.getMessage("white")));
        this.inv.setItem(20, createItem(Material.LIGHT_GRAY_DYE, LanguageHandler.getMessage("light_gray")));
        this.inv.setItem(21, createItem(Material.GRAY_DYE, LanguageHandler.getMessage("gray")));
        this.inv.setItem(22, createItem(Material.BLACK_DYE, LanguageHandler.getMessage("black")));

        this.inv.setItem(8, createItem(Material.WATER_BUCKET, LanguageHandler.getMessage("color_reset")));
        this.inv.setItem(17, createItem(Material.TARGET, LanguageHandler.getMessage("color_random")));
        this.inv.setItem(26, createItem(Material.NAME_TAG, LanguageHandler.getMessage("color_string")));
    }


    /**
     * Basic method to create ItemStack with name from Material
     *
     * @param mat Material
     * @param name String
     * @return ItemStack
     */
    private ItemStack createItem(Material mat, String name) {
        ItemStack item = new ItemStack(mat, 1);
        return createItem(item, name);
    }

    /**
     * change the name of a ItemStack
     *
     * @param item ItemStack
     * @param name String
     * @return ItemStack
     */
    private ItemStack createItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    /**
     * open the inventory for given player
     *
     * @param player Player
     */
    public void openInventory(Player player) {
        player.openInventory(this.inv);
        InventoryHandler.addInventory(inv);
    }
}