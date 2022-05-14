package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class NavigatorInventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 27, "§8» §9Navigator");

        ItemStack item01 = ItemAPI.getItem(Material.ENDER_PEARL, "§9Spawn", null, 1);
        ItemStack item02 = ItemAPI.getItem(Material.GRASS, "§9CityBuild", null, 1);
        ItemStack item03 = ItemAPI.getItem(Material.BARRIER, "§cIn Entwicklung", null, 1);

        player.openInventory(inventory);

        inventory.setItem(13, item01);
        inventory.setItem(11, item02);
        inventory.setItem(15, item03);

    }

}
