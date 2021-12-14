package com.minecraftheads.leathercoloriserpro.utils;

import com.minecraftheads.leathercoloriserpro.handlers.InventoryHandler;
import com.minecraftheads.leathercoloriserpro.handlers.LanguageHandler;
import org.bukkit.Bukkit;
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
    public void initializeItems() {
        setColorIcons();
        this.inv.setItem(28, new ItemStack(Material.LEATHER_HELMET, 1));
        this.inv.setItem(29, new ItemStack(Material.LEATHER_CHESTPLATE, 1));
        this.inv.setItem(30, new ItemStack(Material.LEATHER_LEGGINGS, 1));
        this.inv.setItem(31, new ItemStack(Material.LEATHER_BOOTS, 1));

        this.inv.setItem(33, new ItemStack(Material.LEATHER_HORSE_ARMOR, 1));

       // this.inv.setItem(53, createItem(Material.BARRIER, "Cancel"));
    }

    /**
     * open the inventory with the colored items, color is set via HEX string
     *
     * @param color String
     */
    public void initializeItems(String color) {
        // TODO: Combine the main part of initializeItems()!
        setColorIcons();
        this.inv.setItem(28, createItem(ItemCreator.createItem(Material.LEATHER_HELMET, color), LanguageHandler.getMessage("leather_helmet")));
        this.inv.setItem(29, createItem(ItemCreator.createItem(Material.LEATHER_CHESTPLATE, color), LanguageHandler.getMessage("leather_chestplate")));
        this.inv.setItem(30, createItem(ItemCreator.createItem(Material.LEATHER_LEGGINGS, color), LanguageHandler.getMessage("leather_leggings")));
        this.inv.setItem(31, createItem(ItemCreator.createItem(Material.LEATHER_BOOTS, color), LanguageHandler.getMessage("leather_boots")));

        this.inv.setItem(33, createItem(ItemCreator.createItem(Material.LEATHER_HORSE_ARMOR, color), LanguageHandler.getMessage("leather_horse_armor")));

        //this.inv.setItem(53, createItem(Material.BARRIER, "Cancel"));
    }

    /**
     * open the inventory with the colored items, color is set via DYE selection
     *
     * @param color Material
     */
    public void initializeItems(Material color) {
        setColorIcons();
        this.inv.setItem(28, createItem(ItemCreator.createItem(Material.LEATHER_HELMET, color), LanguageHandler.getMessage("leather_helmet")));
        this.inv.setItem(29, createItem(ItemCreator.createItem(Material.LEATHER_CHESTPLATE, color), LanguageHandler.getMessage("leather_chestplate")));
        this.inv.setItem(30, createItem(ItemCreator.createItem(Material.LEATHER_LEGGINGS, color), LanguageHandler.getMessage("leather_leggings")));
        this.inv.setItem(31, createItem(ItemCreator.createItem(Material.LEATHER_BOOTS, color), LanguageHandler.getMessage("leather_boots")));

        this.inv.setItem(33, createItem(ItemCreator.createItem(Material.LEATHER_HORSE_ARMOR, color), LanguageHandler.getMessage("leather_horse_armor")));

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
        this.inv.setItem(6, createItem(Material.LIGHT_BLUE_DYE, LanguageHandler.getMessage("light_blue")));
        this.inv.setItem(7, createItem(Material.CYAN_DYE, LanguageHandler.getMessage("cyan")));
        this.inv.setItem(8, createItem(Material.BLUE_DYE, LanguageHandler.getMessage("blue")));
        this.inv.setItem(9, createItem(Material.PINK_DYE, LanguageHandler.getMessage("pink")));
        this.inv.setItem(10, createItem(Material.MAGENTA_DYE, LanguageHandler.getMessage("magenta")));
        this.inv.setItem(11, createItem(Material.PURPLE_DYE, LanguageHandler.getMessage("purple")));
        this.inv.setItem(12, createItem(Material.WHITE_DYE, LanguageHandler.getMessage("white")));
        this.inv.setItem(13, createItem(Material.LIGHT_GRAY_DYE, LanguageHandler.getMessage("light_gray")));
        this.inv.setItem(14, createItem(Material.GRAY_DYE, LanguageHandler.getMessage("gray")));
        this.inv.setItem(15, createItem(Material.BLACK_DYE, LanguageHandler.getMessage("black")));

        this.inv.setItem(17, createItem(Material.NAME_TAG, LanguageHandler.getMessage("custom_color")));
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
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return item;
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