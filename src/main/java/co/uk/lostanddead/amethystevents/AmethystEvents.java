package co.uk.lostanddead.amethystevents;

import co.uk.lostanddead.amethystevents.Apocalypse.ApocalypseMain;
import co.uk.lostanddead.amethystevents.Apocalypse.prepCommand;
import co.uk.lostanddead.amethystevents.Apocalypse.startCommand;
import co.uk.lostanddead.amethystevents.Apocalypse.summonCommand;
import co.uk.lostanddead.amethystevents.Events.Cluck;
import co.uk.lostanddead.amethystevents.Events.KillerBunnies;
import co.uk.lostanddead.amethystevents.Events.NicePhantoms;
import co.uk.lostanddead.amethystevents.Events.TheGuardianExperience;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class AmethystEvents extends JavaPlugin {

    private String activeEventName;
    private String activeEventDescription;
    private BossBar title;
    private BossBar eventBar;

    private boolean apocalypseEnabled = false;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        apocalypseEnabled = this.getConfig().getBoolean("apocalypse");

        this.getCommand("event").setExecutor(new eventCommand(this));
        this.getCommand("beginapocalypse").setExecutor(new startCommand(this));
        this.getCommand("summonboss").setExecutor(new summonCommand(this));
        this.getCommand("sendprep").setExecutor(new prepCommand(this));
        this.findEvent();

        eventBar = Bukkit.createBossBar(
                ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "hh " + ChatColor.RESET + "" + ChatColor.RED + "The Apocalypse" + ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + " hh",
                BarColor.WHITE,
                BarStyle.SOLID
        );

        eventBar.addFlag(BarFlag.CREATE_FOG);
        eventBar.addFlag(BarFlag.DARKEN_SKY);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void findEvent(){
        //When adding an event make sure to add it to this switch with a config friendly name
        String event = getConfig().getString("event");
        //We must unregister any old events
        Bukkit.getPluginManager().registerEvents(new onJoin(this), this);
        if (apocalypseEnabled){
            ApocalypseMain apocalypseMain = new ApocalypseMain(this);
            activeEventName = ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + "hh " + ChatColor.RESET + "" + ChatColor.RED + "The Apocalypse" + ChatColor.DARK_GRAY + "" + ChatColor.MAGIC + " hh";
            activeEventDescription = ChatColor.RED + "The End is Nigh";
            Bukkit.getPluginManager().registerEvents(apocalypseMain, this);
            return;
        }
        switch (event){
            //Chose a config friendly name
            case "Killer Bunnies": {
                //This code is triggered when the event is active, use it to set the name and description
                KillerBunnies eventClass = new KillerBunnies(this);
                activeEventName = eventClass.getName();
                activeEventDescription = eventClass.getDescription();
                Bukkit.getPluginManager().registerEvents(eventClass, this);
                break;
            }

            case "Nice Phantoms": {
                NicePhantoms eventClass = new NicePhantoms(this);
                activeEventName = eventClass.getName();
                activeEventDescription = eventClass.getDescription();
                Bukkit.getPluginManager().registerEvents(eventClass, this);
                break;
            }

            case "Cluck": {
                Cluck eventClass = new Cluck(this);
                activeEventName = eventClass.getName();
                activeEventDescription = eventClass.getDescription();
                Bukkit.getPluginManager().registerEvents(eventClass, this);
                break;
            }

            case "The Guardian Experience": {
                TheGuardianExperience eventClass = new TheGuardianExperience(this);
                activeEventName = eventClass.getName();
                activeEventDescription = eventClass.getDescription();
                Bukkit.getPluginManager().registerEvents(eventClass, this);
                break;
            }
        }

        if(activeEventName == null){
            Bukkit.getLogger().info("No event loaded");
            activeEventName = ChatColor.RESET + "No event loaded";
            activeEventDescription = ChatColor.RESET + "No event loaded";
            return;
        }else {
            Bukkit.getLogger().info("Current event is " + activeEventName);
            title = Bukkit.createBossBar(
                    net.md_5.bungee.api.ChatColor.of("#8d6acc") + "Current Event: " + activeEventName,
                    BarColor.WHITE,
                    BarStyle.SOLID
            );
            title.setVisible(true);

            for (Player p : Bukkit.getOnlinePlayers()) {
                getBar().addPlayer(p);
            }
        }
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

    public BossBar getEventBar(){
        return eventBar;
    }

    public boolean isApocalypseEnabled() {
        return apocalypseEnabled;
    }

    public void setApocalypseEnabled(boolean apocalypseEnabled) {
        this.apocalypseEnabled = apocalypseEnabled;
    }
}
