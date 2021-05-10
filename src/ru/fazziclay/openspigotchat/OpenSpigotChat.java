package ru.fazziclay.openspigotchat;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import ru.fazziclay.openspigotchat.listener.ChatListener;
import ru.fazziclay.openspigotchat.util.DebugUtils;
import ru.fazziclay.openspigotchat.util.Utils;

public class OpenSpigotChat extends JavaPlugin {
    @Override
    public void onEnable() {
        try {
            Config.loadConfig();
            Config.loadChats();
            loadPermissions();
            loadCommands();
            Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        } catch (Exception e) {
            Utils.print("Plugin starting error: " + e);
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("Started!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled!");
    }

    public void loadPermissions() {
        Permission permission = new Permission("openspigotchat.direct_message");
        permission.setDefault(PermissionDefault.TRUE);
        permission.setDescription("Use direct message command");
        Bukkit.getPluginManager().addPermission(permission);
        DebugUtils.objectDebug(this, "loadPermissions()", "loaded!");
    }

    public void loadCommands() {
        for (String commandName : Config.unregisterCommands) {
            CommandAPI.unregister(commandName, true);
        }

        if (Config.directMessageEnable) {
            for (String commandName : Config.directMessageCommands) {
                CommandAPI.unregister(commandName, true);
                new CommandAPICommand(commandName)
                        .withArguments(new PlayerArgument(Config.directMessageArgumentsName.get(0)), new GreedyStringArgument(Config.directMessageArgumentsName.get(1)))
                        .withPermission("openspigotchat.direct_message")
                        .executes(CommandsExecutor::onDirectMessageCommand)
                        .register();
            }
        }

        new CommandAPICommand("bubbledebug")
                .withSubcommand(new CommandAPICommand("spawn_text_bubble")
                        .withArguments(new PlayerArgument("ot_kogo_spawnit"), new GreedyStringArgument("message"))
                        .executes((sender, args) -> {
                            Player p = (Player) args[0];
                            String  message = (String) args[1];

                            ChatBubbles.spawnTextLine(p, message, 10*20);
                        })
                )
                .register();
        DebugUtils.objectDebug(this, "loadCommands()", "loaded!");
    }
}
