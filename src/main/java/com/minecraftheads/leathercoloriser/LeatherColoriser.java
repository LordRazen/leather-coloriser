package com.minecraftheads.leathercoloriser;

import com.minecraftheads.leathercoloriser.commands.CommandLeatherColoriser;
import com.minecraftheads.leathercoloriser.listeners.InventoryListener;
import com.minecraftheads.leathercoloriser.listeners.PlayerListener;
import com.minecraftheads.pluginUtils.config.ConfigUpdater;
import com.minecraftheads.pluginUtils.config.LanguageHandler;
import com.minecraftheads.pluginUtils.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public final class LeatherColoriser extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.setPrefix("[LC]");
        Logger.info("Leather Coloriser loaded");
        LanguageHandler.setPlugin(this);
        checkConfig();
        // Plugin startup logic

        // Register Commands
        this.getCommand("LC").setExecutor(new CommandLeatherColoriser());

        // Register Listener
        // this.getServer().getPluginManager().registerEvents(new LCPInventory(), this);
        Bukkit.getPluginManager().registerEvents(new com.minecraftheads.pluginUtils.inventory.InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void checkConfig() {
        //The config needs to exist before using the updater
        saveDefaultConfig();
        File langDir = new File(this.getDataFolder(), "languages/");
        if (!langDir.exists()) {
            langDir.mkdir();
        }
        this.saveResource("languages/en.yml", false);
        this.saveResource("languages/de.yml", false);

        // try to run the updater and then reload the files
        try {
            File configFile = new File(getDataFolder(), "config.yml");
            ConfigUpdater.update(this, "config.yml", configFile, Collections.emptyList());
            configFile = new File(getDataFolder(), "languages/en.yml");
            ConfigUpdater.update(this, "languages/en.yml", configFile, Collections.emptyList());
            configFile = new File(getDataFolder(), "languages/de.yml");
            ConfigUpdater.update(this, "languages/de.yml", configFile, Collections.emptyList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        reloadConfig();

    }

}
