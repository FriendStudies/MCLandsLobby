package eu.jxstcolin.commands;

import eu.jxstcolin.main.MCLandsLobby;
import eu.jxstcolin.util.Config;
import eu.jxstcolin.util.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.Console;
import java.io.IOException;

public class MCLandsLobbyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof ConsoleCommandSender){

            commandSender.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du bist kein Spieler :(");

            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("mclandslobby.admin")){
            player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDazu bist du nicht berechtigt.");
            return true;
        }

        if (args.length == 1){
            if (args[0].equals("setspawn")) {

                MCLandsLobby.getInstance().getLocationManager().saveLocation("Spawn", player.getLocation());

                player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Der Spawn wurde gesetzt.");
            } else {
                player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Das ist kein gültiger Parameter.");
            }
        } else {
            player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Syntax: §9/mclandslobby [Syn: setspawn]");
        }

        return false;
    }
}
