package co.uk.lostanddead.amethystevents.Apocalypse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class mobsSetup {


    public Zombie editZombie(Zombie zombie){
        zombie.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(4);
        zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6);
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        zombie.setHealth(30);
        zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        return zombie;
    }

    public Skeleton editSkeleton(Skeleton skeleton){
        return skeleton;
    }

    public Spider editSpider(Spider spider){
        return spider;
    }

    public Zombie editBossZombie(Zombie zombie){
        zombie.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(6);
        zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5.5);
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        zombie.setHealth(50);
        zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        zombie.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(100);

        ItemStack helmet = new ItemStack(Material.GOLDEN_HELMET);

        ItemMeta helmetItemMeta = helmet.getItemMeta();
        helmetItemMeta.setCustomModelData(1);
        helmetItemMeta.setUnbreakable(true);
        helmetItemMeta.setDisplayName(ChatColor.GOLD + "Zombie Boss Crown");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.RED + "Obtained from defeating the Zombie Boss");
        lore.add(ChatColor.RED + "During the Apocalypse Event");
        helmetItemMeta.setLore(lore);
        helmet.setItemMeta(helmetItemMeta);

        helmet.addEnchantment(Enchantment.THORNS, 3);
        helmet.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

        sword.addEnchantment(Enchantment.DAMAGE_ALL, 3);

        zombie.getEquipment().setItemInMainHand(sword);
        zombie.getEquipment().setItemInMainHandDropChance(0F);
        zombie.getEquipment().setHelmet(helmet);
        zombie.getEquipment().setHelmetDropChance(0.25F);

        return zombie;
    }

    public Skeleton editBossSkeleton(Skeleton skeleton){
        return skeleton;
    }

    public Spider editBossSpider(Spider spider){
        return spider;
    }
}
