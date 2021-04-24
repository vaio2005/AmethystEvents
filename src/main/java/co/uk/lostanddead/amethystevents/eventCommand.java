package co.uk.lostanddead.amethystevents;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class eventCommand implements CommandExecutor {

    public AmethystEvents core;

    public eventCommand(AmethystEvents core){
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(net.md_5.bungee.api.ChatColor.of("#8d6acc") + "Current Event: " + core.getActiveEventName());
        sender.sendMessage("§7" + core.getActiveEventDescription());
        return true;
    }
}
