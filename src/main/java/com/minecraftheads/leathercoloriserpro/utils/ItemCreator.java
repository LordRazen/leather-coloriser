package com.minecraftheads.leathercoloriserpro.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
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
            switch (color) {
                case BLACK_DYE:
                    meta.setColor(DyeColor.BLACK.getColor());
                    break;
                case BLUE_DYE:
                    meta.setColor(DyeColor.BLUE.getColor());
                    break;
                case BROWN_DYE:
                    meta.setColor(DyeColor.BROWN.getColor());
                    break;
                case CYAN_DYE:
                    meta.setColor(DyeColor.CYAN.getColor());
                    break;
                case GRAY_DYE:
                    meta.setColor(DyeColor.GRAY.getColor());
                    break;
                case GREEN_DYE:
                    meta.setColor(DyeColor.GREEN.getColor());
                    break;
                case LIGHT_BLUE_DYE:
                    meta.setColor(DyeColor.LIGHT_BLUE.getColor());
                    break;
                case LIGHT_GRAY_DYE:
                    meta.setColor(DyeColor.LIGHT_GRAY.getColor());
                    break;
                case LIME_DYE:
                    meta.setColor(DyeColor.LIME.getColor());
                    break;
                case MAGENTA_DYE:
                    meta.setColor(DyeColor.MAGENTA.getColor());
                    break;
                case ORANGE_DYE:
                    meta.setColor(DyeColor.ORANGE.getColor());
                    break;
                case PINK_DYE:
                    meta.setColor(DyeColor.PINK.getColor());
                    break;
                case PURPLE_DYE:
                    meta.setColor(DyeColor.PURPLE.getColor());
                    break;
                case RED_DYE:
                    meta.setColor(DyeColor.RED.getColor());
                    break;
                case WHITE_DYE:
                    meta.setColor(DyeColor.WHITE.getColor());
                    break;
                case YELLOW_DYE:
                    meta.setColor(DyeColor.YELLOW.getColor());
                    break;
            }
        }
        armor.setItemMeta(meta);
        return armor;
    }

    public static ItemStack createItem(Material item, String color) {
        ItemStack armor = new ItemStack(item, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) armor.getItemMeta();

        // parse hex colors from user input string
        int red = Integer.parseInt(color.substring(0, 2), 16);
        int green = Integer.parseInt(color.substring(2, 4), 16);
        int blue = Integer.parseInt(color.substring(4, 6), 16);
        if (meta != null) {
            meta.setColor(Color.fromRGB(red, green, blue));
        }
        armor.setItemMeta(meta);
        return armor;
    }
}