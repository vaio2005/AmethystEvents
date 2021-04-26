package co.uk.lostanddead.amethystevents;

import co.uk.lostanddead.amethystevents.Events.KillerBunnies;
import co.uk.lostanddead.amethystevents.Events.NicePhantoms;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;

public final class AmethystEvents extends JavaPlugin {

    private String activeEventName;
    private String activeEventDescription;
    private BossBar title;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        String event = getConfig().getString("event");

        getCommand("event").setExecutor(new eventCommand(this));
        Bukkit.getPluginManager().registerEvents(new onJoin(this), this);

        //When adding an event make sure to add it to this switch with a config friendly name

        switch (event){
            //Chose a config friendly name
            case "Killer Bunnies": {
                //This code is triggered when the event is active, use it to set the name and description
                KillerBunnies eventClass = new KillerBunnies(this);
                activeEventName = eventClass.getName();
                activeEventDescription = eventClass.getDescription();
                Bukkit.getPluginManager().registerEvents(eventClass, this);
            }

            case "Nice Phantoms": {
                NicePhantoms eventClass = new NicePhantoms(this);
                activeEventName = eventClass.getName();
                activeEventDescription = eventClass.getDescription();
                Bukkit.getPluginManager().registerEvents(eventClass, this);
            }
        }

        Bukkit.getLogger().info("Loaded Amethyst Events");
        if(activeEventName == null){
            Bukkit.getLogger().info("No event loaded");
            activeEventName = "No event loaded";
            activeEventDescription = "No event loaded";
        }else{
            Bukkit.getLogger().info("Current event is " + activeEventName);
        }

        title = Bukkit.createBossBar(
                net.md_5.bungee.api.ChatColor.of("#8d6acc") + "Current Event: " + activeEventName,
                BarColor.WHITE,
                BarStyle.SOLID
        );
        title.setVisible(true);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public String getActiveEventName(){
        return activeEventName;
    }

    public String getActiveEventDescription(){
        return activeEventDescription;
    }

    public BossBar getBar() {
        return title;
    }
}
