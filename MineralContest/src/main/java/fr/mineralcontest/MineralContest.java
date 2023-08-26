package fr.mineralcontest;

import fr.mineralcontest.commands.*;
import fr.mineralcontest.events.JoinTeamEvent;
import fr.mineralcontest.events.KitEvent;
import fr.mineralcontest.events.ScoreEvent;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public final class MineralContest extends JavaPlugin {

    public static Inventory inv = ChestGen.generateRandomOreInventory();
    public static ArrayList<Player> op = new ArrayList<>();

    @Override


    public void onEnable() {

        System.out.println("Le plugin MineralContest est bien chargé");
        getCommand("start").setExecutor(new CommandStart());
        getCommand("score").setExecutor(new CommandScore());
        getCommand("setlocation").setExecutor(new CommandTeamLocation());
        getCommand("stopgame").setExecutor(new CommandStop());
        getCommand("arene").setExecutor(new CommandArene());

        this.getServer().getPluginManager().registerEvents(new JoinTeamEvent(), this);
        this.getServer().getPluginManager().registerEvents(new ScoreEvent(), this);
        this.getServer().getPluginManager().registerEvents(new KitEvent(), this);
        ItemManager.init();


        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
            try {
                Start.teamconfiguration.save(Start.teamfile);
                Start.teamconfiguration.load(Start.teamfile);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }

        Bukkit.getWorld("world").getWorldBorder().setSize(1024);
        ScoreEvent.loadchono();
        ScoreEvent.cost();
        KitEvent.loadcutclean();
    }


    @Override
    public void onDisable() {
        System.out.println("Le plugin MineralContest est bien déchargé");    }

}
