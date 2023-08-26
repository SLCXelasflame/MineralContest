package fr.mineralcontest;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Start {


    public  static File teamfile = new File(MineralContest.getPlugin(MineralContest.class).getDataFolder(), "team.yml");
    public static YamlConfiguration teamconfiguration = YamlConfiguration.loadConfiguration(teamfile);


    public  static File file = new File(MineralContest.getPlugin(MineralContest.class).getDataFolder(), "kit.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    public static void addTeam(String team, Player player){
        if (player == null){
            return;
        }
        String pseudo = player.getName();

        teamconfiguration.set(pseudo, team);
        TeamScoreboard.addteam(player, team);

        try {
            teamconfiguration.save(teamfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void setKit(String kit, Player player){
        if (player == null){
            return;
        }
        String pseudo = player.getName();

        config.set(pseudo, kit);

        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String getKit(Player player){
        return config.getString(player.getName());
    }

        public static void sendTitleToAllPlayers(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
            }
        }
    public static String viewteam(Player player){
        return teamconfiguration.getString(player.getName()) ;
    }

public static ArrayList<Player> teamplayer(String team){
    ArrayList<Player> playerlist = new ArrayList<>();
    for (Player player : Bukkit.getOnlinePlayers()) {
        if (teamconfiguration.contains(player.getName())) {
            if (teamconfiguration.get(player.getName()).equals(team)) {
                playerlist.add(player);
            }
        }
    }
    return playerlist;
}}

