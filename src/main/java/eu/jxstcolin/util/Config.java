package eu.jxstcolin.util;


import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private static File file = new File("plugins/MCLandsLobby", "config.yml");

    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setDefaults() {
        cfg.options().copyDefaults(true);
        cfg.addDefault("Settings.Prefix", "&8[&9MCLands&8] &7");
        cfg.addDefault("Settings.StopCountdownInSeconds", 15);
        cfg.addDefault("LicenseSystem.License", "XXXX-XXXX-XXXX-XXXX");
        saveFile();
    }

    public static String getString(String path) {
        return cfg.getString(path).replaceAll("&", "§");
    }

    public static Integer getInt(String path) {
        return Integer.valueOf(cfg.getInt(path));
    }

    public static Double getDouble(String path) {
        return Double.valueOf(cfg.getDouble(path));
    }

    public static Boolean getBoolean(String path) {
        return Boolean.valueOf(cfg.getBoolean(path));
    }

    public static void setCustom(String path, Object o) {
        cfg.set(path, o);
    }

    public static void saveFile() {
        try {
            cfg.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
