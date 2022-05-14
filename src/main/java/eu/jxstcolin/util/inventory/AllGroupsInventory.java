package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AllGroupsInventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 54, "§8» §9Gruppe setzen");

        LuckPerms api = LuckPermsProvider.get();

        api.getGroupManager().getLoadedGroups().forEach(group -> {
            ItemStack g = ItemAPI.getItem(Material.BOOK_AND_QUILL, "§9" + group.getName(), null, 1);

            inventory.addItem(g);
        });

        player.openInventory(inventory);

    }

}
