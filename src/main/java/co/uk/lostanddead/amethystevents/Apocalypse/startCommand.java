package co.uk.lostanddead.amethystevents.Apocalypse;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.*;
import org.bukkit.boss.BossBar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
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

        if (!((Player) commandSender).isOp()){
            return true;
        }

        World world = ((Player) commandSender).getWorld();
        world.setTime(10000);
        world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        world.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        world.setStorm(false);
        world.setThundering(false);
        core.getConfig().set("apocalypse", true);
        core.saveConfig();
        core.setApocalypseEnabled(true);
        core.reloadConfig();

        new BukkitRunnable(){
            @Override
            public void run() {
                world.setTime(world.getTime() + 10);
                if (world.getTime() >= 15000){
                    this.cancel();
                }
            }
        }.runTaskTimer(core, 1, 1);

        sendLater(ChatColor.RED + "The clouds begins to merge...", 5);

        new BukkitRunnable(){
            @Override
            public void run() {
                world.setStorm(true);
                world.setThundering(true);
            }
        }.runTaskLater(core, 4*20);

        new BukkitRunnable(){
            int strikes = 0;
            @Override
            public void run() {

                Player p = Bukkit.getOnlinePlayers().stream().skip((int) (Bukkit.getOnlinePlayers().size() * Math.random())).findFirst().orElse(null);

                Random r = new Random();
                int x = r.nextInt((int) (p.getLocation().getX() + 100) - (int) (p.getLocation().getX() - 100)) + (int) (p.getLocation().getX() - 100);
                int z = r.nextInt((int) (p.getLocation().getZ() + 100) - (int) (p.getLocation().getZ() - 100)) + (int) (p.getLocation().getZ() - 100);
                int y = 30;

                Location loc = new Location(world, x ,y, z);

                new BukkitRunnable(){
                    @Override
                    public void run() {
                        world.strikeLightning(loc);
                    }
                }.runTaskLater(core, r.nextInt(100 - 10) + 10);

                strikes++;

                if (strikes >= 20){
                    this.cancel();
                }
            }
        }.runTaskTimer(core, 15*20, 40);

        sendLater(ChatColor.RED + "The sky begins to darken...", 20);

        new BukkitRunnable(){
            int count = 0;
            @Override
            public void run() {

                PotionEffect blind = new PotionEffect(PotionEffectType.BLINDNESS, 40, 1, true, true, false);

                for (Player p : Bukkit.getOnlinePlayers()){
                    p.addPotionEffect(blind);
                }
            }
        }.runTaskLater(core, 20*20 + 10);

        new BukkitRunnable(){
            @Override
            public void run() {
                BossBar bossBar = core.getEventBar();

                for (Player p : Bukkit.getOnlinePlayers()){
                    bossBar.addPlayer(p);
                }
            }
        }.runTaskLater(core, 20*20 + 5);

        sendLater(ChatColor.RED + "The monsters come out to play...", 30);

        return true;
    }

    private void sendLater(String msg, int seconds){
        new BukkitRunnable(){
            int count = 0;
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.sendTitle(msg, null, 20, 80, 20);
                }
            }
        }.runTaskLater(core, seconds*20);
    }


}
