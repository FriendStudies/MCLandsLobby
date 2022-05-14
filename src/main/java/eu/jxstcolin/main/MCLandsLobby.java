package eu.jxstcolin.main;

import eu.jxstcolin.commands.BuildCommand;
import eu.jxstcolin.commands.CoinsCommand;
import eu.jxstcolin.commands.MCLandsLobbyCommand;
import eu.jxstcolin.listener.PlayerListener;
import eu.jxstcolin.util.AdvancedLicense;
import eu.jxstcolin.util.Config;
import eu.jxstcolin.util.LocationManager;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.logging.Logger;

import static org.bukkit.Bukkit.getServer;

public class MCLandsLobby extends JavaPlugin {

    private static MCLandsLobby instance;

    LocationManager locationManager;

    public static ArrayList<String> buildPlayers = new ArrayList<>();

    public static boolean isCountdownRunning = false;

    public static String userName;

    String prefix;

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    @Override
    public void onEnable() {

        Config.setDefaults();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        instance = this;
        locationManager = new LocationManager();

        if(!new AdvancedLicense(Config.getString("LicenseSystem.License"), "http://45.142.114.159/license/verify.php", this).register()) return;

        prefix = Config.getString("Settings.Prefix");

        register();

        Bukkit.getConsoleSender().sendMessage("§aDas Plugin wurde aktiviert.");

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();

    }

    public void register(){
        getCommand("build").setExecutor(new BuildCommand());
        getCommand("mclandslobby").setExecutor(new MCLandsLobbyCommand());
        getCommand("coins").setExecutor(new CoinsCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§cDas Plugin wurde deaktiviert.");
    }

    public static MCLandsLobby getInstance() {
        return instance;
    }

    public String getPrefix() {
        return prefix;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Permission getPermissions() {
        return perms;
    }

    public static Chat getChat() {
        return chat;
    }
}
