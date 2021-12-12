package com.minecraftheads.leathercoloriserpro.commands;

import com.minecraftheads.leathercoloriserpro.LeatherColoriserPro;
import com.minecraftheads.leathercoloriserpro.utils.InventoryCreator;
import com.minecraftheads.leathercoloriserpro.utils.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeatherColoriserPro implements CommandExecutor {

    /**
     * Initiates LCP when /lcp is entered
     *
     * @param sender CommandSender
     * @param command Command
     * @param label String
     * @param args String
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            InventoryCreator inv = new InventoryCreator();
            inv.initializeArmor();
            inv.openInventory(player);
        } else {
            Logger.info("/lcp can only be used by players");
        }


        return true;
    }

}
