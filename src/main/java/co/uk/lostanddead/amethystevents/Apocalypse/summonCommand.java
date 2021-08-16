package co.uk.lostanddead.amethystevents.Apocalypse;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

import java.util.Locale;

public class summonCommand implements CommandExecutor {

    private AmethystEvents core;
    mobsSetup mobs;

    public summonCommand (AmethystEvents core){
        this.core = core;
        mobs = new mobsSetup(core);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)){
            return true;
        }

        if (commandSender.hasPermission("events.reload")) {
            if (strings.length <= 0) {
                return true;
            }

            Player p = (Player) commandSender;
            World world = p.getWorld();
            Location loc = p.getLocation();

            switch (strings[0].toLowerCase()) {
                case "zombie":
                    Zombie zombie = (Zombie) world.spawnEntity(loc, EntityType.ZOMBIE);
                    mobs.editBossZombie(zombie);
                    break;
                case "skeleton":
                    Skeleton skeleton = (Skeleton) world.spawnEntity(loc, EntityType.SKELETON);
                    mobs.editBossSkeleton(skeleton);
                    break;
                case "spider":
                    Spider spider = (Spider) world.spawnEntity(loc, EntityType.SPIDER);
                    mobs.editBossSpider(spider);
                    break;
                case "creeper":
                    Creeper creeper = (Creeper) world.spawnEntity(loc, EntityType.CREEPER);
                    mobs.editBossCreeper(creeper);
                    break;
            }
        }
        return true;
    }
}
