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
            if (args.length == 0) {

                InventoryCreator inv = new InventoryCreator();
                inv.initializeItems();
                inv.openInventory(player);
            // check if user provided a colorString
            } else if (args.length == 1) {
                // check by regex if string matches HEX code
                String regex = "[0-9a-fA-F]{6}$";
                if (args[0].matches(regex)) {
                    InventoryCreator inv = new InventoryCreator();
                    inv.initializeItems(args[0]);
                    inv.openInventory(player);
                }
            } else {
                return false;
            }

        } else {
            Logger.info("/lcp can only be used by players");
        }
        return true;
    }

}
