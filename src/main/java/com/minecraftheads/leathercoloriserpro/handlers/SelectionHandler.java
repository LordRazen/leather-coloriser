package com.minecraftheads.leathercoloriserpro.handlers;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Basic storage of player and ItemStack relation
 * This is needed so we can track the selected armor piece across the inventories
 */
public class SelectionHandler {

    private final static HashMap<Player, Material> ColorSelection = new HashMap<Player, Material>();

    /**
     * Adds entry to the HashMap with Player as key
     * @param p Player
     * @param mat Material
     */
    public static void addColor(Player p, Material mat) { ColorSelection.put(p, mat); }

    /**
     * Remove entry from the Hashmap
     * @param p Player
     */
    public static void removeColor(Player p) { ColorSelection.remove(p); }

    /**
     * Return the Material which matches the Player
     * @param p Player
     * @return Material
     */
    public static Material getColor(Player p) { return ColorSelection.get(p); }

    /**
     * Get complete list of Player -> Material relations
     * @return HashMap<Player, Material>
     */
    public static HashMap<Player, Material> getColorList() { return ColorSelection; }
}