package fr.mineralcontest.commands;

import fr.mineralcontest.EndGame;
import fr.mineralcontest.GameManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTeamLocation implements CommandExecutor {




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player =(Player) sender;
            if (command.getName().equalsIgnoreCase("setlocation")) {
                GameManager.addLoc(args[0], player);
                player.sendMessage("la location est bien prise en compte mais verifiez sur le base.yml");
            }
        }
        return false;
    }


}
