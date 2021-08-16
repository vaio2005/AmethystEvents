package co.uk.lostanddead.amethystevents;

import org.bukkit.Bukkit;
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
        Bukkit.getLogger().info(args.toString());
        if (args.length >= 1){
            if (args[0].toLowerCase() == "reload" && sender.hasPermission("events.reload")){
                core.getBar().removeAll();
                Bukkit.getLogger().info("Reloading Events");
                sender.sendMessage(ChatColor.AQUA + "Reloading Events");
                core.reloadConfig();
                core.findEvent();
                return true;
            }
        }
        sender.sendMessage(net.md_5.bungee.api.ChatColor.of("#8d6acc") + "Current Event: " + core.getActiveEventName());
        sender.sendMessage("§7" + core.getActiveEventDescription());
        return true;
    }
}
