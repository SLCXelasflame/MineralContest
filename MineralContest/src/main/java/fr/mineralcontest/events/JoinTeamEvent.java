package fr.mineralcontest.events;

import fr.mineralcontest.*;
import org.bukkit.Bukkit;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;


public class JoinTeamEvent implements Listener {



    public Inventory team = Bukkit.createInventory(null, 9, "Team selector");
    public Inventory kit = Bukkit.createInventory(null, 9, "Kit selector");
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage("Psartek mon reuf bon mineral contest a toi");
        EndGame.stop(player);
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event){


        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack it = event.getItem();

        if (it == null) return;

        if (it.equals(ItemManager.Team_Selector)){
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){



                team.setItem(0, ItemManager.red);
                team.setItem(1, ItemManager.yellow);
                team.setItem(2, ItemManager.blue);
                team.setItem(3, ItemManager.green);
                player.openInventory(team);
            }
        } else if (it.equals(ItemManager.kit_selector)){
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK){



                kit.setItem(0, ItemManager.agile);
                kit.setItem(1, ItemManager.travailleur);
                kit.setItem(2, ItemManager.robuste);
                kit.setItem(3, ItemManager.guerrier);
                kit.setItem(4, ItemManager.mineur);
                player.openInventory(kit);
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){

        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        if (current == null) return;

        if (inv.equals(team)){

            event.setCancelled(true);

            if (current.isSimilar(ItemManager.red)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team rouge");
                Start.addTeam("rouge", player);
            }
            else if (current.isSimilar(ItemManager.yellow)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team jaune");
                Start.addTeam("jaune", player);
            }

            else if (current.isSimilar(ItemManager.blue)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team bleu");
                Start.addTeam("bleu", player);
            }

            else if (current.isSimilar(ItemManager.green)){
                player.closeInventory();
                player.sendMessage("§e§lTu vas rejoindre la team verte");
                Start.addTeam("vert", player);
            }


        } else if(inv.equals(kit)){

            event.setCancelled(true);

            if (current.isSimilar(ItemManager.agile)){
                player.closeInventory();
                player.sendMessage("Tu as selectionne le kit agile");
                Start.setKit("agile", player);
            }
            else if (current.isSimilar(ItemManager.travailleur)){
                player.closeInventory();
                player.sendMessage("Tu as selectionne le kit travailleur");
                Start.setKit("travailleur", player);
            }

            else if (current.isSimilar(ItemManager.robuste)){
                player.closeInventory();
                player.sendMessage("Tu as selectionne le kit robuste");
                Start.setKit("robuste", player);
            }

            else if (current.isSimilar(ItemManager.mineur)){
                player.closeInventory();
                player.sendMessage("Tu as selectionne le kit mineur");
                Start.setKit("mineur", player);
            }
            else if (current.isSimilar(ItemManager.guerrier)){
                player.closeInventory();
                player.sendMessage("Tu as selectionne le kit guerrier");
                Start.setKit("guerrier", player);
            }


        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
                for (ItemStack item : event.getDrops()) {
                    if (item != null) {
                        if (ScoreEvent.list.containsKey(item.getType())) {
                            Objects.requireNonNull(Bukkit.getWorld(p.getWorld().getName())).dropItem(p.getLocation(), item);
                        }


                    }}
                event.getDrops().clear();


            }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        p.setGameMode(GameMode.SPECTATOR);

        new BukkitRunnable() {
            int count = 5;

            @Override
            public void run() {
                if (count > 0) {
                    p.sendTitle( String.valueOf(count),"", 10, 30, 10);

                    count--;
                } else {
                    p.setGameMode(GameMode.SURVIVAL);
                    GameManager.giveStuff(p);
                    p.setSaturation(20);
                    GameManager.loadKit(p);
                    String team = Start.viewteam(p);
                    if (team != null && !ScoreManager.teamscore.containsKey(team)) {
                        Location loc = GameManager.configuration.getLocation(team);
                        assert loc != null;
                        event.setRespawnLocation(loc);


                    }
                }

            }
        }.runTaskTimer(MineralContest.getPlugin(MineralContest.class), 0L, 20L);
    }



}
