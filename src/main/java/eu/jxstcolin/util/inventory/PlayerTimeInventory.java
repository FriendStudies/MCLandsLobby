package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerTimeInventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 9, "§8» §9Spieler Zeit");

        ItemStack item01 = ItemAPI.getItem(Material.SPIDER_EYE, "§eTag", null, 1);
        ItemStack item02 = ItemAPI.getItem(Material.SPIDER_EYE, "§9Nacht", null, 1);

        player.openInventory(inventory);

        inventory.setItem(1, item01);
        inventory.setItem(7, item02);

    }

}
