package co.uk.lostanddead.amethystevents;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    public AmethystEvents core;

    public onJoin(AmethystEvents core){
        this.core = core;
    }

    @EventHandler
    public void join(PlayerJoinEvent event){
        //event.getPlayer().setPlayerListHeader("\n          " + net.md_5.bungee.api.ChatColor.of("#8d6acc") + "Current Event: " + core.getActiveEventName() + "          \n");
        core.getBar().addPlayer(event.getPlayer());
    }
}
