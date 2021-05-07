package ru.fazziclay.openspigotchat;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.CommandPermission;
import dev.jorel.commandapi.arguments.EntitySelectorArgument;
import dev.jorel.commandapi.arguments.GreedyStringArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import ru.fazziclay.openspigotchat.listener.ChatListener;
import ru.fazziclay.openspigotchat.util.DebugUtils;
import ru.fazziclay.openspigotchat.util.TextBubblesUtils;
import ru.fazziclay.openspigotchat.util.Utils;

public class OpenSpigotChat extends JavaPlugin {
    public static FileConfiguration pluginConfig;

    @Override
    public void onEnable() {
        try {
            loadConfig();
            loadChats();
            loadPermissions();
            loadCommands();
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
        DebugUtils.debug("[Main] loadConfig(): loaded!");
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

        DebugUtils.debug("[Main] loadChats(): loaded!");
    }

    public void loadPermissions() {
        Bukkit.getPluginManager().addPermission(new Permission("openspigotchat.direct_message"));

        for (Chat chat : Chat.chats) {
            // TODO delete prefix DEV to permission
            Bukkit.getPluginManager().addPermission(new Permission("DEV.openspigotchat.chat."+chat.type));
        }
    }

    public void loadCommands() {
        for (String commandName : Config.unregisterCommands) {
            CommandAPI.unregister(commandName, true);
        }


        for (String commandName : Config.directMessageCommands) {
            CommandAPI.unregister(commandName, true);
            new CommandAPICommand(commandName)
                    .withArguments(new PlayerArgument(Config.directMessageArgumentsName.get(0)), new GreedyStringArgument(Config.directMessageArgumentsName.get(1)))
                    .withPermission("openspigotchat.direct_message")
                    .executes(CommandsExecutor::onDirectMessageCommand)
                    .register();
        }

        new CommandAPICommand("debug1")
                .withPermission(CommandPermission.OP)
                .withSubcommand(new CommandAPICommand("TextBubblesUtils")
                        .withSubcommand(new CommandAPICommand("spawn")
                                .withArguments(
                                    new EntitySelectorArgument("a"),
                                    new GreedyStringArgument("text")
                                ).executes((sender, args) -> {
                                    AreaEffectCloud a = TextBubblesUtils.spawnTextBubble((Entity) args[0], (String) args[1], 30);
                                    sender.sendMessage(a.getUniqueId().toString());
                                })
                        )

                        .withSubcommand(new CommandAPICommand("kill")
                                .withArguments(new EntitySelectorArgument("a"))
                                .executes((sender, args) -> {
                                    TextBubblesUtils.killTextBubble((AreaEffectCloud) args[0]);
                                })
                        )
                )
                .register();
    }
}
