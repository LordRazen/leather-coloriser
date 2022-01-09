package com.minecraftheads.leathercoloriser.data;

import com.minecraftheads.leathercoloriser.utils.CustomHeads;
import com.minecraftheads.leathercoloriser.utils.ItemCreator;
import org.bukkit.Material;

public enum InventoryMapping {
    RANDOM(7, CustomHeads.RANDOM.asMaterial(), "random_color", "randomColor"),
    HEXCOMMAND(8, Material.NAME_TAG, "color_string", "hexCommand"),
    DECREASE_HUE(16, Material.PURPLE_CONCRETE, "decrease_hue", "decrease_hue"),
    INCREASE_HUE(17, Material.RED_TERRACOTTA, "increase_hue", "increase_hue"),
    DECREASE_SATURATION(25, Material.BLUE_STAINED_GLASS, "decrease_saturation", "decrease_saturation"),
    INCREASE_SATURATION(26, Material.BLUE_CONCRETE, "increase_saturation", "increase_saturation"),
    DECREASE_BRIGHTNESS(34, Material.BLACK_CONCRETE, "decrease_brightness", "decrease_brightness"),
    INCREASE_BRIGHTNESS(35, Material.WHITE_CONCRETE, "increase_brightness", "increase_brightness")
    ;


    private int slot;
    private Material mat;
    private String msg;
    private String action;

    InventoryMapping(int slot, Material mat, String msg, String action) {
        this.slot = slot;
        this.mat = mat;
        this.msg = msg;
        this.action = action;
    }

    public String getAction(){
        return this.action;
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
