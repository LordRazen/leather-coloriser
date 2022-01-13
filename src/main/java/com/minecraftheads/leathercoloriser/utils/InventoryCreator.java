package com.minecraftheads.leathercoloriser.utils;

import com.minecraftheads.leathercoloriser.data.DyeColorMapping;
import com.minecraftheads.leathercoloriser.data.InventoryMapping;
import com.minecraftheads.leathercoloriser.handlers.InventoryHandler;
import com.minecraftheads.leathercoloriser.handlers.LanguageHandler;
import com.minecraftheads.leathercoloriser.handlers.SelectionHandler;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryCreator {
    private Inventory inv;
    private final Player p;


    /**
     * Constructor
     *
     * @param player Player
     */
    public InventoryCreator(Player player) {
        this.p = player;
        initializeInventory();
        openInventory(player);
    }



    /**
     * open the inventory with the colored items, color is set via DYE selection
     */
    public void initializeInventory() {
        this.inv = Bukkit.createInventory(null, 54, LanguageHandler.getMessage("title"));
        for (InventoryMapping im : InventoryMapping.values()) {
            this.inv.setItem(im.getSlot(), im.getItemStack(p));
        }
        setWatermark();
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