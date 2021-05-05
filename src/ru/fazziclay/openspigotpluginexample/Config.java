package ru.fazziclay.openspigotpluginexample;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    // Const
    public static boolean isDebugEnable = true;

    // Config
    public static FileConfiguration a = OpenSpigotPluginExample.pluginConfig;

    public static String testMessage = a.getString("test.message");
}
