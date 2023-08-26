package fr.mineralcontest.commands;

import fr.mineralcontest.GameManager;
import fr.mineralcontest.MineralContest;
import fr.mineralcontest.Start;
import fr.mineralcontest.TimerGame;
import fr.mineralcontest.events.ScoreEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandStart implements CommandExecutor {




    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equalsIgnoreCase("start")) {
                startCountdown();

            }
        }
        return false;
    }

    private void startCountdown() {

        new BukkitRunnable() {
            int count = 6;

            @Override
            public void run() {
                if (count == 6){
                    Start.sendTitleToAllPlayers("La game va bientôt se lancée", "", 10, 30, 10);
                    count --;
                }
                else if (count > 0) {
                    Start.sendTitleToAllPlayers( String.valueOf(count),"", 10, 30, 10);
                    count--;
                } else {
                    Start.sendTitleToAllPlayers("Bonne gamme a tous","" , 10, 30, 10);
                    TimerGame.loadobjective();
                    TimerGame.secondsRemaining = 3600;
                    TimerGame.start();
                    GameManager.gamestart();
                    Location loc = GameManager.configuration.getLocation("center");
                    Bukkit.getWorld("world").getBlockAt(loc).setType(Material.AIR);
                    cancel();
                }
            }
        }.runTaskTimer(MineralContest.getPlugin(MineralContest.class), 0L, 20L);

    }

}
