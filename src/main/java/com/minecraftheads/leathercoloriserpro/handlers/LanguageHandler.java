package com.minecraftheads.leathercoloriserpro.handlers;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;

public class LanguageHandler {
    /**
     * fetches a string from the configured lang file
     * @param section String
     * @return String
     */
    public static String getMessage(String section) {
        String lang = Bukkit.getPluginManager().getPlugin("LeatherColoriserPro").getConfig().getString("language");
        YamlConfiguration langFile = YamlConfiguration.loadConfiguration(
                new File(Bukkit.getPluginManager().getPlugin("LeatherColoriserPro").getDataFolder(), "languages/" + lang + ".yml"));

        return langFile.getString(section);
    }
}
