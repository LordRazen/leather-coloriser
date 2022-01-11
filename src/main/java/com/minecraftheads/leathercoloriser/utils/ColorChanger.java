package com.minecraftheads.leathercoloriser.utils;

import com.minecraftheads.leathercoloriser.handlers.SelectionHandler;
import org.bukkit.Color;
import org.bukkit.entity.Player;

public enum ColorChanger {
    DECREASE_HUE{
        public void apply(Player p) {
            Color rgb = SelectionHandler.getColor(p);
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[0] = (hsb[0] + 0.05) > 1 ? (float) (hsb[0] - 0.95) : (float) (hsb[0] + 0.05);
            SelectionHandler.setColor(p, ColorChanger.colorFromHSB(hsb));
        }
    },
    INCREASE_HUE{
        public void apply(Player p) {
            Color rgb = SelectionHandler.getColor(p);
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[0] = (hsb[0] - 0.05) < 0 ? (float) (hsb[0] + 0.95) : (float) (hsb[0] - 0.05);
            SelectionHandler.setColor(p, ColorChanger.colorFromHSB(hsb));
        }
    },
    DECREASE_SATURATION {
        public void apply(Player p) {
            Color rgb = SelectionHandler.getColor(p);
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[1] = (hsb[1] - 0.1) < 0 ? 0 : (float) (hsb[1] - 0.1);
            SelectionHandler.setColor(p, ColorChanger.colorFromHSB(hsb));
        }
    },
    INCREASE_SATURATION{
        public void apply(Player p) {
            Color rgb = SelectionHandler.getColor(p);
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[1] = (hsb[1] + 0.1) > 1 ? 1 : (float) (hsb[1] + 0.1);
            SelectionHandler.setColor(p, ColorChanger.colorFromHSB(hsb));
        }
    },
    DECREASE_BRIGHTNESS{
        public void apply(Player p) {
            Color rgb = SelectionHandler.getColor(p);
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[2] = (hsb[2] - 0.1) < 0 ? 0 : (float) (hsb[2] - 0.1);
            SelectionHandler.setColor(p, ColorChanger.colorFromHSB(hsb));
        }
    },
    INCREASE_BRIGHTNESS{
        public void apply(Player p) {
            Color rgb = SelectionHandler.getColor(p);
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[2] = (hsb[2] + 0.1) > 1 ? 1 : (float) (hsb[2] + 0.1);
            SelectionHandler.setColor(p, ColorChanger.colorFromHSB(hsb));
        }
    };

    /**
     * Sets the color after calculating it from the color in storage
     * @param p Player
     */
    public abstract void apply(Player p);

    /**
     * calculates Color from hsb float value
     * @param hsb float[]
     * @return Color
     */
    private static Color colorFromHSB(float[] hsb) {
        int color = java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        return Color.fromRGB(Integer.parseInt(hexColor.replace("#", ""), 16));
    }
}