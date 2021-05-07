package ru.fazziclay.openspigotchat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
    // Const
    public static boolean isDebugEnable = true;

    // Config
    public static FileConfiguration a = OpenSpigotChat.pluginConfig;

    public static String messageJoinPlayer = a.getString("message.joinPlayer");
    public static String messageLeavePlayer = a.getString("message.leavePlayer");
    public static String messageKickPlayer = a.getString("message.kickPlayer");

    public static List<String> chatList = a.getStringList("chat.list");
    public static String chatDefault= a.getString("chat.default");
}
