package com.minecraftheads.leathercoloriser.utils;

import com.minecraftheads.leathercoloriser.data.DyeColorMapping;
import com.minecraftheads.leathercoloriser.data.InventoryMapping;
import com.minecraftheads.leathercoloriser.handlers.InventoryHandler;
import com.minecraftheads.leathercoloriser.handlers.LanguageHandler;
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
        this.color = DyeColorMapping.DEFAULT.getColor();
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
        setItems();
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
     * generate Inventory from InventoryMapping
     */
    private void setItems() {
        for (InventoryMapping im : InventoryMapping.values()) {
            this.inv.setItem(im.getSlot(), im.getItemStack());
        }
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