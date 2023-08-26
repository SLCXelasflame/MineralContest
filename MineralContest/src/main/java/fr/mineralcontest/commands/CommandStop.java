package fr.mineralcontest.commands;

import fr.mineralcontest.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandStop implements CommandExecutor {




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("stopgame")) {
                TimerGame.secondsRemaining = 1;
                for (Player player : Bukkit.getOnlinePlayers()) {
                    EndGame.stop(player);}

            }
        }
        return false;
    }


}
