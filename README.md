# Amethyst Events
This plugin is uses as the core system for managing server events on Amethyst Realms,
<br>We do have a team of staff and developers who make and managed these events as well as the core system for the server (Not open source), but we could always use more events.
<br>If you have an idea then just make a pull request to this repo and add your event!
<br>If you have an idea for an event but don't know how to code then let me know, and I might add it (LostAndDead#0001)

## How to add an event
I have tried to make adding an event as easy as possible,
<br>If you are ever in doubt then look at the killer bunnies event that is fully commented.

1. Create a class for your event in the Events package, it should implement the Listener class
2. Should you need to access plugin or server values the main class is passed into the event on creation
```java
public AmethystEvents core;

//All events are given the core when setup
public KillerBunnies(AmethystEvents core){
    this.core = core;
}
```
3. Implement 2 key functions as shown bellow
```java
//All events must return a user friendly name
public String getName(){
    return ChatColor.RED + "Killer Bunnies";
}

//All events must return a non-colour coded and user friendly description
public String getDescription(){
    return "All bunnies are out to get you!";
}
```
4. Add whatever listeners and code your event requires
5. Add it to the master switch in the main class and set the name and description values
```java
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
```
6. And boom, that should be it. Any issues message me on Discord (LostAndDead#0001)
   
