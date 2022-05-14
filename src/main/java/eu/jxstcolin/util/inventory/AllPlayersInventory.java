package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import eu.jxstcolin.main.MCLandsLobby;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AllPlayersInventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 54, "§8» §9Spieler");

        Bukkit.getOnlinePlayers().forEach(o -> {

            ItemStack skull = ItemAPI.getPlayerHead(o.getName(), "§9" + o.getName(), null, 1);
            inventory.addItem(skull);

        });

        player.openInventory(inventory);

    }

}
