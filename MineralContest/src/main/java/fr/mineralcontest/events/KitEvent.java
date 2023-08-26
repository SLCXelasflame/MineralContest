package fr.mineralcontest.events;

import fr.mineralcontest.GameManager;
import fr.mineralcontest.ItemManager;
import fr.mineralcontest.Start;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class KitEvent implements Listener {
    public static HashMap<Material, Material> cutclean = new HashMap<>();
    public static ArrayList<Material> tools = new ArrayList<>();

    public static void loadcutclean(){
        cutclean.put(Material.IRON_ORE, Material.IRON_INGOT);
        cutclean.put(Material.GOLD_ORE, Material.GOLD_INGOT);
        cutclean.put(Material.COAL_ORE, Material.COAL_BLOCK);
        cutclean.put(Material.COPPER_ORE, Material.COPPER_INGOT);
        cutclean.put(Material.DEEPSLATE_COAL_ORE, Material.COAL_BLOCK);
        cutclean.put(Material.DEEPSLATE_COPPER_ORE, Material.COPPER_INGOT);
        cutclean.put(Material.DEEPSLATE_IRON_ORE, Material.IRON_INGOT);
        cutclean.put(Material.DEEPSLATE_GOLD_ORE, Material.GOLD_INGOT);
        tools.add(Material.WOODEN_PICKAXE);
        tools.add(Material.STONE_PICKAXE);
        tools.add(Material.IRON_PICKAXE);
        tools.add(Material.GOLDEN_PICKAXE);
        tools.add(Material.DIAMOND_PICKAXE);
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL && Start.getKit((Player) event.getEntity()).equalsIgnoreCase("agile")) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block blockbreak = event.getBlock();
        if (Start.getKit(player).equalsIgnoreCase("mineur")) {
            if (cutclean.containsKey(blockbreak.getType())) {
                Location loc = blockbreak.getLocation();
                Bukkit.getWorld("world").dropItem(loc, new ItemStack(cutclean.get(blockbreak.getType()), 1));
                blockbreak.setType(Material.AIR);
            }
        }
    }
    @EventHandler
    public void onCraft(CraftItemEvent event){
        ItemStack item = event.getCurrentItem();
        assert item !=null;
        if (tools.contains(item.getType())){
            ItemMeta meta = item.getItemMeta();
            meta.addEnchant(Enchantment.DIG_SPEED ,3, true);
            item.setItemMeta(meta);
        }
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        if (droppedItem.getType().equals(Material.BARRIER)) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem.getType().equals(Material.BARRIER)) {
            event.setCancelled(true);
        }}
}
