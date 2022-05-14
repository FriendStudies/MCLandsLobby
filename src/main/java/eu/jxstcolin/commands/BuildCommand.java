package eu.jxstcolin.commands;

import eu.jxstcolin.main.MCLandsLobby;
import eu.jxstcolin.util.items.JoinItems;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BuildCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof ConsoleCommandSender){
            commandSender.sendMessage("§cDas darfst du nicht!");
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("mclandslobby.build")){

            player.sendMessage("§cDas darfst du nicht!");

            return true;
        }

        if (args.length == 0) {
            if (MCLandsLobby.buildPlayers.contains(player.getName())) {

                MCLandsLobby.buildPlayers.remove(player.getName());

                player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du kannst nun nicht mehr bauen.");

                player.setGameMode(GameMode.ADVENTURE);

                JoinItems.giveItems(player);

            } else {
                MCLandsLobby.buildPlayers.add(player.getName());

                player.setGameMode(GameMode.CREATIVE);
                player.getInventory().clear();

                player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du kannst nun bauen.");

            }

            return false;
        } if (args.length == 1) {
            if (player.hasPermission("mclandslobby.build.others")){

                Player target = (Player) Bukkit.getPlayer(args[0]);

                if (MCLandsLobby.buildPlayers.contains(target.getName())) {

                    MCLandsLobby.buildPlayers.remove(target.getName());

                    player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Der Spieler §9" + target.getName() + " §7kann nun nicht mehr bauen.");
                    target.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du kannst nun nicht mehr bauen.");

                    target.setGameMode(GameMode.ADVENTURE);

                    JoinItems.giveItems(target);

                } else {
                    MCLandsLobby.buildPlayers.add(target.getName());

                    player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Der Spieler §9" + target.getName() + " §7kann nun bauen.");
                    target.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du kannst nun bauen.");

                    target.setGameMode(GameMode.CREATIVE);
                    target.getInventory().clear();
                }

                return false;
            }
        } else {
            if (MCLandsLobby.buildPlayers.contains(player.getName())) {

                MCLandsLobby.buildPlayers.remove(player.getName());

                player.setGameMode(GameMode.ADVENTURE);

                JoinItems.giveItems(player);

                player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du kannst nun nicht mehr bauen.");
            } else {
                MCLandsLobby.buildPlayers.add(player.getName());

                player.setGameMode(GameMode.CREATIVE);
                player.getInventory().clear();

                player.sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du kannst nun bauen.");
            }

            return false;
        }

        return false;
    }
}
