package co.uk.lostanddead.amethystevents.Apocalypse;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class startCommand implements CommandExecutor {

    public AmethystEvents core;

    public startCommand(AmethystEvents core){
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof ConsoleCommandSender){ return true; }
        if (commandSender.hasPermission("events.reload")) {

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.playSound(p.getLocation(), "minecraft:event", 0.75F, 1);
            }

            World world = ((Player) commandSender).getWorld();
            world.setTime(10000);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
            world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
            world.setStorm(false);
            world.setThundering(false);

            new BukkitRunnable() {
                @Override
                public void run() {
                    world.setTime(world.getTime() + 7);
                    if (world.getTime() >= 15000) {
                        this.cancel();
                    }
                }
            }.runTaskTimer(core, 1, 1);

            sendLater(ChatColor.RED + "The clouds begins to merge...", 16);

            new BukkitRunnable() {
                @Override
                public void run() {
                    world.setStorm(true);
                }
            }.runTaskLater(core, 16 * 20);

            sendLater(ChatColor.RED + "The Sky Begins To Darken...", 32);

            PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS, (32 * 20), 0, true, true, false);

            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.addPotionEffect(blind);
                        p.spawnParticle(Particle.MOB_APPEARANCE, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), 1);
                        p.playSound(p.getLocation(), Sound.ENTITY_WITHER_HURT, 2, 1);
                    }
                }
            }.runTaskLater(core, (33 * 20));

            strikeLightening(core, (32 * 20) + 10);
            strikeLightening(core, (39 * 20) + 10);
            strikeLightening(core, (44 * 20) + 10);
            strikeLightening(core, (47 * 20) + 10);

            sendLater(ChatColor.RED + "The Monsters Come Out To Play...", 48);

            for (int i = 0; i <= 32; i++) {
                spawnMob(core, (48 * 20) + (i * 5));
            }

            sendLater("한" + ChatColor.RED + "Let The Apocalypse Begin! " + ChatColor.RESET + "한", 64);

            for (int i = 0; i <= 30; i++) {
                strikeLightening(core, (62 * 20) + (i * 5));
            }

            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        core.getEventBar().addPlayer(p);
                    }
                }
            }.runTaskLater(core, (64 * 20) - 5);

            new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1f, 1);
                        core.getConfig().set("apocalypse", true);
                        core.saveConfig();
                        core.setApocalypseEnabled(true);
                        core.reloadConfig();
                        core.findEvent();
                    }
                }
            }.runTaskLater(core, (64 * 20) + 10);
        }
        return true;
    }

    private void sendLater(String msg, int seconds){
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.sendTitle(msg, null, 20, 80, 20);
                }
            }
        }.runTaskLater(core, seconds*20);
    }

    private static void strikeLightening(AmethystEvents core, int delay){

        new BukkitRunnable(){
            Random r = new Random();
            @Override
            public void run() {
                Player p = Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random())).findFirst().orElse(null);
                World world = p.getWorld();

                int x = r.nextInt((int) (p.getLocation().getX() + 100) - (int) (p.getLocation().getX() - 100)) + (int) (p.getLocation().getX() - 100);
                int z = r.nextInt((int) (p.getLocation().getZ() + 100) - (int) (p.getLocation().getZ() - 100)) + (int) (p.getLocation().getZ() - 100);
                int y = world.getHighestBlockAt(x, z).getY();

                Location loc = new Location(world, x ,y, z);

                world.strikeLightning(loc);
            }
        }.runTaskLater(core, delay);
    }

    private static void spawnMob(AmethystEvents core, int delay){

        new BukkitRunnable(){
            Random r = new Random();
            mobsSetup mobs = new mobsSetup(core);
            @Override
            public void run(){

                Player p = Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random())).findFirst().orElse(null);
                World world = p.getWorld();

                int x = r.nextInt((int) (p.getLocation().getX() + 100) - (int) (p.getLocation().getX() - 100)) + (int) (p.getLocation().getX() - 100);
                int z = r.nextInt((int) (p.getLocation().getZ() + 100) - (int) (p.getLocation().getZ() - 100)) + (int) (p.getLocation().getZ() - 100);
                int y = world.getHighestBlockAt(x, z).getY() + 1;

                Location loc = new Location(world, x ,y, z);

                int typeInt = r.nextInt(4);

                switch (typeInt){
                    case 0:
                        Zombie zombie = (Zombie) world.spawnEntity(loc, EntityType.ZOMBIE);
                        mobs.editBossZombie(zombie);
                        break;
                    case 1:
                        Skeleton skeleton = (Skeleton) world.spawnEntity(loc, EntityType.SKELETON);
                        mobs.editBossSkeleton(skeleton);
                        break;
                    case 2:
                        Spider spider = (Spider) world.spawnEntity(loc, EntityType.SPIDER);
                        mobs.editBossSpider(spider);
                        break;
                    case 3:
                        Creeper creeper = (Creeper) world.spawnEntity(loc, EntityType.CREEPER);
                        mobs.editBossCreeper(creeper);
                        break;
                }

                int strikeInt = r.nextInt(2);
                if (strikeInt == 1){
                    world.strikeLightning(loc);
                }
            }
        }.runTaskLater(core, delay);
    }
}
