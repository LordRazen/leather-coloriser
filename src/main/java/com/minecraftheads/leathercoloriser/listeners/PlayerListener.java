package com.minecraftheads.leathercoloriser.listeners;

import com.minecraftheads.leathercoloriser.handlers.SelectionHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    /**
     * remove the selected color from storage when player leaves as this would be a possible memory leak
     * @param e PlayerQuitEvent
     */
    @EventHandler
    public static void onPlayerQuit(PlayerQuitEvent e) {
        SelectionHandler.removeColor(e.getPlayer());
    }
}
