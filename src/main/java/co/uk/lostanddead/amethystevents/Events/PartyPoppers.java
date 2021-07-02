package co.uk.lostanddead.amethystevents.Events;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Objects;

public class PartyPoppers implements Listener {

    public AmethystEvents core;

    public PartyPoppers(AmethystEvents core) {
        this.core = core;
    }

    public String getName() {
        return ChatColor.GREEN + "Party Poppers!";
    }

    public String getDescription() {
        return "Creepers are friendly clouds of confetti!";
    }

    @EventHandler
    public void explode(EntityExplodeEvent event){
        if(event.getEntity() instanceof Creeper){

            Creeper creeper = (Creeper) event.getEntity();
            Location loc = creeper.getLocation();

            event.setCancelled(true);
            creeper.remove();

            Firework f1 = (Firework) Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.FIREWORK);
            FireworkMeta f1Meta = f1.getFireworkMeta();

            f1Meta.setPower(2);
            //f1Meta.addEffect();
        }
    }
}