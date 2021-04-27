package co.uk.lostanddead.amethystevents.Events;

//Grab ya imports

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;


public class NicePhantoms implements Listener {

    public AmethystEvents core;

    public NicePhantoms(AmethystEvents core) {
        this.core = core;
    }

    public String getName() {
        return ChatColor.DARK_PURPLE + "Nice Phantoms";
    }
    
    public String getDescription() { return "Phantoms are nice now"; }

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Phantom && event.getEntity() instanceof Player) {
            BukkitScheduler scheduler = Bukkit.getServer().getScheduler();

            event.setDamage(0);
            Player p = (Player) event.getEntity();
            p.setAbsorptionAmount(2);

            scheduler.scheduleSyncDelayedTask((Plugin) this, new Runnable() {
                @Override
                public void run() {
                    p.setAbsorptionAmount(0);
                }
            }, 100);
        }
    }    
}