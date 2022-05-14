package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import eu.jxstcolin.main.MCLandsLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SettingsInventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 9, "§8» §9Einstellungen");

        ItemStack item01 = ItemAPI.getItem(Material.YELLOW_FLOWER, "§9Spieler-Zeit", null, 1);;

        player.openInventory(inventory);

        inventory.addItem(item01);

    }

}
