package com.minecraftheads.leathercoloriser.data;

import com.minecraftheads.leathercoloriser.handlers.LanguageHandler;
import com.minecraftheads.leathercoloriser.handlers.SelectionHandler;
import com.minecraftheads.leathercoloriser.utils.CustomHeads;
import com.minecraftheads.leathercoloriser.utils.ItemCreator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Map;

public enum InventoryMapping {
    // Control units
    RESET(7, Material.WATER_BUCKET, "color_reset", "reset"),
    RANDOM(7, CustomHeads.RANDOM.asMaterial(), "random_color", "randomColor"),
    HEXCOMMAND(8, Material.NAME_TAG, "color_string", "hexCommand"),
    DECREASE_HUE(16, Material.PURPLE_CONCRETE, "decrease_hue", "decrease_hue"),
    INCREASE_HUE(17, Material.RED_TERRACOTTA, "increase_hue", "increase_hue"),
    DECREASE_SATURATION(25, Material.BLUE_STAINED_GLASS, "decrease_saturation", "decrease_saturation"),
    INCREASE_SATURATION(26, Material.BLUE_CONCRETE, "increase_saturation", "increase_saturation"),
    DECREASE_BRIGHTNESS(34, Material.BLACK_CONCRETE, "decrease_brightness", "decrease_brightness"),
    INCREASE_BRIGHTNESS(35, Material.WHITE_CONCRETE, "increase_brightness", "increase_brightness"),

    //Dye Colors
    YELLOW_DYE(18, Material.YELLOW_DYE, "yellow", "dye"),
    ORANGE_DYE(19, Material.ORANGE_DYE, "orange", "dye"),
    RED_DYE(20, Material.RED_DYE, "red", "dye"),
    BROWN_DYE(21, Material.BROWN_DYE, "brown", "dye"),
    LIME_DYE(22, Material.LIME_DYE, "lime", "dye"),
    GREEN_DYE(23, Material.GREEN_DYE, "green", "dye"),
    PINK_DYE(27, Material.PINK_DYE, "pink", "dye"),
    MAGENTA_DYE(28, Material.MAGENTA_DYE, "magenta", "dye"),
    PURPLE_DYE(29, Material.PURPLE_DYE, "purple", "dye"),
    LIGHT_BLUE_DYE(30, Material.LIGHT_BLUE_DYE, "light_blue", "dye"),
    CYAN_DYE(31, Material.CYAN_DYE, "cyan", "dye"),
    BLUE_DYE(32, Material.BLUE_DYE, "blue", "dye"),
    WHITE_DYE(37, Material.WHITE_DYE, "white", "dye"),
    LIGHT_GRAY_DYE(38, Material.LIGHT_GRAY_DYE, "light_gray", "dye"),
    GRAY_DYE(39, Material.GRAY_DYE, "gray", "dye"),
    BLACK_DYE(40, Material.BLACK_DYE, "black", "dye")
    ;


    private int slot;
    private Material mat;
    private String name;
    private String action;

    InventoryMapping(int slot, Material mat, String name, String action) {
        this.slot = slot;
        this.mat = mat;
        this.name = name;
        this.action = action;
    }

    public String getAction(){
        return this.action;
    }
    public int getSlot() { return this.slot; }
    public String getName() { return this.name; }
    public Material getMaterial() { return this.mat; }

    public ItemStack getItemStack() {
        ItemStack is = new ItemStack(mat, 1);
        ItemMeta isMeta = is.getItemMeta();
        isMeta.setDisplayName(LanguageHandler.getMessage(name));
        is.setItemMeta(isMeta);
        return is;
    }

    public static InventoryMapping getBySlot(int slot) {
        for (InventoryMapping im : InventoryMapping.values()) {
            if (im.slot == slot) {
                return im;
            }
        }
        return null;
    }


}
