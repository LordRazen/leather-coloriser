package com.minecraftheads.leathercoloriser.utils;

import com.minecraftheads.leathercoloriser.handlers.LanguageHandler;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemCreator {
    /**
     * Creates a leather armor ItemStack with a color
     *
     * @param material Material
     * @param name     String
     * @param color    Color
     * @return ItemStack
     */
    public static ItemStack createDyedItem(Material material, String name, Color color) {
        ItemStack armor = renameItem(new ItemStack(material, 1), LanguageHandler.getMessage(name));
        LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
        if (meta != null) {
            meta.setColor(color);
        }
        armor.setItemMeta(meta);
        return armor;
    }

    /**
     * Basic method to create ItemStack with name from Material
     *
     * @param material Material
     * @param name     String
     * @return ItemStack
     */
    public static ItemStack createItem(Material material, String name) {
        return renameItem(new ItemStack(material, 1), name);
    }

    /**
     * Change the name of a ItemStack
     *
     * @param item ItemStack
     * @param name String
     * @return ItemStack
     */
    public static ItemStack renameItem(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(LanguageHandler.getMessage(name));
        item.setItemMeta(meta);
        return item;
    }
}