package com.minecraftheads.leathercoloriser.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LanguageHandler {
    /**
     * Fetches a string from the configured lang file
     *
     * @param section String
     * @return String
     */
    public static String getMessage(String section) {
        // Get language from Config
        String lang = Bukkit.getPluginManager().getPlugin("LeatherColoriser").getConfig().getString("language");

        // Read corresponding language file
        YamlConfiguration langFile = YamlConfiguration.loadConfiguration(
                new File(Bukkit.getPluginManager().getPlugin("LeatherColoriser").getDataFolder(), "languages/" + lang + ".yml"));

        // Return the correct string
        return "§r" + langFile.getString(section);
    }
}
