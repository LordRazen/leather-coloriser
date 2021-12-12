package com.minecraftheads.leathercoloriserpro.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.MapMeta;

public class ItemCreator {
    /**
     * Creates a leather armor ItemStack with a color
     * @param item ItemStack
     * @param color Material
     * @return ItemStack
     */
    public static ItemStack createItem(Material item, Material color) {
        ItemStack armor = new ItemStack(item, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();
        if (meta != null) {
            switch (color.toString()) {
                case "BLACK_DYE":
                    meta.setColor(Color.BLACK);
                    break;
                case "BLUE_DYE":
                    meta.setColor(Color.BLUE);
                    break;
                case "BROWN_DYE":
                    meta.setColor(Color.fromBGR(0x00, 0x30, 0x60));
                    break;
                case "CYAN_DYE":
                    meta.setColor(Color.AQUA);
                    break;
                case "GRAY_DYE":
                    meta.setColor(Color.GRAY);
                    break;
                case "GREEN_DYE":
                    meta.setColor(Color.GREEN);
                    break;
                case "LIGHT_BLUE_DYE":
                    meta.setColor(Color.NAVY);
                    break;
                case "LIGHT_GRAY_DYE":
                    meta.setColor(Color.SILVER);
                    break;
                case "LIME_DYE":
                    meta.setColor(Color.LIME);
                    break;
                case "MAGENTA_DYE":
                    meta.setColor(Color.FUCHSIA);
                    break;
                case "ORANGE_DYE":
                    meta.setColor(Color.ORANGE);
                    break;
                case "PINK_DYE":
                    meta.setColor(Color.fromBGR(0xAF, 0xAF, 0xFF));
                    break;
                case "PURPLE_DYE":
                    meta.setColor(Color.PURPLE);
                    break;
                case "RED_DYE":
                    meta.setColor(Color.RED);
                    break;
                case "WHITE_DYE":
                    meta.setColor(Color.WHITE);
                    break;
                case "YELLOW_DYE":
                    meta.setColor(Color.YELLOW);
                    break;
            }
        }
        armor.setItemMeta(meta);
        return armor;
    }
}