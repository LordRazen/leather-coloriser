package com.minecraftheads.leathercolorizer.data;

import com.minecraftheads.pluginUtils.config.LanguageHandler;

public enum LanguageMapping {
    PREFIX("prefix"),
    TITLE_GUI("title_gui"),
    TITLE_CHAT("title_chat"),
    ERROR_INVALID_COLOR("error_invalid_color"),
    ERROR_ITEM_MISSING("error_item_missing"),
    ERROR_PERMISSION_MISSING("error_permission_missing"),
    INFO_OPEN_LC("info_open_lc"),
    INFO_OPEN_LC_RGB("info_open_lc_rgb"),
    INFO_INFO("info_info"),
    INFO_RELOAD("info_reload"),
    INFO_VERSION("info_version"),
    RELOAD("reload"),
    COLOR_RESET("color_reset"),
    COLOR_RANDOM("color_random"),
    COLOR_STRING("color_string"),
    DECREASE_HUE("decrease_hue"),
    INCREASE_HUE("increase_hue"),
    DECREASE_SATURATION("decrease_saturation"),
    INCREASE_SATURATION("increase_saturation"),
    DECREASE_BRIGHTNESS("decrease_brightness"),
    INCREASE_BRIGHTNESS("increase_brightness"),
    YELLOW("yellow"),
    ORANGE("orange"),
    RED("red"),
    BROWN("brown"),
    LIME("lime"),
    GREEN("green"),
    LIGHT_BLUE("light_blue"),
    CYAN("cyan"),
    BLUE("blue"),
    PINK("pink"),
    MAGENTA("magenta"),
    PURPLE("purple"),
    WHITE("white"),
    LIGHT_GRAY("light_gray"),
    GRAY("gray"),
    BLACK("black"),
    LEATHER_HELMET("leather_helmet"),
    LEATHER_CHESTPLATE("leather_chestplate"),
    LEATHER_LEGGINGS("leather_leggings"),
    LEATHER_BOOTS("leather_boots"),
    LEATHER_HORSE_ARMOR("leather_horse_armor"),
    WOLF_ARMOR("wolf_armor");

    private final String path;

    LanguageMapping(String path) {
        this.path = path;
    }

    public String getString() {
        return LanguageHandler.getString(path);
    }

    public String getStringWithPrefix() {
        return LanguageMapping.PREFIX.getString() + "§r " + LanguageHandler.getString(path);
    }

}
