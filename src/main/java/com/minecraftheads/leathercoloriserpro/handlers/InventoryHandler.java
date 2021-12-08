package com.minecraftheads.leathercoloriserpro.handlers;

import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * Basic storage of a inventoryList so we can check if the inv is one of our LCP invs
 */
public class InventoryHandler {
    private final static List<Inventory> InventoryList = new ArrayList<>();

    public static void addInventory(Inventory i) {InventoryList.add(i);}
    public static void removeInventory(Inventory i) {InventoryList.remove(i);}
    public static List<Inventory> getInventory() {return InventoryList;}
}