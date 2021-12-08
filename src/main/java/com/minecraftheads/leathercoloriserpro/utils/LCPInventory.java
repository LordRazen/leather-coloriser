package com.minecraftheads.leathercoloriserpro.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class LCPInventory implements Listener {
    private static final String name = "Leather Coloriser Pro (/lcp)";
    private static final int size = 54;
    private final Inventory inv;

    /**
     * Constructor
     */
    public LCPInventory() {
        inv = Bukkit.createInventory(null, size, name);
        initializeItems();
    }

    /**
     *
     */
    public void initializeItems() {
        inv.setItem(0,
                createGuiCustomHeadItem(Material.PLAYER_HEAD,
                        "Color",
                        "§aFirst line of the lore",
                        "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY5ZTNlNmU1YjJiOTJmMGJlYjM2OGI3MzhiOTkzZDdiYTIyNWJmOWJiMjc1OGJmYzlmYzJkYWJhNGE1YTdkIn19fQ=="));

        inv.setItem(1,
                createGuiPlayerHeadItem(Material.PLAYER_HEAD,
                        "Color",
                        "§aFirst line of the lore",
                        "LordRazen=="));


        inv.setItem(9,
                createGuiItem(Material.LEATHER_HORSE_ARMOR,
                        "Leather Horse Armor",
                        "§aFirst line of the lore"));
        inv.setItem(10,
                createGuiItem(Material.LEATHER_HELMET,
                        "Leather Helmet",
                        "§aFirst line of the lore"));
        inv.setItem(11,
                createGuiItem(Material.LEATHER_CHESTPLATE,
                        "Leather Chestplate",
                        "§aFirst line of the lore"));
        inv.setItem(12,
                createGuiItem(Material.LEATHER_LEGGINGS,
                        "Leather Leggings",
                        "§aFirst line of the lore"));
        inv.setItem(13,
                createGuiItem(Material.LEATHER_BOOTS,
                        "Leather Boots",
                        "§aFirst line of the lore"));

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

    /**
     *
     * @param material
     * @param name
     * @param lore
     * @param playerName
     * @return
     */
    public ItemStack createGuiPlayerHeadItem(final Material material,
                                       final String name,
                                       final String lore,
                                       final String playerName) {
        // Get Basic Item
        final ItemStack item = createGuiItem(material, name, lore);

        // Set Player Head Data
        final SkullMeta meta = (SkullMeta) item.getItemMeta();
        final OfflinePlayer player = Bukkit.getPlayer(playerName);
        meta.setOwningPlayer(player);
        item.setItemMeta(meta);
        return item;
    }



    /**
     *
     * @param material
     * @param name
     * @param lore
     * @param value
     * @return
     */
    public ItemStack createGuiCustomHeadItem(final Material material,
                                             final String name,
                                             final String lore,
                                             final String value) {
        // Get Basic Item
        ItemStack item = createGuiItem(material, name, lore);

        // Set Custom Head Data
        SkullMeta meta = (SkullMeta) item.getItemMeta();





        item.setItemMeta(meta);
        return item;
    }




    public void openInventory(final HumanEntity ent) {
        ent.openInventory(inv);
    }




    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        // Abort if the Inventory has the wrong name
        if (!e.getView().getTitle().equals(name)) return;

        // Abort only if the InventoryClick is within the created Inventory
        if(e.getRawSlot() > size) return;

        e.setCancelled(true);


        // verify current item is not null
        final ItemStack clickedItem = e.getCurrentItem();
        if (clickedItem == null || clickedItem.getType().isAir()) return;



        // Using slots click is a best option for your inventory click's
        final Player p = (Player) e.getWhoClicked();
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

    // TODO: ITEMS CAN BE PLACED INSIDE THE GUI AND ARE GONE IF YOU CLOSE IT!
}
