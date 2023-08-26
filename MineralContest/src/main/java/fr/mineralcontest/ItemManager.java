package fr.mineralcontest;


import org.bukkit.ChatColor;
import org.bukkit.Material;


import org.bukkit.enchantments.Enchantment;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class ItemManager {


    public static ItemStack Team_Selector;
    public static ItemStack Bingo_List;
    public static ItemStack red;
    public static ItemStack blue;
    public static ItemStack green;
    public static ItemStack yellow;
    public static ItemStack kit_selector;
    public static ItemStack agile;
    public static ItemStack robuste;
    public static ItemStack mineur;
    public static ItemStack guerrier;
    public static ItemStack travailleur;
    public static ItemStack mineurlock;




    public static void init() {
        createTeam_Selector();
        createBingo_List();
        createred();
        createblue();
        creategreen();
        createyellow();
        createagile();
        createkit_selector();
        createmineur();
        createguerrier();
        createtravaileur();
        createrobuste();
        createmineurlock();
    }

    private static void createTeam_Selector() {
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Team selector");
        meta.addEnchant(Enchantment.MENDING, 1, true);
        item.setItemMeta(meta);
        Team_Selector = item;
    }

    private static void createBingo_List() {
        ItemStack item = new ItemStack(Material.BOOK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Bingo list");
        meta.addEnchant(Enchantment.MENDING, 1, true);
        item.setItemMeta(meta);
        Bingo_List = item;
    }
    private static void createred() {
        ItemStack item = new ItemStack(Material.RED_STAINED_GLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Equipe rouge");
        item.setItemMeta(meta);
        red = item;
    }
    private static void createblue() {
        ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_BLUE + "Equipe bleu");
        item.setItemMeta(meta);
        blue = item;
    }
    private static void creategreen() {
        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "Equipe vert");
        item.setItemMeta(meta);
        green = item;
    }
    private static void createyellow() {
        ItemStack item = new ItemStack(Material.YELLOW_STAINED_GLASS, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Equipe jaune");
        item.setItemMeta(meta);
        yellow = item;
    }

    private static void createagile() {
        ItemStack item = new ItemStack(Material.FEATHER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Agile");
        item.setItemMeta(meta);
        agile = item;
    }

    private static void createkit_selector() {
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Kit selector");
        item.setItemMeta(meta);
        kit_selector = item;
    }

    private static void createmineur() {
        ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Mineur");
        item.setItemMeta(meta);
        mineur = item;
    }

    private static void createguerrier() {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Guerrier");
        item.setItemMeta(meta);
        guerrier = item;
    }

    private static void createtravaileur() {
        ItemStack item = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Travailleur");
        item.setItemMeta(meta);
        travailleur = item;
    }

    private static void createrobuste() {
        ItemStack item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Robuste");
        item.setItemMeta(meta);
        robuste = item;
    }
    private static void createmineurlock() {
        ItemStack item = new ItemStack(Material.BARRIER, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.RED + "Case d'inventaire condamnée");
        item.setItemMeta(meta);
        mineurlock = item;
    }


}