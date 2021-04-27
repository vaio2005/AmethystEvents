package co.uk.lostanddead.amethystevents.Events;

//Grab ya imports

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.ChatColor;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class NicePhantoms implements Listener {

    public AmethystEvents core;

    public NicePhantoms(AmethystEvents core) {
        this.core = core;
    }

    public String getName() {
        return ChatColor.PURPLE + "Nice Phantoms";
    }
    
    public String getDescription() {
        return "Phantoms are nice now"
    }

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Phantom) {
            event.setDamage(0);
        }
    }    
}