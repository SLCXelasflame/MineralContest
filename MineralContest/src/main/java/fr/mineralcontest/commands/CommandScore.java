package fr.mineralcontest.commands;

import fr.mineralcontest.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandScore implements CommandExecutor {




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("score")) {
                ScoreManager.afficherscore(((Player) sender).getPlayer());
            }
        }
        return false;
    }


}
