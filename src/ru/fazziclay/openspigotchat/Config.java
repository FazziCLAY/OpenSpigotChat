package ru.fazziclay.openspigotchat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
    // Const
    public static boolean isDebugEnable = true;

    // Config
    public static FileConfiguration a = OpenSpigotChat.pluginConfig;

    public static List<String> chatList = a.getStringList("chat.list");
    public static String chatDefault= a.getString("chat.default");
}
