package com.minecraftheads.leathercoloriser;

import com.minecraftheads.leathercoloriser.commands.CommandLeatherColoriser;
import com.minecraftheads.leathercoloriser.listeners.InventoryListener;
import com.minecraftheads.leathercoloriser.listeners.PlayerListener;
import com.minecraftheads.pluginUtils.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class LeatherColoriser extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.setPrefix("[LC]");
        Logger.info("LeatherColoriser starts...");
        checkConfig();
        // Plugin startup logic

        // Register Commands
        this.getCommand("LC").setExecutor(new CommandLeatherColoriser());

        // Register Listener
        // this.getServer().getPluginManager().registerEvents(new LCPInventory(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void checkConfig() {
        if (this.getConfig().getDouble("version", 0.0) == 1.0) {
            saveConfig();
        } else {
            saveDefaultConfig();
            reloadConfig();
        }
        File langDir = new File(this.getDataFolder(), "languages/");
        if (!langDir.exists()) {
            langDir.mkdir();
        }
        this.saveResource("languages/en.yml", false);
        this.saveResource("languages/de.yml", false);
    }

}
