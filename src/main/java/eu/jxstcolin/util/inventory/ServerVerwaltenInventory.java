package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import eu.jxstcolin.main.MCLandsLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ServerVerwaltenInventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 27, "§8» §9Server Verwalten");

        ItemStack item01;

        ItemStack item02 = ItemAPI.getItem(Material.SKULL_ITEM, "§9Spieler", null, 1);;

        if (!MCLandsLobby.isCountdownRunning) {
            item01 = ItemAPI.getItem(Material.ARROW, "§4Server Stoppen", null, 1);
        } else {
            item01 = ItemAPI.getItem(Material.ARROW, "§aCountdown abbrechen", null, 1);
        }

        player.openInventory(inventory);

        inventory.setItem(11, item01);
        inventory.setItem(15, item02);

    }

}
