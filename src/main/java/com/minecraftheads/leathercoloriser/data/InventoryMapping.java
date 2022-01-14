package com.minecraftheads.leathercoloriser.data;

import com.minecraftheads.pluginUtils.config.LanguageHandler;
import com.minecraftheads.leathercoloriser.handlers.SelectionHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;


public enum InventoryMapping {
    // Control units
    RESET(6, Material.WATER_BUCKET, LanguageMapping.COLOR_RESET.getString(), "resetColor"),
    RANDOM(7, CustomHeads.RANDOM.asMaterial(), LanguageMapping.COLOR_RANDOM.getString(), "randomColor"),
    HEXCOMMAND(8, Material.NAME_TAG, LanguageMapping.COLOR_STRING.getString(), "hexCommand"),
    DECREASE_HUE(16, CustomHeads.DECREASE_HUE.asMaterial(), LanguageMapping.DECREASE_HUE.getString(), "decrease_hue"),
    INCREASE_HUE(17, CustomHeads.INCREASE_HUE.asMaterial(), LanguageMapping.INCREASE_HUE.getString(), "increase_hue"),
    DECREASE_SATURATION(25, CustomHeads.DECREASE_SATURATION.asMaterial(), LanguageMapping.DECREASE_SATURATION.getString(), "decrease_saturation"),
    INCREASE_SATURATION(26, CustomHeads.INCREASE_SATURATION.asMaterial(), LanguageMapping.INCREASE_SATURATION.getString(), "increase_saturation"),
    DECREASE_BRIGHTNESS(34, CustomHeads.DECREASE_BRIGHTNESS.asMaterial(), LanguageMapping.DECREASE_BRIGHTNESS.getString(), "decrease_brightness"),
    INCREASE_BRIGHTNESS(35, CustomHeads.INCREASE_BRIGHTNESS.asMaterial(), LanguageMapping.INCREASE_BRIGHTNESS.getString(), "increase_brightness"),

    // Colors
    YELLOW_DYE(18, Material.YELLOW_DYE, LanguageMapping.YELLOW.getString(), "dye"),
    ORANGE_DYE(19, Material.ORANGE_DYE, LanguageMapping.ORANGE.getString(), "dye"),
    RED_DYE(20, Material.RED_DYE, LanguageMapping.RED.getString(), "dye"),
    BROWN_DYE(21, Material.BROWN_DYE, LanguageMapping.BROWN.getString(), "dye"),
    LIME_DYE(22, Material.LIME_DYE, LanguageMapping.LIME.getString(), "dye"),
    GREEN_DYE(23, Material.GREEN_DYE, LanguageMapping.GREEN.getString(), "dye"),
    PINK_DYE(27, Material.PINK_DYE, LanguageMapping.PINK.getString(), "dye"),
    MAGENTA_DYE(28, Material.MAGENTA_DYE, LanguageMapping.MAGENTA.getString(), "dye"),
    PURPLE_DYE(29, Material.PURPLE_DYE, LanguageMapping.PURPLE.getString(), "dye"),
    LIGHT_BLUE_DYE(30, Material.LIGHT_BLUE_DYE, LanguageMapping.LIGHT_BLUE.getString(), "dye"),
    CYAN_DYE(31, Material.CYAN_DYE, LanguageMapping.CYAN.getString(), "dye"),
    BLUE_DYE(32, Material.BLUE_DYE, LanguageMapping.BLUE.getString(), "dye"),
    WHITE_DYE(37, Material.WHITE_DYE, LanguageMapping.WHITE.getString(), "dye"),
    LIGHT_GRAY_DYE(38, Material.LIGHT_GRAY_DYE, LanguageMapping.LIGHT_GRAY.getString(), "dye"),
    GRAY_DYE(39, Material.GRAY_DYE, LanguageMapping.GRAY.getString(), "dye"),
    BLACK_DYE(40, Material.BLACK_DYE, LanguageMapping.BLACK.getString(), "dye"),

    // armor
    LEATHER_HELMET(0, Material.LEATHER_HELMET, LanguageMapping.LEATHER_HELMET.getString(), "armor"),
    LEATHER_CHESTPLATE(1, Material.LEATHER_CHESTPLATE, LanguageMapping.LEATHER_CHESTPLATE.getString(), "armor"),
    LEATHER_LEGGINGS(2, Material.LEATHER_LEGGINGS, LanguageMapping.LEATHER_LEGGINGS.getString(), "armor"),
    LEATHER_BOOTS(3, Material.LEATHER_BOOTS, LanguageMapping.LEATHER_BOOTS.getString(), "armor"),
    LEATHER_HORSE_ARMOR(4, Material.LEATHER_HORSE_ARMOR, LanguageMapping.LEATHER_HORSE_ARMOR.getString(), "armor")
    ;


    private int slot;
    private Material mat;
    private String name;
    private String action;

    /**
     * Constructor
     *
     * @param slot int
     * @param mat Material
     * @param name String
     * @param action String
     */
    InventoryMapping(int slot, Material mat, String name, String action) {
        this.slot = slot;
        this.mat = mat;
        this.name = name;
        this.action = action;
    }

    /**
     * Returns the action of the given enum
     * @return String
     */
    public String getAction(){
        return this.action;
    }

    /**
     * returns the slot number of the given enum
     * @return int
     */
    public int getSlot() { return this.slot; }

    /**
     * returns the Material of the given enum
     * @return Material
     */
    public Material getMaterial() { return this.mat; }

    /**
     * returns the ItemStack (colored if armor)
     * @param p Player
     * @return ItemStack
     */
    public ItemStack getItemStack(Player p) {
        ItemStack item = new ItemStack(mat, 1);
        // If item is the Random head overwrite it with CustomHead
        // TODO: Find better Solution for Custom Heads!
        switch(action) {
            case "randomColor":
                item = CustomHeads.RANDOM.asItemStack();
                break;
            case "decrease_hue":
                item = CustomHeads.DECREASE_HUE.asItemStack();
                break;
            case "increase_hue":
                item = CustomHeads.INCREASE_HUE.asItemStack();
                break;
            case "decrease_saturation":
                item = CustomHeads.DECREASE_SATURATION.asItemStack();
                break;
            case "increase_saturation":
                item = CustomHeads.INCREASE_SATURATION.asItemStack();
                break;
            case "decrease_brightness":
                item = CustomHeads.DECREASE_BRIGHTNESS.asItemStack();
                break;
            case "increase_brightness":
                item = CustomHeads.INCREASE_BRIGHTNESS.asItemStack();
                break;
        }

        // set displayname
        ItemMeta isMeta = item.getItemMeta();
        isMeta.setDisplayName(name);
        item.setItemMeta(isMeta);

        // if selected piece is an armor piece, color it and return the colored item
        if (action.equals("armor")) {
            LeatherArmorMeta laMeta = (LeatherArmorMeta) item.getItemMeta();
            laMeta.setColor(SelectionHandler.getColor(p));
            laMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(laMeta);
        }
        return item;
    }

    /**
     * returns the enum by given Slot (needed to find by clicked slot)
     * @param slot int
     * @return InventoryMapping
     */
    public static InventoryMapping getBySlot(int slot) throws NullPointerException {
        for (InventoryMapping im : InventoryMapping.values()) {
            if (im.slot == slot) {
                return im;
            }
        }
        throw new NullPointerException();
    }


}
