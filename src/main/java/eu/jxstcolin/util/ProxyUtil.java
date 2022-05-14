package eu.jxstcolin.util;

import eu.jxstcolin.main.MCLandsLobby;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class ProxyUtil {

    public static void sendPlayerToServer(Player player, String serverName){

        ByteArrayOutputStream b = new ByteArrayOutputStream();

        DataOutputStream out= new DataOutputStream(b);

        try{

            out.writeUTF("Connect");

            out.writeUTF(serverName);

        } catch (Exception e) {

            e.printStackTrace();

        }

        player.sendPluginMessage(MCLandsLobby.getInstance(), "BungeeCord", b.toByteArray());

    }
}
