package com.minecraftheads.leathercoloriserpro;

import com.minecraftheads.leathercoloriserpro.commands.CommandLeatherColoriserPro;
import com.minecraftheads.leathercoloriserpro.listeners.InventoryListener;
import com.minecraftheads.leathercoloriserpro.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;

public final class LeatherColoriserPro extends JavaPlugin {

    /**
     * constructors for unittests
     */
    public LeatherColoriserPro() {
        super();
    }
    protected LeatherColoriserPro(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        Logger.info("LeatherColoriserPro starts...");
        checkConfig();
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
