package ru.fazziclay.openspigotchat;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class Config {
    // Const
    public static boolean isDebugEnable = true;

    // Config
    public static FileConfiguration a = OpenSpigotChat.pluginConfig;

    public static List<String> unregisterCommands = a.getStringList("unregisterCommands");

    // message
    public static String messageJoinPlayer = a.getString("message.joinPlayer");
    public static String messageLeavePlayer = a.getString("message.leavePlayer");

    //directMessage
    public static List<String> directMessageCommands = a.getStringList("directMessage.commands");
    public static String directMessageSenderPattern = a.getString("directMessage.senderPattern");
    public static String directMessageRecipientPattern = a.getString("directMessage.recipientPattern");
    public static String directMessagePlayerNotFound = a.getString("directMessage.playerNotFound");
    public static List<String> directMessageArgumentsName = a.getStringList("directMessage.argumentsName");

    // chat
    public static List<String> chatList = a.getStringList("chat.list");
    public static String chatDefault= a.getString("chat.default");
}
