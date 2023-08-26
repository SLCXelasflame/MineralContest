package fr.mineralcontest.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ChestGen {
    public static Inventory generateRandomOreInventory() {
        Inventory inventory = Bukkit.createInventory(null, 54, "Mineral Inventory");
        Random random = new Random();
        int totalScore = 0;
         inventory.clear();
         totalScore = 0;

         for (int i = 0; i < inventory.getSize(); i++) {
             ItemStack oreStack = getRandomOre(random);
             int score = getScoreFromOre(oreStack.getType());

                totalScore += score;
                inventory.setItem(i, oreStack);
            }


        return inventory;
    }

    private static ItemStack getRandomOre(Random random) {
        int randomValue = random.nextInt(4);
        Material oreType;

        switch (randomValue) {
            case 0:
                oreType = Material.IRON_INGOT;
                break;
            case 1:
                oreType = Material.GOLD_INGOT;
                break;
            case 2:
                oreType = Material.DIAMOND;
                break;
            default:
                oreType = Material.EMERALD;
                break;
        }

        return new ItemStack(oreType, 1);
    }

    private static int getScoreFromOre(Material oreType) {
        switch (oreType) {
            case IRON_ORE:
                return 10;
            case GOLD_ORE:
                return 50;
            case DIAMOND_ORE:
                return 150;
            case EMERALD_ORE:
                return 300;
            default:
                return 0;
        }
    }
}
