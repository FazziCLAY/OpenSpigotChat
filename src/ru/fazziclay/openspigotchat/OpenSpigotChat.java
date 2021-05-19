package ru.fazziclay.openspigotchat;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import ru.fazziclay.openspigotchat.listener.ChatListener;

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
            Bukkit.getLogger().info("Plugin starting error: " + e);
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
        Bukkit.getPluginManager().addPermission(permission);
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

        CommandAPI.unregister("bubbledebug", true);
        new CommandAPICommand("bubbledebug")
                .withPermission(CommandPermission.OP)
                .withSubcommand(new CommandAPICommand("spawnTextLine")
                        .withArguments(new EntitySelectorArgument("entity"), new GreedyStringArgument("message"))
                        .executes((sender, args) -> {
                            Entity p = (Entity) args[0];
                            String message = ((String) args[1]).replace("\\n", "\n");

                            ChatBubbles.spawnTextLine(p, message, 10*20);
                        })
                )
                .withSubcommand(new CommandAPICommand("spawnTextScreen")
                        .withArguments(new EntitySelectorArgument("entity"), new GreedyStringArgument("message"))
                        .executes((sender, args) -> {
                            Entity p = (Entity) args[0];
                            String message = ((String) args[1]).replace("\\n", "\n");

                            ChatBubbles.spawnTextScreen(p, message, 10*20);
                        })
                )
                .register();
    }
}
