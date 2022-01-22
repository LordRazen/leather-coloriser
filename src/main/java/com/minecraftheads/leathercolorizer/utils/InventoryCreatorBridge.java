package com.minecraftheads.leathercolorizer.utils;

import com.minecraftheads.leathercolorizer.data.InventoryMapping;
import com.minecraftheads.leathercolorizer.data.LanguageMapping;
import com.minecraftheads.pluginUtils.inventory.InventoryCreator;
import org.bukkit.entity.Player;

public class InventoryCreatorBridge {

    /**
     * This is a bridge to pluginUtils.
     * It adds all items from InventoryMapping to the custom Inventory and then opens it
     *
     * @param player Player
     */
    public InventoryCreatorBridge(Player player) {
        InventoryCreator inv = new InventoryCreator(LanguageMapping.TITLE.getString());
        for (InventoryMapping im : InventoryMapping.values()) {
            inv.addItem(im.getSlot(), im.getItemStack(player));
        }
        inv.openInventory(player);
    }
}