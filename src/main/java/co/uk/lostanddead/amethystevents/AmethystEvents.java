package co.uk.lostanddead.amethystevents;

import co.uk.lostanddead.amethystevents.Events.KillerBunnies;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AmethystEvents extends JavaPlugin {

    private String activeEventName;
    private String activeEventDescription;

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
        }

        Bukkit.getLogger().info("Loaded Amethyst Events");
        if(activeEventName == null){
            Bukkit.getLogger().info("No event loaded");
            activeEventName = "No event loaded";
            activeEventDescription = "No event loaded";
        }else{
            Bukkit.getLogger().info("Current event is " + activeEventName);
        }
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
}
