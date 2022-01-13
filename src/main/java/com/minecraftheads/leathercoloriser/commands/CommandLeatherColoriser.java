package com.minecraftheads.leathercoloriser.commands;

import com.minecraftheads.leathercoloriser.data.DyeColorMapping;
import com.minecraftheads.leathercoloriser.data.LanguageMapping;
import com.minecraftheads.leathercoloriser.handlers.SelectionHandler;
import com.minecraftheads.leathercoloriser.utils.InventoryCreatorBridge;
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
            Logger.info(LanguageMapping.ERROR_INVALID_COMMAND_SENDER.getString());
            return true;
        }

        Player player = (Player) sender;

        // check permissions
        if (!player.hasPermission("lc.main")) {
            player.sendMessage(LanguageMapping.ERROR_PERMISSION_MISSING.getStringWithPrefix());
            return true;
        }

        // Default / No arguments
        if (args.length == 0) {
            if (SelectionHandler.getColor(player) == null) {
                SelectionHandler.setColor(player, DyeColorMapping.DEFAULT.getColor());
            }
            new InventoryCreatorBridge(player);
        }
        // One Argument
        else if (args.length == 1) {
            // Check by regex if string matches HEX Color Code
            String regex = "^[#]?[0-9a-fA-F]{6}$";
            if (args[0].contains("#")) {
                args[0] = args[0].replace("#", "");
            }
            if (args[0].matches(regex)) {
                SelectionHandler.setColor(player, Color.fromRGB(Integer.parseInt(args[0], 16)));
                new InventoryCreatorBridge(player);
            }
            // Invalid Color
            else {
                player.sendMessage(LanguageMapping.ERROR_INVALID_COLOR.getStringWithPrefix());
            }
        }
        // More arguments - invalid
        else {
            player.sendMessage(LanguageMapping.ERROR_INVALID_COLOR.getStringWithPrefix());
        }
        return true;
    }
}
