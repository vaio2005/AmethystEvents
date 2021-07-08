package co.uk.lostanddead.amethystevents.Apocalypse;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class ApocalypseMain implements Listener {

    private AmethystEvents core;

    public ApocalypseMain(AmethystEvents core){
        this.core = core;
    }

    @EventHandler
    public void onSleep(PlayerBedEnterEvent event){
        event.setUseBed(Event.Result.DENY);
        Player p = event.getPlayer();
        p.sendMessage(ChatColor.RED + "You think a mere mortal like yourself can end this? I laugh at you!");
        p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
        Block bed = event.getBed();
        bed.breakNaturally();
        p.spawnParticle(Particle.EXPLOSION_LARGE, bed.getLocation().getX(),  bed.getLocation().getY(),  bed.getLocation().getZ(), 50, 2, 2, 2);
    }
}
