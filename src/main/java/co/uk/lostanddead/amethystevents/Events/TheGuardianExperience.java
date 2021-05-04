package co.uk.lostanddead.amethystevents.Events;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import java.util.concurrent.ThreadLocalRandom;

import static org.bukkit.Bukkit.getServer;


public class TheGuardianExperience implements Listener {

    public AmethystEvents core;

    public TheGuardianExperience(AmethystEvents core) {
        this.core = core;
    }

    public String getName() {
        return ChatColor.GREEN + "The Guardian Experience";
    }

    public String getDescription() {
        return "The Guardians are just misunderstood";
    }

    ConsoleCommandSender console = getServer().getConsoleSender();

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Guardian && event.getEntity() instanceof Player) {
            int XP = ThreadLocalRandom.current().nextInt(0,4);
            Player p = (Player) event.getEntity();
            p.giveExp(XP);
        }
    }

    @EventHandler
    public void mobDeath(EntityDeathEvent death) {
        if (death.getEntity() instanceof Guardian) {
            int drop = ThreadLocalRandom.current().nextInt(0,100);
            if (drop >= 95) {
                death.getDrops().add(new ItemStack(Material.HEART_OF_THE_SEA, 1));
            }
            if (drop < 50 && drop > 60) {
                death.getDrops().add(new ItemStack(Material.SEA_LANTERN, 2));
            }
            if (drop == 69) {
                death.getDrops().add(new ItemStack(Material.TRIDENT, 1));
            }
            console.sendMessage(String.valueOf(drop));
        }
    }
}
