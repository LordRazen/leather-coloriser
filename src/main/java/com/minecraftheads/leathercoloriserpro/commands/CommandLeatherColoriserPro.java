package com.minecraftheads.leathercoloriserpro.commands;

import com.minecraftheads.leathercoloriserpro.LeatherColoriserPro;
import com.minecraftheads.leathercoloriserpro.utils.LCPInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandLeatherColoriserPro implements CommandExecutor {

    private LeatherColoriserPro plugin;

    /**
     * Constructor
     *
     * @param
     */
    public CommandLeatherColoriserPro(LeatherColoriserPro plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        player.sendRawMessage("HI!");

        LCPInventory inv = new LCPInventory();
        inv.openInventory(player);

        return true;
    }
}
