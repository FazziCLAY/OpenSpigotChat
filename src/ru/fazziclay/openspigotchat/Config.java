package ru.fazziclay.openspigotchat;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    // Const
    public static boolean isDebugEnable = true;

    // Config
    public static FileConfiguration a = OpenSpigotChat.pluginConfig;

    public static String testMessage = a.getString("test.message");
}
