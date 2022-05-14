package eu.jxstcolin.util.inventory;

import eu.jxstcolin.api.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;

public class ShopInventory {

    public static void openInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(null, 54, "§8» §9Shop");

        ItemStack stained_glass = ItemAPI.getItem(Material.STAINED_GLASS_PANE, "§8 ", null, 1, 7);

        for(int i = 0; i < inventory.getSize(); i++){
            inventory.setItem(i, stained_glass);
        }

        ArrayList<String> premiumLore = new ArrayList<>();
        premiumLore.add("§8-----------------");
        premiumLore.add("§7Rang§8: §6Premium");
        premiumLore.add("§7Preis§8: §910.000$");
        premiumLore.add("§8-----------------");

        ArrayList<String> ultraLore = new ArrayList<>();
        ultraLore.add("§8-----------------");
        ultraLore.add("§7Rang§8: §aUltra");
        ultraLore.add("§7Preis§8: §925.000$");
        ultraLore.add("§8-----------------");

        ArrayList<String> proLore = new ArrayList<>();
        proLore.add("§8-----------------");
        proLore.add("§7Rang§8: §bPro");
        proLore.add("§7Preis§8: §950.000$");
        proLore.add("§8-----------------");

        ArrayList<String> ultimateLore = new ArrayList<>();
        ultimateLore.add("§8-----------------");
        ultimateLore.add("§7Rang§8: §bUltimate");
        ultimateLore.add("§7Preis§8: §975.000$");
        ultimateLore.add("§8-----------------");

        ArrayList<String> eliteLore = new ArrayList<>();
        eliteLore.add("§8-----------------");
        eliteLore.add("§7Rang§8: §5Elite");
        eliteLore.add("§7Preis§8: §9150.000$");
        eliteLore.add("§8-----------------");

        ArrayList<String> zeusLore = new ArrayList<>();
        zeusLore.add("§8-----------------");
        zeusLore.add("§7Rang§8: §5Zeus");
        zeusLore.add("§7Preis§8: §9300.000$");
        zeusLore.add("§8-----------------");

        ArrayList<String> supremeLore = new ArrayList<>();
        supremeLore.add("§8-----------------");
        supremeLore.add("§7Rang§8: §dSupreme");
        supremeLore.add("§7Preis§8: §9500.000$");
        supremeLore.add("§8-----------------");

        ArrayList<String> masterLore = new ArrayList<>();
        masterLore.add("§8-----------------");
        masterLore.add("§7Rang§8: §3Master");
        masterLore.add("§7Preis§8: §91.000.000$");
        masterLore.add("§8-----------------");

        ItemStack item01 = ItemAPI.getItem(Material.CACTUS, "§5Elite", eliteLore, 1);
        ItemStack item02 = ItemAPI.getItem(Material.TNT, "§6Premium", premiumLore, 1);
        ItemStack item03 = ItemAPI.getItem(Material.ENCHANTMENT_TABLE, "§5Zeus", zeusLore, 1);
        ItemStack item04 = ItemAPI.getItem(Material.ENDER_CHEST, "§aUltra", ultraLore, 1);
        ItemStack item05 = ItemAPI.getItem(Material.BEACON, "§dSupreme", supremeLore, 1);
        ItemStack item06 = ItemAPI.getItem(Material.GOLD_BLOCK, "§bPro", proLore, 1);
        ItemStack item07 = ItemAPI.getItem(Material.PUMPKIN, "§3Master", masterLore, 1);
        ItemStack item08 = ItemAPI.getItem(Material.BOOKSHELF, "§bUltimate", ultimateLore, 1);

        ItemStack item09 = ItemAPI.getItem(Material.STAINED_GLASS, "§aZurück", null, 1, 5);
        ItemStack item10 = ItemAPI.getItem(Material.STAINED_GLASS, "§cVerlassen", null, 1, 14);

        inventory.setItem(11, item01);
        inventory.setItem(13, item03);
        inventory.setItem(15, item05);
        inventory.setItem(17, item07);
        inventory.setItem(38, item02);
        inventory.setItem(40, item04);
        inventory.setItem(42, item06);
        inventory.setItem(44, item08);

        inventory.setItem(18, item09);
        inventory.setItem(27, item10);

        player.openInventory(inventory);

    }

}
