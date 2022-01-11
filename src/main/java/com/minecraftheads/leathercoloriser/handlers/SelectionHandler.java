package com.minecraftheads.leathercoloriser.handlers;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Basic storage of player and Color relation
 * This is needed so we can store the Color if the inventory is closed
 */
public class SelectionHandler {

    private final static HashMap<Player, Color> ColorSelection = new HashMap<Player, Color>();

    /**
     * Adds entry to the HashMap with Player as key
     * @param p Player
     * @param color Color
     */
    public static void setColor(Player p, Color color) { ColorSelection.put(p, color); }

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
    public static Color getColor(Player p) { return ColorSelection.get(p); }

    /**
     * Get complete list of Player -> Material relations
     * @return HashMap<Player, Material>
     */
    public static HashMap<Player, Color> getColorList() { return ColorSelection; }
}