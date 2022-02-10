package com.minecraftheads.leathercolorizer;

import com.minecraftheads.leathercolorizer.commands.CommandLeatherColorizer;
import com.minecraftheads.leathercolorizer.listeners.InventoryListener;
import com.minecraftheads.leathercolorizer.listeners.PlayerListener;
import com.minecraftheads.pluginUtils.config.ConfigUpdater;
import com.minecraftheads.pluginUtils.config.LanguageHandler;
import com.minecraftheads.pluginUtils.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public final class LeatherColorizer extends JavaPlugin {

    private static String[] LANGUAGES = {"en", "de", "it", "ru", "tr"};

    @Override
    public void onEnable() {
        Logger.setPrefix("[LC]");
        Logger.info("Leather Colorizer v" + getDescription().getVersion() + " loaded");
        LanguageHandler.setPlugin(this);
        checkConfig();

        // Register Commands
        this.getCommand("LC").setExecutor(new CommandLeatherColorizer());

        // Register Listener
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

        // Ensure Language Dir exists
        File languageDir = new File(this.getDataFolder(), "languages/");
        if (!languageDir.exists()) languageDir.mkdir();

        try {
            // Config
            File configFile = new File(getDataFolder(), "config.yml");
            ConfigUpdater.update(this, "config.yml", configFile, Collections.emptyList());

            // Language Files
            for (String language : LANGUAGES) {
                String languageFile = "languages/" + language + ".yml";

                File file = new File(getDataFolder(), languageFile);
                if (file.exists()) continue;

                // Ensure language files exist
                this.saveResource(languageFile, false);

                // Update
                configFile = new File(getDataFolder(), languageFile);
                ConfigUpdater.update(this, languageFile, configFile, Collections.emptyList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        reloadConfig();
    }
}
