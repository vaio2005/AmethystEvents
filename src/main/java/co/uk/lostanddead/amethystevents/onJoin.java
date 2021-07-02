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
        if (core.getBar() != null){
            core.getBar().addPlayer(event.getPlayer());
            return;
        }
        if (core.getEventBar() != null && core.isApocalypseEnabled()){
            core.getEventBar().addPlayer(event.getPlayer());
            return;
        }

    }
}
