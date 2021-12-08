package com.minecraftheads.leathercoloriserpro;

import com.minecraftheads.leathercoloriserpro.commands.CommandLeatherColoriserPro;
import com.minecraftheads.leathercoloriserpro.utils.LCPInventory;
import com.minecraftheads.leathercoloriserpro.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LeatherColoriserPro extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.info("LeatherColoriserPro starts...");
        // Plugin startup logic

        // Register Commands
        this.getCommand("LCP").setExecutor(new CommandLeatherColoriserPro(this));

        // Register Listener
        // this.getServer().getPluginManager().registerEvents(new LCPInventory(), this);
        Bukkit.getPluginManager().registerEvents(new LCPInventory(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
