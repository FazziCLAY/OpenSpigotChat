package ru.fazziclay.openspigotchat;

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
}
