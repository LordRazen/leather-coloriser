package com.minecraftheads.leathercoloriserpro.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemCreator {
    public static ItemStack createItem(ItemStack item, Material color) {
        LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
        switch (color) {
            case BLACK_DYE:
                meta.setColor(Color.BLACK);
            case BLUE_DYE:
                meta.setColor(Color.BLUE);
            case BROWN_DYE:
                meta.setColor(Color.fromBGR(0x00, 0x30, 0x60));
            case CYAN_DYE:
                meta.setColor(Color.AQUA);
            case GRAY_DYE:
                meta.setColor(Color.GRAY);
            case GREEN_DYE:
                meta.setColor(Color.GREEN);
            case LIGHT_BLUE_DYE:
                meta.setColor(Color.NAVY);
            case LIGHT_GRAY_DYE:
                meta.setColor(Color.SILVER);
            case LIME_DYE:
                meta.setColor(Color.LIME);
            case MAGENTA_DYE:
                meta.setColor(Color.FUCHSIA);
            case ORANGE_DYE:
                meta.setColor(Color.ORANGE);
            case PINK_DYE:
                meta.setColor(Color.fromBGR(0xAF, 0xAF, 0xFF));
            case PURPLE_DYE:
                meta.setColor(Color.PURPLE);
            case RED_DYE:
                meta.setColor(Color.RED);
            case WHITE_DYE:
                meta.setColor(Color.WHITE);
            case YELLOW_DYE:
                meta.setColor(Color.YELLOW);
        }
        item.setItemMeta(meta);
        return item;
    }
}