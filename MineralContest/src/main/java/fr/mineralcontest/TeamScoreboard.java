package fr.mineralcontest;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class TeamScoreboard {
    public static Scoreboard displayScoreboard(){
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Team rouge = board.registerNewTeam("rouge");
        rouge.setPrefix("[Rouge] ");
        rouge.setColor(ChatColor.RED);
        Team bleu = board.registerNewTeam("bleu");
        bleu.setPrefix("[Bleu] ");
        bleu.setColor(ChatColor.BLUE);
        Team vert = board.registerNewTeam("vert");
        vert.setPrefix("[Vert] ");
        vert.setColor(ChatColor.GREEN);
        Team jaune = board.registerNewTeam("jaune");
        jaune.setPrefix("[Jaune] ");
        jaune.setColor(ChatColor.YELLOW);

        return board;
    }

    public static void addteam(Player player, String team){
        Scoreboard scoreboard = displayScoreboard();
        Objects.requireNonNull(scoreboard.getTeam(team)).addEntry(player.getName());

        player.setScoreboard(scoreboard);

    }

    
}
