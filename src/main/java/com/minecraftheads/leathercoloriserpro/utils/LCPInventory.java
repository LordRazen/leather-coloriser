package com.minecraftheads.leathercoloriserpro.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class LCPInventory implements Listener {
    private final Inventory inv;

    /**
     * Constructor
     */
    public LCPInventory() {
        inv = Bukkit.createInventory(null, 54, "Leather Coloriser Pro");

        initializeItems();
    }

    /**
     *
     */
    public void initializeItems() {
        inv.setItem(3, createGuiItem(Material.DIAMOND_SWORD,
                "Example Sword",
                "§aFirst line of the lore", "§bSecond line of the lore"));

    }

    /**
     * Create ItemStack
     *
     * @param material
     * @param name
     * @param lore
     * @return
     */
    public ItemStack createGuiItem(final Material material,
                                    final String name,
                                    final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }

    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }




    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getView().getTitle().equals("Leather Coloriser Pro")) {
            return;
        }

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType().isAir()) {
            return;
        }

        final Player p = (Player) e.getWhoClicked();

        // Using slots click is a best option for your inventory click's
        p.sendMessage("You clicked at slot " + e.getRawSlot());
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().equals(inv)) {
            Logger.info("DRAG");
            e.setCancelled(true);
        }
    }


}
