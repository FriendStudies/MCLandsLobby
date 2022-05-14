package eu.jxstcolin.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationManager {

    private static File file = new File("plugins/MCLandsLobby", "locations.yml");
    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void saveLocation(String name, Location location) {
        cfg.set(name + ".world", location.getWorld().getName());
        cfg.set(name + ".x", Double.valueOf(location.getX()));
        cfg.set(name + ".y", Double.valueOf(location.getY()));
        cfg.set(name + ".z", Double.valueOf(location.getZ()));
        cfg.set(name + ".yaw", Float.valueOf(location.getYaw()));
        cfg.set(name + ".pitch", Float.valueOf(location.getPitch()));
        saveFile();
    }

    public Location getLocation(String name) {
        String world = cfg.getString(name + ".world");
        double x = cfg.getDouble(name + ".x");
        double y = cfg.getDouble(name + ".y");
        double z = cfg.getDouble(name + ".z");
        Location location = new Location(Bukkit.getWorld(world), x, y, z);
        location.setPitch((float)cfg.getDouble(name + ".pitch"));
        location.setYaw((float)cfg.getDouble(name + ".yaw"));
        return location;
    }

    public void saveFile() {
        try {
            cfg.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
