package fr.mineralcontest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class TimerGame {


    public static int secondsRemaining = 3600;
    public static Boolean chest = false;

    public static Scoreboard scoreboard = TeamScoreboard.displayScoreboard();
    public static Objective objective = scoreboard.registerNewObjective(ChatColor.AQUA + "Mineral Contest", "dummy");
    public static Team timer = scoreboard.registerNewTeam("timer");
    public static Team point = scoreboard.registerNewTeam("point");
    public static ArrayList<Integer> chronos = new ArrayList<>();


    public static void loadobjective(){objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        timer.addEntry("Temps restant : ");
        point.addEntry("Points : ");
        objective.getScore("Temps restant : ").setScore(1);
        objective.getScore("Points : ").setScore(0);
        objective.setDisplayName("Mineral Contest");
        chronos.add(15*60);
        chronos.add(30*60);
        chronos.add(45*60);


    }




    public static void start() {

        new BukkitRunnable() {

            @Override
            public void run() {
                if (secondsRemaining > 0) {
                    if (chronos.contains(secondsRemaining)){
                        chest = true;
                        Location loc = GameManager.configuration.getLocation("center");
                        Bukkit.getWorld("world").getBlockAt(loc).setType(Material.NETHER_BRICKS);
                        Bukkit.broadcastMessage("Le coffre d arene vient d'apparaitre depeche toi d'y aller avec le /arene");
                    }
                    updateScoreboard();
                    secondsRemaining--;
                } else {
                    Bukkit.broadcastMessage("La game est terminée");
                    cancel();
                }
            }
        }.runTaskTimer(MineralContest.getPlugin(MineralContest.class), 0L, 20L); // Exécution toutes les secondes
    }

    private static void updateScoreboard() {

        int hours = secondsRemaining / 3600;
        int minutes = (secondsRemaining % 3600) / 60;
        int remainingSeconds = secondsRemaining % 60;

        String formattedTime = String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
        timer.setSuffix(formattedTime);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setScoreboard(scoreboard);
        }
    }


}



