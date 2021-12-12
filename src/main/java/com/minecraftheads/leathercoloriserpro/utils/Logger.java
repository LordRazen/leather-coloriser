package com.minecraftheads.leathercoloriserpro.utils;

import org.bukkit.Bukkit;

public class Logger {
    /**
     * Logger Method
     *
     * @param msg String
     */
    public static void info(String msg) {
        Bukkit.getLogger().info("[LCP] " + msg);
    }
}
