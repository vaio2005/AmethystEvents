package co.uk.lostanddead.amethystevents.Events;

//Grab ya imports

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.ChatColor;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

//Use this event as an example of all that it must have to work (edits must also be made to the main file to add a new event)

public class KillerBunnies implements Listener {

    public AmethystEvents core;

    //All events are given the core when setup
    public KillerBunnies(AmethystEvents core){
        this.core = core;
    }

    //All events must return a user friendly name
    public String getName(){
        return ChatColor.RED + "Killer Bunnies";
    }

    //All events must return a non-colour coded and user friendly description
    public String getDescription(){
        return "All bunnies are out to get you!";
    }

    //Bellow you can add all the event listeners your event might need to do whatever it does
    @EventHandler
    public void onSpawn(EntitySpawnEvent event){
        if(event.getEntity() instanceof Rabbit){
            Rabbit bunny = (Rabbit) event.getEntity();
            bunny.setRabbitType(Rabbit.Type.THE_KILLER_BUNNY);
        }
    }
}
