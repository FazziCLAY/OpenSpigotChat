package ru.fazziclay.openspigotchat.util;

import org.bukkit.Bukkit;

public class Utils {
    public static void print(String message) {
        Bukkit.getLogger().info(message);
    }

    public static String fixMessage(String source) {
        return source.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
