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
    private double amount;

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
            amount = p.getAbsorptionAmount();
            if (amount <8) {
                p.setAbsorptionAmount(amount + 1);
            }
            scheduler.runTaskLater(core, new Runnable() {
                @Override
                public void run() {
                    amount = p.getAbsorptionAmount();
                    if (amount !=0) {
                        p.setAbsorptionAmount(amount - 1);
                    }
                }
            }, 1200);

        }
    }    
}