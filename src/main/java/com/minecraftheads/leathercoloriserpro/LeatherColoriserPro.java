package com.minecraftheads.leathercoloriserpro;

import com.minecraftheads.leathercoloriserpro.commands.CommandLeatherColoriserPro;
import com.minecraftheads.leathercoloriserpro.listeners.InventoryListener;
import com.minecraftheads.leathercoloriserpro.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LeatherColoriserPro extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.info("LeatherColoriserPro starts...");
        // Plugin startup logic

        // Register Commands
        this.getCommand("LCP").setExecutor(new CommandLeatherColoriserPro());

        // Register Listener
        // this.getServer().getPluginManager().registerEvents(new LCPInventory(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
