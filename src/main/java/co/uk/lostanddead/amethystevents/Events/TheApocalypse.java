package co.uk.lostanddead.amethystevents.Events;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class TheApocalypse implements Listener {

    public AmethystEvents core;

    public TheApocalypse(AmethystEvents core) {
        this.core = core;
    }

    public String getName() {
        return ChatColor.DARK_RED + "The Apocalypse";
    }

    public String getDescription() {
        return ChatColor.GRAY + "The end is near. The last Steves must survive";
    }

    @EventHandler(ignoreCancelled = true)
    public void zombieHorses(EntitySpawnEvent event) {
        if (event.getEntity() instanceof Horse) {
            event.setCancelled(true);
            Location loc = event.getEntity().getLocation();
            int horse_type = ThreadLocalRandom.current().nextInt(0,2);
            int has_rider = ThreadLocalRandom.current().nextInt(0, 100);


            if (horse_type == 0) {
                Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.ZOMBIE_HORSE);
                ZombieHorse horse = (ZombieHorse) event.getEntity();

                if (has_rider < 5) {
                    horse.addPassenger(rider);
                }
            }

            else {
                Objects.requireNonNull(loc.getWorld()).spawnEntity(loc, EntityType.SKELETON_HORSE);
                SkeletonHorse horse = (SkeletonHorse) event.getEntity();

                if (has_rider < 5) {
                    horse.addPassenger(rider);
                }
            }
        }
    }

    @EventHandler
    public void bossZombie(EntitySpawnEvent event) {
        ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);
        ItemStack chestp = new ItemStack(Material.LEATHER_CHESTPLATE);
        ItemStack leg = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemStack boot = new ItemStack(Material.GOLDEN_BOOTS);
        ItemStack sword = new ItemStack(Material.NETHERITE_SWORD);

        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2);
        chestp.addEnchantment(Enchantment.THORNS, 1);
        leg.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        boot.addEnchantment(Enchantment.DEPTH_STRIDER, 3);
        boot.addEnchantment(Enchantment.SOUL_SPEED, 3);
        sword.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        sword.addEnchantment(Enchantment.KNOCKBACK, 1);

        if (event.getEntity() instanceof Zombie) {
            int chance = ThreadLocalRandom.current().nextInt(0, 100);
            Zombie boss = (Zombie) event.getEntity();
            if (chance < 3) {
                boss.setInvisible(true);
            }
        }

        if (event.getEntity() instanceof Spider) {
            int chance = ThreadLocalRandom.current().nextInt(0,10);
            Spider spider = (Spider) event.getEntity();
            if (chance == 5) {
                spider.setInvisible(true);
                spider.setGlowing(true);
                spider.setHealth(24.0);
            }
        }
    }
}
