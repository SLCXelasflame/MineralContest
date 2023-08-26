package fr.mineralcontest.commands;

import fr.mineralcontest.GameManager;
import fr.mineralcontest.ScoreManager;
import fr.mineralcontest.Start;
import fr.mineralcontest.TimerGame;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandArene implements CommandExecutor {




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("arene")) {
                if (TimerGame.chest){
                    String team = Start.teamconfiguration.getString(sender.getName());
                        Location loc = GameManager.configuration.getLocation("arene" + team);
                        if (loc == null){
                            sender.sendMessage("la location arene n'est pas defini");
                        }
                        else {
                            for (Player player : Start.teamplayer(team)) {
                                player.teleport(loc);
                            }
                        }
                }
            }
        }
        return false;
    }


}
