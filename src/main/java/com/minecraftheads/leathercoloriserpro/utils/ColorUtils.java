package com.minecraftheads.leathercoloriserpro.utils;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;

public class ColorUtils {

    public static Color DEFAULT_COLOR = Color.fromRGB(0xA06540);

    /**
     * Modify Color based on the HSB (HSV) Model
     * https://www.rapidtables.com/web/color/RGB_Color.html
     *
     * @param rgb  Color
     * @param type ColorChangeType
     * @return Color
     */
    public static Color changeColor(Color rgb, ColorChangeType type) {
        // Convert RGB to HSB (HSV)
        float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);

        // Modify HSV Values
        switch (type) {
            // Hue values are circular!
            case DECREASE_HUE:
                hsb[0] = (hsb[0] + 0.05) > 1 ? (float) (hsb[0] - 0.95) : (float) (hsb[0] + 0.05);
                break;
            case INCREASE_HUE:
                hsb[0] = (hsb[0] - 0.05) < 0 ? (float) (hsb[0] + 0.95) : (float) (hsb[0] - 0.05);
                break;
            case DECREASE_SATURATION:
                hsb[1] = (hsb[1] - 0.1) < 0 ? 0 : (float) (hsb[1] - 0.1);
                break;
            case INCREASE_SATURATION:
                hsb[1] = (hsb[1] + 0.1) > 1 ? 1 : (float) (hsb[1] + 0.1);
                break;
            case DECREASE_BRIGHTNESS:
                hsb[2] = (hsb[2] - 0.1) < 0 ? 0 : (float) (hsb[2] - 0.1);
                break;
            case INCREASE_BRIGHTNESS:
                hsb[2] = (hsb[2] + 0.1) > 1 ? 1 : (float) (hsb[2] + 0.1);
                break;
        }

        // Convert the HSB Color to a Color Object
        int color = java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        return Color.fromRGB(Integer.parseInt(hexColor.replace("#", ""), 16));
    }

    /**
     * Get Color Object from Minecraft Dye
     *
     * @param mat Material
     * @return Color
     */
    public static Color getColorFromDye(Material mat) {
        System.out.print(DyeColor.BROWN.getColor().toString());
        System.out.print(DEFAULT_COLOR.toString()); // TODO
        switch (mat) {
            case BLACK_DYE:
                return DyeColor.BLACK.getColor();
            case BLUE_DYE:
                return DyeColor.BLUE.getColor();
            case BROWN_DYE:
                return DyeColor.BROWN.getColor();
            case CYAN_DYE:
                return DyeColor.CYAN.getColor();
            case GRAY_DYE:
                return DyeColor.GRAY.getColor();
            case GREEN_DYE:
                return DyeColor.GREEN.getColor();
            case LIGHT_BLUE_DYE:
                return DyeColor.LIGHT_BLUE.getColor();
            case LIGHT_GRAY_DYE:
                return DyeColor.LIGHT_GRAY.getColor();
            case LIME_DYE:
                return DyeColor.LIME.getColor();
            case MAGENTA_DYE:
                return DyeColor.MAGENTA.getColor();
            case ORANGE_DYE:
                return DyeColor.ORANGE.getColor();
            case PINK_DYE:
                return DyeColor.PINK.getColor();
            case PURPLE_DYE:
                return DyeColor.PURPLE.getColor();
            case RED_DYE:
                return DyeColor.RED.getColor();
            case WHITE_DYE:
                return DyeColor.WHITE.getColor();
            case YELLOW_DYE:
                return DyeColor.YELLOW.getColor();
        }
        return DyeColor.BROWN.getColor();
    }
}
