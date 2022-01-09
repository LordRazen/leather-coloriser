package com.minecraftheads.leathercoloriser.commands;

import com.minecraftheads.leathercoloriser.handlers.LanguageHandler;
import com.minecraftheads.leathercoloriser.utils.InventoryCreator;
import com.minecraftheads.pluginUtils.utils.Logger;
import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeatherColoriser implements CommandExecutor {

    /**
     * Initiates LCP when /lc is entered
     *
     * @param sender  CommandSender
     * @param command Command
     * @param label   String
     * @param args    String
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Abort if Sender is no player
        if (!(sender instanceof Player)) {
            Logger.info(LanguageHandler.getMessage("error_invalid_command_sender"));
            return true;
        }

        Player player = (Player) sender;

        // check permissions
        if (!player.hasPermission("lc.main")) {
            player.sendMessage(LanguageHandler.getMessage("error_permission_missing"));
            return true;
        }

        // Default / No arguments
        if (args.length == 0) {
            new InventoryCreator().openInventory(player);
        }
        // One Argument
        else if (args.length == 1) {
            // Check by regex if string matches HEX Color Code
            String regex = "^[#]?[0-9a-fA-F]{6}$";
            if (args[0].contains("#")) {
                args[0] = args[0].replace("#", "");
            }
            if (args[0].matches(regex)) {
                new InventoryCreator(Color.fromRGB(Integer.parseInt(args[0], 16))).openInventory(player);
            }
            // Invalid Color
            else {
                player.sendMessage(LanguageHandler.getMessage("error_invalid_color"));
            }
        }
        // More arguments - invalid
        else {
            player.sendMessage(LanguageHandler.getMessage("error_invalid_color"));
        }
        return true;
    }
}
