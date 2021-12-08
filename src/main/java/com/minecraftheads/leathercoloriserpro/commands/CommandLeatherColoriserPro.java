package com.minecraftheads.leathercoloriserpro.commands;

import com.minecraftheads.leathercoloriserpro.LeatherColoriserPro;
import com.minecraftheads.leathercoloriserpro.utils.InventoryCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeatherColoriserPro implements CommandExecutor {

    private LeatherColoriserPro plugin;

    /**
     * Constructor
     *
     * @param LeatherColoriserPro (plugin)
     */
    public CommandLeatherColoriserPro(LeatherColoriserPro plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        InventoryCreator inv = new InventoryCreator();
        inv.initializeArmor();
        inv.openInventory(player);

        return true;
    }

}
