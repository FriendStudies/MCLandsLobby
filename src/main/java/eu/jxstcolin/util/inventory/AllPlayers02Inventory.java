package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AllPlayers02Inventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 9, "§8» §9Action-Changer");

        ItemStack action01 = ItemAPI.getItem(Material.BOOK, "§9Gruppe setzen", null, 1);

        inventory.addItem(action01);

        player.openInventory(inventory);

    }

}
