package ru.fazziclay.openspigotchat.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Utils {
    public static void print(String message) {
        Bukkit.getLogger().info(message);
    }

    public static String fixMessage(String source) {
        return source.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static void splitMessage(Player sender, String message){
        String[] message_split = message.split("s");
    }
}
