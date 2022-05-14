package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import eu.jxstcolin.main.MCLandsLobby;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ProfileInventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 27, "§8» §9Dein Profil");

        LuckPerms api = LuckPermsProvider.get();

        ArrayList<String> lore = new ArrayList<>();
        lore.add("§8--------------------");
        lore.add("§8» §7Dein Rang§8: §9" + Objects.requireNonNull(api.getUserManager().getUser(player.getUniqueId())).getPrimaryGroup());
        lore.add("§8--------------------");

        ItemStack item01 = ItemAPI.getItem(Material.BREWING_STAND_ITEM, "§9Settings", null, 1);
        ItemStack item02 = ItemAPI.getItem(Material.COMMAND, "§4Server Verwalten", null, 1);
        ItemStack item03 = ItemAPI.getItem(Material.EMERALD_BLOCK, "§9Ingame-Shop", null, 1);
        ItemStack item04 = ItemAPI.getPlayerHead(player.getName(), "§9Deine Statistiken§8: ", lore, 1);

        player.openInventory(inventory);

        inventory.setItem(10, item01);
        if (player.hasPermission("mclandslobby.admin")) {
            inventory.setItem(13, item02);
        }
        inventory.setItem(16, item03);
        inventory.setItem(0, item04);

    }
}
