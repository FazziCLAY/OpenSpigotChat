package ru.fazziclay.openspigotchat;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.configuration.file.FileConfiguration;
import ru.fazziclay.openspigotchat.util.DebugUtils;

import java.util.List;

public class Config {
    public static FileConfiguration configFile;                 // Файл конфига
    public static boolean isDebugEnable = false;                 // Включён ли режим отладки
    public static String errorText1 = "[\"\",{\"translate\":\"chat.cannotSend\",\"color\":\"red\"},\"\\n\",{\"text\":\"OpenSpigotChat: Error to sending message: chat == null!\",\"color\":\"red\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://fazziclay.ru/\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":[{\"text\":\"FeedBack to plugin developer! https://fazziclay.ru/\",\"color\":\"gray\"}]}}]";

    public static List<String>  unregisterCommands;             // Команды которые должны быть удалены с сервера (CommandAPI)
    public static boolean       messageEnable;                  // Изменять ли сообщения входа/выхода
    public static String        messageJoinPlayer;              // Паттерн сообщения о входе
    public static String        messageLeavePlayer;             // Паттерн сообщения о выходе
    public static boolean       directMessageEnable;            // Включено ли изменение личных сообщений (CommandAPI)
    public static List<String>  directMessageCommands;          // Команды для изменения личных сообщений
    public static String        directMessageSenderPattern;     // Паттерн сообщения которое отправляеться отправителю сообщения
    public static String        directMessageRecipientPattern;  // Паттерн сообщения которое отправляеться получателю сообщения
    public static String        directMessagePlayerNotFound;    // Паттерн сообщения о том что игрок не онлайн
    public static List<String>  directMessageArgumentsName;     // Имена аргументов команды
    public static List<String>  chatList;                       // Список доступных чатов
    public static String        chatDefault;                    // Стандартный чат (Без префикса)

    public static void loadConfig() {
        DebugUtils.staticDebug("Config", "loadConfig()");

        // Load configFile
        OpenSpigotChat.getPlugin(OpenSpigotChat.class).getConfig().options().copyDefaults(true);
        OpenSpigotChat.getPlugin(OpenSpigotChat.class).saveConfig();
        configFile = OpenSpigotChat.getPlugin(OpenSpigotChat.class).getConfig();
        DebugUtils.staticDebug("Config", "loadConfig()", "file loaded!");

        // Load Config variables
        unregisterCommands = getStringList("unregisterCommands");
        messageEnable      = getBoolean("message.enable");
        messageJoinPlayer  = getString("message.joinPlayer");
        messageLeavePlayer = getString("message.leavePlayer");
        directMessageEnable           = getBoolean("directMessage.enable");
        directMessageCommands         = getStringList("directMessage.commands");
        directMessageSenderPattern    = getString("directMessage.senderPattern");
        directMessageRecipientPattern = getString("directMessage.recipientPattern");
        directMessagePlayerNotFound   = getString("directMessage.playerNotFound");
        directMessageArgumentsName    = getStringList("directMessage.argumentsName");
        chatList    = getStringList("chat.list");
        chatDefault = getString("chat.default");

        DebugUtils.staticDebug("Config", "loadConfig()", "variable loaded!");
    }

    public static void loadChats() {
        DebugUtils.staticDebug("Config", "loadChats()");

        Chat.chats.clear();
        for (String chatName : chatList) {
            DebugUtils.staticDebug("Config", "loadChats()", "loop: chatName="+chatName);

            String configPath = "chat."+chatName;

            ChatMessageType messageType = ChatMessageType.valueOf(getString(configPath+".messageType").toUpperCase());
            String pattern              = getString(configPath+".pattern");
            String prefix               = getString(configPath+".prefix");
            int range                   = getInt(configPath+".range");

            if (prefix.equals("none")) prefix = null;

            Chat chat = new Chat();
            chat.name = chatName;
            chat.messageType = messageType;
            chat.pattern = pattern;
            chat.prefix = prefix;
            chat.range = range;

            Chat.chats.add(chat);
        }

        DebugUtils.staticDebug("Config", "loadChats()", "loaded!");
    }

    public static String getString(String path) {
        return configFile.getString(path);
    }

    public static List<String> getStringList(String path) {
        return configFile.getStringList(path);
    }

    public static boolean getBoolean(String path) {
        return configFile.getBoolean(path);
    }

    public static int getInt(String path) {
        return configFile.getInt(path);
    }
}
