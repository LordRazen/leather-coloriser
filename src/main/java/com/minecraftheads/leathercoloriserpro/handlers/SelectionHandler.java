package com.minecraftheads.leathercoloriserpro.handlers;

import com.minecraftheads.leathercoloriserpro.utils.Logger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class SelectionHandler {
    private final static HashMap<Player, ItemStack> ItemSelection = new HashMap<>();

    public static void addItem(Player p, ItemStack i) { ItemSelection.put(p, i); }
    public static void removeItem(Player p) { ItemSelection.remove(p); }
    public static ItemStack getItem(Player p) {
        return ItemSelection.get(p);
    }

    public static Map<Player, ItemStack> getItemList() { return ItemSelection; }

}