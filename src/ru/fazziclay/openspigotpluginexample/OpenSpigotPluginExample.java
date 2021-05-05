package ru.fazziclay.openspigotpluginexample;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.fazziclay.openspigotpluginexample.util.Debug;
import ru.fazziclay.openspigotpluginexample.util.Utils;

public class OpenSpigotPluginExample extends JavaPlugin {
    public static FileConfiguration pluginConfig;

    @Override
    public void onEnable() {
        try {
            loadConfig();

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
