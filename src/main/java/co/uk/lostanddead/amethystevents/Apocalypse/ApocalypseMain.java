package co.uk.lostanddead.amethystevents.Apocalypse;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.util.Random;

public class ApocalypseMain implements Listener {

    private AmethystEvents core;
    private Random rand;
    private mobsSetup mobs;

    public ApocalypseMain(AmethystEvents core){
        this.core = core;
        mobs = new mobsSetup(core);
        rand = new Random();
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

    @EventHandler
    public void onSpawn(EntitySpawnEvent event){
        int randomInt = rand.nextInt(101);
        switch (event.getEntityType()){
            case ZOMBIE:
                Zombie zombie = (Zombie) event.getEntity();
                if (randomInt == 1){
                    mobs.editBossZombie(zombie);
                }else{
                    mobs.editZombie(zombie);
                }
                break;
            case SKELETON:
                Skeleton skeleton = (Skeleton) event.getEntity();
                if (randomInt == 1){
                    mobs.editBossSkeleton(skeleton);
                }else{
                    mobs.editSkeleton(skeleton);
                }
                break;
            case SPIDER:
                Spider spider = (Spider) event.getEntity();
                if (randomInt == 1){
                    mobs.editBossSpider(spider);
                }else{
                    mobs.editSpider(spider);
                }
                break;
            case HORSE:
                Horse horse = (Horse) event.getEntity();

                int randTame = rand.nextInt(10);

                if (randomInt % 2 == 0){
                    ZombieHorse zHorse = (ZombieHorse) horse.getWorld().spawnEntity(horse.getLocation(), EntityType.ZOMBIE_HORSE);
                    if (randTame == 1){
                        zHorse.setTamed(true);
                    }
                }else{
                    SkeletonHorse sHorse = (SkeletonHorse) horse.getWorld().spawnEntity(horse.getLocation(), EntityType.SKELETON_HORSE);
                    if (randTame == 1){
                        sHorse.setTamed(true);
                    }
                }

                event.setCancelled(true);
        }
    }
}
