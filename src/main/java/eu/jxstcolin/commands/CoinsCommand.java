package eu.jxstcolin.commands;

import eu.jxstcolin.main.MCLandsLobby;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof ConsoleCommandSender){

            commandSender.sendMessage("§cDu bist kein Spieler. :(");

            return true;
        }

        Player player = (Player) commandSender;

        player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Deine Coins§8: §9" + MCLandsLobby.getEconomy().format(MCLandsLobby.getEconomy().getBalance(player.getName())));

        return false;
    }
}
