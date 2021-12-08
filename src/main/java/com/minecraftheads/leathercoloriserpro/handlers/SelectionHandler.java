package com.minecraftheads.leathercoloriserpro.handlers;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Basic storage of player and ItemStack relation
 * This is needed so we can track the selected armor piece across the inventories
 */
public class SelectionHandler {

    private final static HashMap<Player, ItemStack> ItemSelection = new HashMap<>();

    /**
     * Adds entry to the HashMap with Player as key
     * @param p Player
     * @param i ItemStack
     */
    public static void addItem(Player p, ItemStack i) { ItemSelection.put(p, i); }

    /**
     * Remove entry from the Hashmap
     * @param p Player
     */
    public static void removeItem(Player p) { ItemSelection.remove(p); }

    /**
     * Return the ItemStack which matches the Player
     * @param p Player
     * @return ItemStack
     */
    public static ItemStack getItem(Player p) { return ItemSelection.get(p); }

    /**
     * Get complete list of Player -> ItemStack relations
     * @return HashMap<Player, ItemStack>
     */
    public static HashMap<Player, ItemStack> getItemList() { return ItemSelection; }
}