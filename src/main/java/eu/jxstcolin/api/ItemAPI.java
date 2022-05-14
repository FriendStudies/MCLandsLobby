package eu.jxstcolin.api;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.Skull;

import java.util.ArrayList;

public class ItemAPI {

    public static ItemStack getItem(Material material, String displayName, ArrayList<String> lore, int amount){

        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setAmount(amount);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack getItem(Material material, String displayName, ArrayList<String> lore, int amount, int subType){

        ItemStack itemStack = new ItemStack(material, amount, (byte) subType);

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

    public static ItemStack getPlayerHead(String playerOfHeadName, String displayName, ArrayList<String> lore, int amount){
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (short) SkullType.PLAYER.ordinal());
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner(playerOfHeadName);
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        skull.setItemMeta(meta);

        return skull;
    }
}
