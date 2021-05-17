package co.uk.lostanddead.amethystevents.Events;

//Grab ya imports

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

//Use this event as an example of all that it must have to work (edits must also be made to the main file to add a new event)

public class Cluck implements Listener {

    public AmethystEvents core;

    //All events are given the core when setup
    public Cluck(AmethystEvents core){
        this.core = core;
    }

    //All events must return a user friendly name
    public String getName(){
        return ChatColor.WHITE + "Cluck";
    }

    //All events must return a non-colour coded and user friendly description
    public String getDescription(){
        return "Cluck Cluck! Float Cluck!";
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        PotionEffect falling = new PotionEffect(PotionEffectType.SLOW_FALLING, 1200, 1, true, true, false);
        Player p = event.getPlayer();
        p.addPotionEffect(falling);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(core, new Runnable() {
            @Override
            public void run() {
                p.addPotionEffect(falling);
            }
        }, 1000, 1000);
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event){
        PotionEffect falling = new PotionEffect(PotionEffectType.SLOW_FALLING, 1200, 1, true, true, false);
        Player p = event.getPlayer();
        p.addPotionEffect(falling);
    }

    @EventHandler
    public void damage(EntityDamageEvent event){
        if (event.getEntity() instanceof Player){
            Player p = (Player) event.getEntity();
            p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_HURT, 1f, 1f);
        }
    }
}
