package net.threader.resistantblock.command;

import net.threader.resistantblock.ResistantBlocks;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ResistantBlockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.hasPermission("resistantblocks.admin")) {
            sender.sendMessage("No permission");
            return true;
        }
        if(args[0].equalsIgnoreCase("reload")) {
            ResistantBlocks.instance().reloadConfig();
            return true;
        }
        return false;
    }
}
