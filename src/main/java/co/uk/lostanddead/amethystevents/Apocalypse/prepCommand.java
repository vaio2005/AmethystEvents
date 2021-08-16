package co.uk.lostanddead.amethystevents.Apocalypse;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

public class prepCommand implements CommandExecutor {

    public AmethystEvents core;

    public prepCommand(AmethystEvents core){
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof ConsoleCommandSender) && commandSender.hasPermission("events.reload")) {

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1f, 1f);
                p.sendMessage("于" + ChatColor.GRAY + "Please make sure you have the following setting setup for the best experience");
                p.sendMessage("");
                p.sendMessage("于" + ChatColor.AQUA + "Do not use any custom clients such as Lunar or Badlion");
                p.sendMessage("");
                p.sendMessage("于" + ChatColor.AQUA + "Please ensure you have your volume turned on (You should have heard a ping)");
                p.sendMessage("");
                p.sendMessage("于" + ChatColor.AQUA + "Please make sure you dont have any full bright of blindness removing mods");
            }
        }
        return true;
    }
}
