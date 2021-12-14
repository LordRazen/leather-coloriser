package com.minecraftheads.leathercoloriserpro.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.MapMeta;

public class ItemCreator {
    /**
     * Creates a leather armor ItemStack with a color
     * @param item ItemStack
     * @param color Color
     * @return ItemStack
     */
    public static ItemStack createItem(Material item, Color color) {
        ItemStack armor = new ItemStack(item, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
        if (meta != null) {
            meta.setColor(color);
        }
        armor.setItemMeta(meta);
        return armor;
    }
}