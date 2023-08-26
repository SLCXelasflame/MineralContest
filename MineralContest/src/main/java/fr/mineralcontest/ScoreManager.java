package fr.mineralcontest;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class ScoreManager {
    public static HashMap<String, Integer> teamscore = new HashMap<>();
    public static void addteamscore(String team){
        teamscore.put(team, 0);
        modifscore(team, 0);
    }


    public static void modifscore(String team, int score){
        int old = teamscore.get(team);
        teamscore.put(team, old + score);
        TimerGame.point.setSuffix(teamscore.get(team).toString());
        for (Player player : Start.teamplayer(team)){
            player.setScoreboard(TimerGame.scoreboard);
            player.sendMessage("Tu as marqué " + score + " points");
            player.sendMessage("Ton score est de " + teamscore.get(team) + " points");
        }
    }

    public static void afficherscore(Player player) {
        for (String key : teamscore.keySet()) {
            player.sendMessage(key +" : " + teamscore.get(key) +" points");
        }
    }

    public static void redstone(Player player, int malus) {
        for (String key : teamscore.keySet()) {
                if (!(Start.viewteam(player).equalsIgnoreCase(key))){
                    modifscore(key, -malus);
                }
        }
    }}
