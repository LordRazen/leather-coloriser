package com.minecraftheads.leathercoloriser.data;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.material.Dye;

public enum DyeColorMapping {
    YELLOW_DYE(Material.YELLOW_DYE, DyeColor.YELLOW.getColor()),
    ORANGE_DYE(Material.ORANGE_DYE, DyeColor.ORANGE.getColor()),
    RED_DYE(Material.RED_DYE, DyeColor.RED.getColor()),
    BROWN_DYE(Material.BROWN_DYE, DyeColor.BROWN.getColor()),
    LIME_DYE(Material.LIME_DYE, DyeColor.LIME.getColor()),
    GREEN_DYE(Material.GREEN_DYE, DyeColor.GREEN.getColor()),
    PINK_DYE(Material.PINK_DYE, DyeColor.PINK.getColor()),
    MAGENTA_DYE(Material.MAGENTA_DYE, DyeColor.MAGENTA.getColor()),
    PURPLE_DYE(Material.PURPLE_DYE, DyeColor.PURPLE.getColor()),
    LIGHT_BLUE_DYE(Material.LIGHT_BLUE_DYE, DyeColor.LIGHT_BLUE.getColor()),
    CYAN_DYE(Material.CYAN_DYE, DyeColor.CYAN.getColor()),
    BLUE_DYE(Material.BLUE_DYE, DyeColor.BLUE.getColor()),
    WHITE_DYE(Material.WHITE_DYE, DyeColor.WHITE.getColor()),
    LIGHT_GRAY_DYE(Material.LIGHT_GRAY_DYE, DyeColor.LIGHT_GRAY.getColor()),
    GRAY_DYE(Material.GRAY_DYE, DyeColor.GRAY.getColor()),
    BLACK_DYE(Material.BLACK_DYE, DyeColor.BLACK.getColor()),
    DEFAULT(Material.AIR, Color.fromRGB(0xA06540))
    ;

    private Material mat;
    private Color color;
    DyeColorMapping(Material mat, Color color) {
        this.color = color;
        this.mat = mat;
    }

    public Color getColor() {
        return color;
    }

    public static Color getColorByMat(Material dye) {
        for (DyeColorMapping dcm : DyeColorMapping.values()) {
            if (dcm.mat.equals(dye)) {
                return dcm.color;
            }
        }
        return null;
    }
}
