package co.uk.lostanddead.amethystevents.Apocalypse;

import co.uk.lostanddead.amethystevents.AmethystEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class mobsSetup {

    private AmethystEvents core;

    public mobsSetup(AmethystEvents core){
        this.core=core;
    }


    public Zombie editZombie(Zombie zombie){
        zombie.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(4);
        zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6);
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        zombie.setHealth(30);
        zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        return zombie;
    }

    public Skeleton editSkeleton(Skeleton skeleton){
        skeleton.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(4);
        skeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
        skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
        skeleton.setHealth(30);
        return skeleton;
    }

    public Spider editSpider(Spider spider){
        spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
        spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        spider.setHealth(20);
        spider.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        return spider;
    }

    public Zombie editBossZombie(Zombie zombie){
        zombie.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(6);
        zombie.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5.5);
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        zombie.setHealth(50);
        zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        zombie.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS).setBaseValue(100);
        zombie.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.5);

        ItemStack helmet = new ItemStack(Material.PAPER);

        ItemMeta helmetItemMeta = helmet.getItemMeta();
        helmetItemMeta.setCustomModelData(1);
        helmetItemMeta.setUnbreakable(true);
        helmetItemMeta.setDisplayName(ChatColor.DARK_GREEN + "Zombie Lord Crown");
        helmetItemMeta.getPersistentDataContainer().set(new NamespacedKey(core, "AE-UUID"), PersistentDataType.STRING, zombie.getUniqueId().toString());
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.RED + "Obtained from defeating a Zombie Lord");
        lore.add(ChatColor.RED + "During the Apocalypse Event");
        helmetItemMeta.setLore(lore);
        helmet.setItemMeta(helmetItemMeta);

        helmet.addUnsafeEnchantment(Enchantment.THORNS, 3);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

        sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);

        zombie.getEquipment().setItemInMainHand(sword);
        zombie.getEquipment().setItemInMainHandDropChance(0F);
        zombie.getEquipment().setHelmet(helmet);
        zombie.getEquipment().setHelmetDropChance(0.10F);

        zombie.setCustomNameVisible(true);
        zombie.setCustomName(ChatColor.RED + "" + ChatColor.MAGIC + "hh" + ChatColor.DARK_GREEN + " Zombie Lord " + ChatColor.RED + "" + ChatColor.MAGIC + "hh");
        zombie.setPersistent(true);

        PotionEffect fireImmune = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
        zombie.addPotionEffect(fireImmune);

        return zombie;
    }

    public Skeleton editBossSkeleton(Skeleton skeleton){
        skeleton.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(5);
        skeleton.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
        skeleton.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        skeleton.setHealth(50);
        skeleton.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.4);

        ItemStack helmet = new ItemStack(Material.PAPER);

        ItemMeta helmetItemMeta = helmet.getItemMeta();
        helmetItemMeta.setCustomModelData(2);
        helmetItemMeta.setUnbreakable(true);
        helmetItemMeta.setDisplayName(ChatColor.WHITE + "Skeleton Lord Crown");
        helmetItemMeta.getPersistentDataContainer().set(new NamespacedKey(core, "AE-UUID"), PersistentDataType.STRING, skeleton.getUniqueId().toString());
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.RED + "Obtained from defeating a Skeleton Lord");
        lore.add(ChatColor.RED + "During the Apocalypse Event");
        helmetItemMeta.setLore(lore);
        helmet.setItemMeta(helmetItemMeta);

        helmet.addUnsafeEnchantment(Enchantment.THORNS, 3);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4);

        ItemStack bow = new ItemStack(Material.BOW);

        bow.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 4);
        bow.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 2);
        bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);

        skeleton.getEquipment().setItemInMainHand(bow);
        skeleton.getEquipment().setItemInMainHandDropChance(0F);
        skeleton.getEquipment().setHelmet(helmet);
        skeleton.getEquipment().setHelmetDropChance(0.10F);

        skeleton.setCustomNameVisible(true);
        skeleton.setCustomName(ChatColor.RED + "" + ChatColor.MAGIC + "hh" + ChatColor.WHITE + " Skeleton Lord " + ChatColor.RED + "" + ChatColor.MAGIC + "hh");
        skeleton.setPersistent(true);

        PotionEffect fireImmune = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
        skeleton.addPotionEffect(fireImmune);
        return skeleton;
    }

    public Spider editBossSpider(Spider spider){
        spider.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(5);
        spider.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(5);
        spider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
        spider.setHealth(50);
        spider.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.4);
        spider.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(0.3);

        ItemStack string = new ItemStack(Material.STRING);

        ItemMeta stringItemMeta = string.getItemMeta();
        stringItemMeta.setDisplayName(ChatColor.GRAY + "Spider Lord Silk");
        stringItemMeta.getPersistentDataContainer().set(new NamespacedKey(core, "AE-UUID"), PersistentDataType.STRING, spider.getUniqueId().toString());
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(ChatColor.RED + "Obtained from defeating a Spider Lord");
        lore.add(ChatColor.RED + "During the Apocalypse Event");
        stringItemMeta.setLore(lore);
        string.setItemMeta(stringItemMeta);

        string.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 6);

        spider.getEquipment().setItemInMainHand(string);
        spider.getEquipment().setItemInMainHandDropChance(0.10F);

        spider.setCustomNameVisible(true);
        spider.setCustomName(ChatColor.RED + "" + ChatColor.MAGIC + "hh" + ChatColor.GRAY + " Spider Lord " + ChatColor.RED + "" + ChatColor.MAGIC + "hh");
        spider.setPersistent(true);

        PotionEffect fireImmune = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1);
        spider.addPotionEffect(fireImmune);
        return spider;
    }
}
