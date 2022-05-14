package eu.jxstcolin.listener;

import eu.jxstcolin.api.ItemAPI;
import eu.jxstcolin.commands.MCLandsLobbyCommand;
import eu.jxstcolin.main.MCLandsLobby;
import eu.jxstcolin.util.Config;
import eu.jxstcolin.util.ProxyUtil;
import eu.jxstcolin.util.inventory.*;
import eu.jxstcolin.util.items.JoinItems;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Objects;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event){

        event.getPlayer().setGameMode(GameMode.ADVENTURE);

        JoinItems.giveItems(event.getPlayer());

        event.setJoinMessage(MCLandsLobby.getInstance().getPrefix() + "§7Der Spieler §9" + event.getPlayer().getName() + " §7hat die Lobby §abetreten§8.");

        event.getPlayer().teleport(MCLandsLobby.getInstance().getLocationManager().getLocation("Spawn"));

    }

    @EventHandler
    public void onExplodeBlock(BlockExplodeEvent event){
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExplodeEntity(EntityExplodeEvent event){
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event){
        event.setQuitMessage(MCLandsLobby.getInstance().getPrefix() + "§7Der Spieler §9" + event.getPlayer().getName() + " §7hat die Lobby §cverlassen§8.");

        MCLandsLobby.buildPlayers.remove(event.getPlayer().getName());

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPickup(InventoryPickupItemEvent event){
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBow(EntityShootBowEvent event){
        if (!MCLandsLobby.buildPlayers.contains(event.getEntity().getName())){
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBuild(BlockPlaceEvent event) {
        if (!MCLandsLobby.buildPlayers.contains(event.getPlayer().getName())) {

            event.setCancelled(true);

            event.getPlayer().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Das darfst du hier nicht!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent event) {
        if (!MCLandsLobby.buildPlayers.contains(event.getPlayer().getName())) {

            event.setCancelled(true);

            event.getPlayer().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Das darfst du hier nicht!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryDrop(InventoryDragEvent event) {
        if (!MCLandsLobby.buildPlayers.contains(event.getWhoClicked().getName())) {

            event.setCancelled(true);

            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Das darfst du hier nicht!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!MCLandsLobby.buildPlayers.contains(event.getWhoClicked().getName())) {

            event.setCancelled(true);

            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Das darfst du hier nicht!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!MCLandsLobby.buildPlayers.contains(event.getWhoClicked().getName())) {

            event.setCancelled(true);

            if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Navigator")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Spawn")) {

                    event.getWhoClicked().teleport(MCLandsLobby.getInstance().getLocationManager().getLocation("Spawn"));

                    event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du wurdest zur Position §9SPAWN §7teleportiert.");
                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9CityBuild")) {
                    event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Verbindung wird hergestellt..");
                    ProxyUtil.sendPlayerToServer((Player) event.getWhoClicked(), "CityBuild-1");
                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cIn Entwicklung")) {
                    event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDieser Modus ist in Wartungsarbeiten.");
                }
            } if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Dein Profil")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Server Verwalten")) {

                    ServerVerwaltenInventory.openInventory((Player) event.getWhoClicked());

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Settings")) {

                    SettingsInventory.openInventory((Player) event.getWhoClicked());

                }
            } if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Server Verwalten")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Server Stoppen")) {

                    if (!MCLandsLobby.isCountdownRunning) {

                        MCLandsLobby.isCountdownRunning = true;

                        event.getWhoClicked().closeInventory();

                        MCLandsLobby.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(MCLandsLobby.getInstance(), new Runnable() {

                            int c = Config.getInt("Settings.StopCountdownInSeconds");

                            public void run() {
                                if (c == 0) {
                                    MCLandsLobby.isCountdownRunning = false;
                                    c = Config.getInt("Settings.StopCountdownInSeconds");
                                    MCLandsLobby.getInstance().getServer().getScheduler().cancelTasks(MCLandsLobby.getInstance());
                                    Bukkit.shutdown();
                                } else {
                                    Bukkit.broadcastMessage(MCLandsLobby.getInstance().getPrefix() + "§7Der Server startet in §9" + c + " §9Sekunden §7neu§8.");
                                    c--;
                                }
                            }
                        }, 0, 20);

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cEs läuft bereits ein Countdown.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aCountdown abbrechen")) {

                    if (MCLandsLobby.isCountdownRunning) {

                        Bukkit.broadcastMessage(MCLandsLobby.getInstance().getPrefix() + "§7Der Countdown wurde abgebrochen.");

                        MCLandsLobby.getInstance().getServer().getScheduler().cancelTasks(MCLandsLobby.getInstance());

                        MCLandsLobby.isCountdownRunning = false;

                        event.getWhoClicked().closeInventory();

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Es läuft aktuell kein Countdown.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Spieler")) {
                    AllPlayersInventory.openInventory((Player) event.getWhoClicked());
                }
            } if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Spieler")) {
                String name = event.getCurrentItem().getItemMeta().getDisplayName().replace("§9", "");
                event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast §9" + name + " §7ausgewählt.");

                MCLandsLobby.userName = name;

                AllPlayers02Inventory.openInventory((Player) event.getWhoClicked());

            } if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Action-Changer")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Gruppe setzen")){
                    AllGroupsInventory.openInventory((Player) event.getWhoClicked());
                }
            } if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Gruppe setzen")) {
                String gName = event.getCurrentItem().getItemMeta().getDisplayName().replace("§9", "");

                event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Die Gruppe §c" + gName + " §7wurde gesetzt.");

                LuckPerms api = LuckPermsProvider.get();

                User user = api.getUserManager().getUser(MCLandsLobby.userName);

                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

                Bukkit.dispatchCommand(console, "lp user " + MCLandsLobby.userName + " parent set " + gName);

                MCLandsLobby.userName = null;

                event.getWhoClicked().closeInventory();
            } if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Einstellungen")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Spieler-Zeit")){
                    PlayerTimeInventory.openInventory((Player) event.getWhoClicked());
                }
            } if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Spieler Zeit")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eTag")){
                    Player player = (Player) event.getWhoClicked();
                    player.setPlayerTime(0, true);
                    event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Deine Zeit wurde auf §eTag §7geändert.");
                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Nacht")){
                    Player player = (Player) event.getWhoClicked();
                    player.setPlayerTime(14000, true);
                    event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Deine Zeit wurde auf §9Nacht §7geändert.");
                }
            } if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Dein Profil")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9Ingame-Shop")) {

                    ShopInventory.openInventory((Player) event.getWhoClicked());

                }

            }

            if (event.getInventory().getTitle().equalsIgnoreCase("§8» §9Shop")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aZurück")) {

                    ProfileInventory.openInventory((Player) event.getWhoClicked());

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cVerlassen")) {

                    event.getWhoClicked().closeInventory();

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Premium")) {

                    LuckPerms api = LuckPermsProvider.get();

                    User user = api.getUserManager().getUser(event.getWhoClicked().getName());

                    if (!user.getPrimaryGroup().equalsIgnoreCase("premium")) {

                        if (MCLandsLobby.getEconomy().getBalance(event.getWhoClicked().getName()) >= 10000) {

                            MCLandsLobby.getEconomy().withdrawPlayer(event.getWhoClicked().getName(), 10000);

                            ConsoleCommandSender console = Bukkit.getConsoleSender();

                            Bukkit.dispatchCommand(console, "lp user " + event.getWhoClicked().getName() + " parent set " + "premium");

                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast den Rang §6Premium §7gekauft.");
                        } else {
                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDu hast nicht genügend Geld auf dem Konto.");
                        }

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDiesen Rang besitzt du bereits.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aUltra")) {

                    LuckPerms api = LuckPermsProvider.get();

                    User user = api.getUserManager().getUser(event.getWhoClicked().getName());

                    if (!user.getPrimaryGroup().equalsIgnoreCase("ultra")) {

                        if (MCLandsLobby.getEconomy().getBalance(event.getWhoClicked().getName()) >= 25000) {

                            MCLandsLobby.getEconomy().withdrawPlayer(event.getWhoClicked().getName(), 25000);

                            ConsoleCommandSender console = Bukkit.getConsoleSender();

                            Bukkit.dispatchCommand(console, "lp user " + event.getWhoClicked().getName() + " parent set " + "ultra");

                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast den Rang §aUltra §7gekauft.");
                        } else {
                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDu hast nicht genügend Geld auf dem Konto.");
                        }

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDiesen Rang besitzt du bereits.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bPro")) {

                    LuckPerms api = LuckPermsProvider.get();

                    User user = api.getUserManager().getUser(event.getWhoClicked().getName());

                    if (!user.getPrimaryGroup().equalsIgnoreCase("pro")) {

                        if (MCLandsLobby.getEconomy().getBalance(event.getWhoClicked().getName()) >= 50000) {

                            MCLandsLobby.getEconomy().withdrawPlayer(event.getWhoClicked().getName(), 50000);

                            ConsoleCommandSender console = Bukkit.getConsoleSender();

                            Bukkit.dispatchCommand(console, "lp user " + event.getWhoClicked().getName() + " parent set " + "pro");

                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast den Rang §bPro §7gekauft.");
                        } else {
                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDu hast nicht genügend Geld auf dem Konto.");
                        }

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDiesen Rang besitzt du bereits.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bUltimate")) {

                    LuckPerms api = LuckPermsProvider.get();

                    User user = api.getUserManager().getUser(event.getWhoClicked().getName());

                    if (!user.getPrimaryGroup().equalsIgnoreCase("ultimate")) {

                        if (MCLandsLobby.getEconomy().getBalance(event.getWhoClicked().getName()) >= 75000) {

                            MCLandsLobby.getEconomy().withdrawPlayer(event.getWhoClicked().getName(), 75000);

                            ConsoleCommandSender console = Bukkit.getConsoleSender();

                            Bukkit.dispatchCommand(console, "lp user " + event.getWhoClicked().getName() + " parent set " + "ultimate");

                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast den Rang §bUltimate §7gekauft.");
                        } else {
                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDu hast nicht genügend Geld auf dem Konto.");
                        }

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDiesen Rang besitzt du bereits.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Elite")) {

                    LuckPerms api = LuckPermsProvider.get();

                    User user = api.getUserManager().getUser(event.getWhoClicked().getName());

                    if (!user.getPrimaryGroup().equalsIgnoreCase("elite")) {

                        if (MCLandsLobby.getEconomy().getBalance(event.getWhoClicked().getName()) >= 150000) {

                            MCLandsLobby.getEconomy().withdrawPlayer(event.getWhoClicked().getName(), 150000);

                            ConsoleCommandSender console = Bukkit.getConsoleSender();

                            Bukkit.dispatchCommand(console, "lp user " + event.getWhoClicked().getName() + " parent set " + "elite");

                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast den Rang §5Elite §7gekauft.");
                        } else {
                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDu hast nicht genügend Geld auf dem Konto.");
                        }

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDiesen Rang besitzt du bereits.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Zeus")) {

                    LuckPerms api = LuckPermsProvider.get();

                    User user = api.getUserManager().getUser(event.getWhoClicked().getName());

                    if (!user.getPrimaryGroup().equalsIgnoreCase("zeus")) {

                        if (MCLandsLobby.getEconomy().getBalance(event.getWhoClicked().getName()) >= 300000) {

                            MCLandsLobby.getEconomy().withdrawPlayer(event.getWhoClicked().getName(), 300000);

                            ConsoleCommandSender console = Bukkit.getConsoleSender();

                            Bukkit.dispatchCommand(console, "lp user " + event.getWhoClicked().getName() + " parent set " + "zeus");

                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast den Rang §5Zeus §7gekauft.");
                        } else {
                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDu hast nicht genügend Geld auf dem Konto.");
                        }

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDiesen Rang besitzt du bereits.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§dSupreme")) {

                    LuckPerms api = LuckPermsProvider.get();

                    User user = api.getUserManager().getUser(event.getWhoClicked().getName());

                    if (!user.getPrimaryGroup().equalsIgnoreCase("supreme")) {

                        if (MCLandsLobby.getEconomy().getBalance(event.getWhoClicked().getName()) >= 500000) {

                            MCLandsLobby.getEconomy().withdrawPlayer(event.getWhoClicked().getName(), 500000);

                            ConsoleCommandSender console = Bukkit.getConsoleSender();

                            Bukkit.dispatchCommand(console, "lp user " + event.getWhoClicked().getName() + " parent set " + "supreme");

                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast den Rang §dSupreme §7gekauft.");
                        } else {
                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDu hast nicht genügend Geld auf dem Konto.");
                        }

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDiesen Rang besitzt du bereits.");
                    }

                } if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3Master")) {

                    LuckPerms api = LuckPermsProvider.get();

                    User user = api.getUserManager().getUser(event.getWhoClicked().getName());

                    if (!user.getPrimaryGroup().equalsIgnoreCase("master")) {

                        if (MCLandsLobby.getEconomy().getBalance(event.getWhoClicked().getName()) >= 1000000) {

                            MCLandsLobby.getEconomy().withdrawPlayer(event.getWhoClicked().getName(), 1000000);

                            ConsoleCommandSender console = Bukkit.getConsoleSender();

                            Bukkit.dispatchCommand(console, "lp user " + event.getWhoClicked().getName() + " parent set " + "master");

                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du hast den Rang §3Master §7gekauft.");
                        } else {
                            event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDu hast nicht genügend Geld auf dem Konto.");
                        }

                    } else {
                        event.getWhoClicked().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§cDiesen Rang besitzt du bereits.");
                    }

                }

            }

        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onItemInteract(PlayerInteractEvent event) {
        if (!MCLandsLobby.buildPlayers.contains(event.getPlayer().getName())) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR){
                if (event.getItem().getType() == Material.SKULL_ITEM && event.getItem().getItemMeta().getDisplayName().equals("§9Dein Profil")) {
                    ProfileInventory.openInventory(event.getPlayer());
                } if (event.getItem().getType() == Material.BLAZE_ROD && event.getItem().getItemMeta().getDisplayName().equals("§cSpieler verstecken")) {
                    ItemStack spielerAnzeigenItem = ItemAPI.getItem(Material.BLAZE_ROD, "§aSpieler anzeigen", null, 1);

                    event.getPlayer().getInventory().setItem(2, spielerAnzeigenItem);

                    event.getPlayer().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du siehst nun alle Spieler wieder.");

                    for (Player players : Bukkit.getOnlinePlayers()){
                        event.getPlayer().hidePlayer(players);
                    }
                } if (event.getItem().getType() == Material.BLAZE_ROD && event.getItem().getItemMeta().getDisplayName().equals("§aSpieler anzeigen")) {
                    ItemStack spielerVersteckenItem = ItemAPI.getItem(Material.BLAZE_ROD, "§cSpieler verstecken", null, 1);

                    event.getPlayer().getInventory().setItem(2, spielerVersteckenItem);

                    event.getPlayer().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Du siehst nun keinen Spieler mehr.");

                    for (Player players : Bukkit.getOnlinePlayers()){
                        event.getPlayer().showPlayer(players);
                    }
                } if (event.getItem().getType() == Material.COMPASS && event.getItem().getItemMeta().getDisplayName().equals("§9Navigator")) {

                    NavigatorInventory.openInventory(event.getPlayer());

                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryDrop(PlayerDropItemEvent event) {
        if (!MCLandsLobby.buildPlayers.contains(event.getPlayer().getName())) {

            event.setCancelled(true);

            event.getPlayer().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§7Das darfst du hier nicht!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        if (!MCLandsLobby.buildPlayers.contains(event.getEntity().getName())) {

            event.setCancelled(true);

        }
    }

    private static Vector getRandomDirection() {
        Vector direction = new Vector();
        direction.setY(Math.random());
        return direction;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(PlayerMoveEvent event){

         int y = event.getPlayer().getLocation().getBlock().getY();

         if(event.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.EMERALD_BLOCK) {
            event.getPlayer().teleport(MCLandsLobby.getInstance().getLocationManager().getLocation("Spawn"));
            event.getPlayer().sendMessage(MCLandsLobby.getInstance().getPrefix() + "§aDu hast das Jump and Run geschafft.");
         }
    }
}
