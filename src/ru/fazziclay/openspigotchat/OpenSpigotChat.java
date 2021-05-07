package ru.fazziclay.openspigotchat;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.fazziclay.openspigotchat.listener.ChatListener;
import ru.fazziclay.openspigotchat.util.Debug;
import ru.fazziclay.openspigotchat.util.Utils;

public class OpenSpigotChat extends JavaPlugin {
    public static FileConfiguration pluginConfig;

    @Override
    public void onEnable() {
        try {
            loadConfig();
            loadChats();
            Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        } catch (Exception e) {
            Utils.print("Plugin starting error: " + e);
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        Utils.print("Started!");
    }

    @Override
    public void onDisable() {
        Utils.print("Plugin disabled!");
    }

    public void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
        pluginConfig = getConfig();
        Debug.debug("[Main] loadConfig(): loaded!");
    }

    public void loadChats() {
        Chat.chats.clear();

        for (int i = 0; i < Config.chatList.size(); i++) {
            String chatName = Config.chatList.get(i);
            String pattern = pluginConfig.getString("chat."+chatName+".pattern");
            String prefix = pluginConfig.getString("chat."+chatName+".prefix");
            if (prefix.equals("none")) {
                prefix = null;
            }
            int range = pluginConfig.getInt("chat."+chatName+".range");
            ChatMessageType messageType = ChatMessageType.valueOf(pluginConfig.getString("chat."+chatName+".messageType").toUpperCase());

            Chat chat = new Chat(chatName, pattern, prefix, range, messageType);
            Chat.chats.add(chat);
        }

        Debug.debug("[Main] loadChats(): loaded!");
    }
}
