package fr.mineralcontest;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;
import java.util.Timer;

public class EndGame {
    public static void stop(Player player){

            player.setGameMode(GameMode.SURVIVAL);
            Location loc = GameManager.configuration.getLocation("center");
            assert loc != null;
            player.teleport(loc);
            GameManager.clearAllEffects(player);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(20.0);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1.0);
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)).setBaseValue(0.1);
            Scoreboard scoreboard = TeamScoreboard.displayScoreboard();
            TimerGame.timer.setSuffix("");
            TimerGame.point.setSuffix("0");
            player.setScoreboard(scoreboard);
            player.getInventory().clear();
            player.getInventory().setItem(0, ItemManager.Team_Selector);
            player.getInventory().setItem(1, ItemManager.kit_selector);

            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, PotionEffect.INFINITE_DURATION, 254, true , false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, PotionEffect.INFINITE_DURATION, 254, true, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, PotionEffect.INFINITE_DURATION, 254, true, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 254, true, false));
            String team = Start.viewteam(player);
            String command = "rg removemember " + team  + " " + player.getName();
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);


    }
}
