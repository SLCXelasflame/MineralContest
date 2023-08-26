package fr.mineralcontest;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameManager {

    public static File file = new File(MineralContest.getPlugin(MineralContest.class).getDataFolder(), "base.yml");
    public static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);

    public static void giveStuff(Player player){
        player.getInventory().clear();
        Inventory inv = player.getInventory();
        ItemStack[] armor = new ItemStack[4];
        armor[0] = new ItemStack(Material.IRON_BOOTS, 1);
        armor[1] = new ItemStack(Material.IRON_LEGGINGS, 1);
        armor[2] = new ItemStack(Material.IRON_CHESTPLATE, 1);
        armor[3] = new ItemStack(Material.IRON_HELMET, 1);
        player.getInventory().setArmorContents(armor);
        inv.setItem(0, new ItemStack(Material.IRON_SWORD, 1));
        inv.setItem(1, new ItemStack(Material.BOW, 1));
        inv.setItem(2, new ItemStack(Material.ARROW, 32));
        inv.setItem(3, new ItemStack(Material.COOKED_BEEF, 64));
        inv.setItem(4, new ItemStack(Material.WATER_BUCKET, 1));
        player.getInventory().setItemInOffHand(new ItemStack(Material.SHIELD));
        player.setGameMode(GameMode.SURVIVAL);

    }
    public static void loadKit(Player player){
        String kit = Start.config.getString(player.getName());
        assert kit != null;
        if (kit.equalsIgnoreCase("guerrier")){
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(14.0);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1.25);
            player.setHealth(14.0);
        }
        if (kit.equalsIgnoreCase("travailleur")){
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(10.0);
            player.setHealth(10.0);
        }
        if (kit.equalsIgnoreCase("robuste")){
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(30.0);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.085);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(0.85);
            player.setHealth(30.0);
        }
        if (kit.equalsIgnoreCase("agile")){
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.12);

        }
        if (kit.equalsIgnoreCase("mineur")){
            for (int i = 9; i<18; i++){
                player.getInventory().setItem(i, ItemManager.mineurlock);
            }        }
    }
    public static void clearAllEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
    }

    public static void gamestart() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            String team = Start.viewteam(player);
            clearAllEffects(player);
            if (team != null && !ScoreManager.teamscore.containsKey(team)) {
                ScoreManager.addteamscore(team);
                String command = "/rg addmember " + team  + " " + player.getName();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                player.getInventory().clear();
                Location loc = configuration.getLocation(team);
                String kit = Start.config.getString(player.getName());
                if (loc == null || kit == null){
                    player.setGameMode(GameMode.SPECTATOR);
                    player.sendMessage("Le spawn de votre team n'est pas défini");
                }
                else {
                    player.teleport(loc);
                    giveStuff(player);
                    loadKit(player);
                }

            }
        }

    }
    public static void addLoc(String center, Player player){
        configuration.set(center, player.getLocation());
        Location location = player.getLocation();
        double y = location.getY();
        for (String key : configuration.getKeys(false)){
            Location loc = configuration.getLocation(key);
            if (key.contains("arene")){
                loc.setY(y + 4);
            } else if (!key.equals("center")) {
                loc.setY(y + 10);
            }
            configuration.set(key, loc);
        }
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}