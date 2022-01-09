package com.minecraftheads.leathercoloriser.utils;

import org.bukkit.Color;

public enum ColorChanger {
    DECREASE_HUE{
        public Color apply(Color rgb) {
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[0] = (hsb[0] + 0.05) > 1 ? (float) (hsb[0] - 0.95) : (float) (hsb[0] + 0.05);
            return ColorChanger.colorFromHSB(hsb);
        }
    },
    INCREASE_HUE{
        public Color apply(Color rgb) {
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[0] = (hsb[0] - 0.05) < 0 ? (float) (hsb[0] + 0.95) : (float) (hsb[0] - 0.05);
            return ColorChanger.colorFromHSB(hsb);
        }
    },
    DECREASE_SATURATION {
        public Color apply(Color rgb) {
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[1] = (hsb[1] - 0.1) < 0 ? 0 : (float) (hsb[1] - 0.1);
            return ColorChanger.colorFromHSB(hsb);
        }
    },
    INCREASE_SATURATION{
        public Color apply(Color rgb) {
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[1] = (hsb[1] + 0.1) > 1 ? 1 : (float) (hsb[1] + 0.1);
            return ColorChanger.colorFromHSB(hsb);
        }
    },
    DECREASE_BRIGHTNESS{
        public Color apply(Color rgb) {
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[2] = (hsb[2] - 0.1) < 0 ? 0 : (float) (hsb[2] - 0.1);
            return ColorChanger.colorFromHSB(hsb);
        }
    },
    INCREASE_BRIGHTNESS{
        public Color apply(Color rgb) {
            float[] hsb = java.awt.Color.RGBtoHSB(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), null);
            hsb[2] = (hsb[2] + 0.1) > 1 ? 1 : (float) (hsb[2] + 0.1);
            return ColorChanger.colorFromHSB(hsb);
        }
    };

    public abstract Color apply(Color actualColor);

    private static Color colorFromHSB(float[] hsb) {
        int color = java.awt.Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        return Color.fromRGB(Integer.parseInt(hexColor.replace("#", ""), 16));
    }
}