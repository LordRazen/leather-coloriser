package com.minecraftheads.leathercoloriserpro.utils;

import com.minecraftheads.leathercoloriserpro.handlers.InventoryHandler;
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
        setColors();
        this.inv.setItem(28, new ItemStack(Material.LEATHER_BOOTS, 1));
        this.inv.setItem(30, new ItemStack(Material.LEATHER_LEGGINGS, 1));
        this.inv.setItem(32, new ItemStack(Material.LEATHER_CHESTPLATE, 1));
        this.inv.setItem(34, new ItemStack(Material.LEATHER_HELMET, 1));

        this.inv.setItem(53, createItem(Material.BARRIER, "Cancel"));
    }

    /**
     * open the inventory with the colored items, color is set via HEX string
     *
     * @param color String
     */
    public void initializeItems(String color) {
        setColors();
        this.inv.setItem(28, createItem(ItemCreator.createItem(Material.LEATHER_BOOTS, color), "Leather Boots"));
        this.inv.setItem(30, createItem(ItemCreator.createItem(Material.LEATHER_LEGGINGS, color), "Leather Pants"));
        this.inv.setItem(32, createItem(ItemCreator.createItem(Material.LEATHER_CHESTPLATE, color), "Leather Chestplate"));
        this.inv.setItem(34, createItem(ItemCreator.createItem(Material.LEATHER_HELMET, color), "Leather Helmet"));

        this.inv.setItem(53, createItem(Material.BARRIER, "Cancel"));
    }

    /**
     * open the inventory with the colored items, color is set via DYE selection
     *
     * @param color Material
     */
    public void initializeItems(Material color) {
        setColors();
        this.inv.setItem(28, createItem(ItemCreator.createItem(Material.LEATHER_BOOTS, color), "Leather Boots"));
        this.inv.setItem(30, createItem(ItemCreator.createItem(Material.LEATHER_LEGGINGS, color), "Leather Pants"));
        this.inv.setItem(32, createItem(ItemCreator.createItem(Material.LEATHER_CHESTPLATE, color), "Leather Chestplate"));
        this.inv.setItem(34, createItem(ItemCreator.createItem(Material.LEATHER_HELMET, color), "Leather Helmet"));

        this.inv.setItem(53, createItem(Material.BARRIER, "Cancel"));
    }

    /**
     * generates the Dys slots
     */
    private void setColors() {
        this.inv.setItem(0, createItem(Material.BLACK_DYE, "Dye black"));
        this.inv.setItem(1, createItem(Material.BLUE_DYE, "Dye blue"));
        this.inv.setItem(2, createItem(Material.BROWN_DYE, "Dye brown"));
        this.inv.setItem(3, createItem(Material.CYAN_DYE, "Dye cyan"));
        this.inv.setItem(4, createItem(Material.GRAY_DYE, "Dye gray"));
        this.inv.setItem(5, createItem(Material.GREEN_DYE, "Dye green"));
        this.inv.setItem(6, createItem(Material.LIGHT_BLUE_DYE, "Dye light blue"));
        this.inv.setItem(7, createItem(Material.LIGHT_GRAY_DYE, "Dye light gray"));
        this.inv.setItem(8, createItem(Material.LIME_DYE, "Dye lime"));
        this.inv.setItem(9, createItem(Material.MAGENTA_DYE, "Dye magenta"));
        this.inv.setItem(10, createItem(Material.ORANGE_DYE, "Dye orange"));
        this.inv.setItem(11, createItem(Material.PINK_DYE, "Dye pink"));
        this.inv.setItem(12, createItem(Material.PURPLE_DYE, "Dye purple"));
        this.inv.setItem(13, createItem(Material.RED_DYE, "Dye red"));
        this.inv.setItem(14, createItem(Material.WHITE_DYE, "Dye white"));
        this.inv.setItem(15, createItem(Material.YELLOW_DYE, "Dye yellow"));
        this.inv.setItem(16, createItem(Material.NAME_TAG, "Custom color code"));
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