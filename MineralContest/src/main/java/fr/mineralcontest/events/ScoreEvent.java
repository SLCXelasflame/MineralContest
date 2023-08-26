package fr.mineralcontest.events;

import fr.mineralcontest.*;
import fr.mineralcontest.commands.ChestGen;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.EnderChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Scoreboard;
import org.checkerframework.checker.units.qual.A;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class ScoreEvent implements Listener {

    public static HashMap<Material, Integer> list = new HashMap<>();

    public static void cost(){
        list.put(Material.IRON_INGOT, 10);
        list.put(Material.GOLD_INGOT, 50);
        list.put(Material.DIAMOND, 150);
        list.put(Material.EMERALD, 300);
    }

    @EventHandler
        public void onEnderchestClose(InventoryCloseEvent event) {
            Player player = (Player) event.getPlayer();
            Inventory enderchest = event.getInventory();

            if (enderchest.getType() == InventoryType.ENDER_CHEST) {
                int score = 0;
                for (ItemStack item : event.getInventory().getContents()) {
                    if (item != null) {
                        if (list.containsKey(item.getType())) {
                            score += item.getAmount() * list.get(item.getType());
                        } else if (item.getType().equals(Material.REDSTONE)) {
                            ScoreManager.redstone(player, 5*item.getAmount());
                        } else if (item.getType().equals(Material.COPPER_INGOT)) {
                            ScoreManager.redstone(player, 10*item.getAmount());

                        }
                    }

                }
                if (Start.getKit(player).equalsIgnoreCase("travailleur")){
                    score *= 1.25;
                }
                player.getEnderChest().clear();
                ScoreManager.modifscore(Start.viewteam(player), score);
            }
        }

    private static Player open = null;
    private static Player old = null;

    private static BukkitTask countdownTask = null;
    private static ArrayList<Inventory> chronolist = new ArrayList<>();
    public static Inventory invchrono = Bukkit.createInventory(null, 9, "Timer");
    public static Inventory invchrono1 = Bukkit.createInventory(null, 9, "Timer");
    public static Inventory invchrono2 = Bukkit.createInventory(null, 9, "Timer");
    public static Inventory invchrono3 = Bukkit.createInventory(null, 9, "Timer");
    public static Inventory invchrono4 = Bukkit.createInventory(null, 9, "Timer");
    public static Inventory invchrono5 = Bukkit.createInventory(null, 9, "Timer");

    public static Inventory bonus = Bukkit.createInventory(null, 36);
    public static int count = 4;


    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        if (event.getPlayer().equals(open) && !(chronolist.contains(event.getInventory()))) {
            open = null;
            if (countdownTask != null) {
                countdownTask.cancel(); // Annuler la tâche différée en cas de fermeture de l'inventaire
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();

        if (clickedBlock != null && clickedBlock.getType() == Material.NETHER_BRICKS &&
                event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (open != null) {
                return; // Quitter si un compte à rebours est déjà en cours
            }

            rlcounter();

            player.openInventory(chronolist.get(0));
            open = player;

            countdownTask = new BukkitRunnable() {
                @Override
                public void run() {
                    if (open != null) {
                        if (count >= 0) {
                            int timer = 5 - count;
                            player.openInventory(chronolist.get(timer));
                            old = player;
                            count--;
                        } else {
                            player.openInventory(MineralContest.inv);
                            clickedBlock.setType(Material.AIR);
                            rlcounter();
                            TimerGame.chest = false;
                            cancel();
                        }
                    } else {
                        Bukkit.broadcastMessage("tu as arreter de crocheter le coffre");

                        cancel();
                    }
                }
            }.runTaskTimer(MineralContest.getPlugin(MineralContest.class), 0L, 20);
        }
    }
public static void loadchono(){

    invchrono.setItem(0, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono.setItem(1, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono.setItem(2, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono.setItem(3, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono.setItem(4, new ItemStack(Material.RED_STAINED_GLASS, 1));
    chronolist.add(invchrono);


    invchrono1.setItem(0, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono1.setItem(1, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono1.setItem(2, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono1.setItem(3, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono1.setItem(4, new ItemStack(Material.RED_STAINED_GLASS, 1));
    chronolist.add(invchrono1);


    invchrono2.setItem(0, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono2.setItem(1, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono2.setItem(2, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono2.setItem(3, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono2.setItem(4, new ItemStack(Material.RED_STAINED_GLASS, 1));
    chronolist.add(invchrono2);


    invchrono3.setItem(0, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono3.setItem(1, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono3.setItem(2, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono3.setItem(3, new ItemStack(Material.RED_STAINED_GLASS, 1));
    invchrono3.setItem(4, new ItemStack(Material.RED_STAINED_GLASS, 1));
    chronolist.add(invchrono3);



    invchrono4.setItem(0, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono4.setItem(1, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono4.setItem(2, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono4.setItem(3, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono4.setItem(4, new ItemStack(Material.RED_STAINED_GLASS, 1));
    chronolist.add(invchrono4);

    invchrono5.setItem(0, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono5.setItem(1, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono5.setItem(2, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono5.setItem(3, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    invchrono5.setItem(4, new ItemStack(Material.GREEN_STAINED_GLASS, 1));
    chronolist.add(invchrono5);

}
    public void rlcounter() {
        count = 4;
        open = null;
    }



    }








