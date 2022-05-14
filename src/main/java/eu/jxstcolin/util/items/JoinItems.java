package eu.jxstcolin.util.items;

import eu.jxstcolin.api.ItemAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class JoinItems {

    public static void giveItems(Player player){

        player.getInventory().clear();

        player.getInventory().setItem(2, ItemAPI.getItem(Material.BLAZE_ROD, "§cSpieler verstecken", null, 1));
        player.getInventory().setItem(4, ItemAPI.getItem(Material.COMPASS, "§9Navigator", null, 1));
        player.getInventory().setItem(6, ItemAPI.getPlayerHead(player.getName(), "§9Dein Profil", null, 1));

    }
}
