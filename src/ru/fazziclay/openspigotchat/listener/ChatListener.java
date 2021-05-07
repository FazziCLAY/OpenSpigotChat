package ru.fazziclay.openspigotchat.listener;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.fazziclay.openspigotchat.Chat;
import ru.fazziclay.openspigotchat.Config;
import ru.fazziclay.openspigotchat.util.ChatUtils;
import ru.fazziclay.openspigotchat.util.Debug;
import ru.fazziclay.openspigotchat.util.Utils;

import java.util.regex.Pattern;


public class ChatListener implements Listener {
    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onJoin(PlayerJoinEvent event) {
        String player_nickname = event.getPlayer().getName();
        String player_uuid = event.getPlayer().getUniqueId().toString();

        if (event.getJoinMessage() == null) {
            return;
        }

        BaseComponent message = ChatUtils.convertToTextComponent(Config.messageJoinPlayer
                .replace("%player_nickname%", player_nickname)
                .replace("%player_uuid%", player_uuid));

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.SYSTEM, message);
        }
        Utils.print(message.toLegacyText());
        event.setJoinMessage(null);
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onLeave(PlayerQuitEvent event) {
        String player_nickname = event.getPlayer().getName();
        String player_uuid = event.getPlayer().getUniqueId().toString();

        if (event.getQuitMessage() == null) {
            return;
        }

        BaseComponent message = ChatUtils.convertToTextComponent(Config.messageLeavePlayer
                .replace("%player_nickname%", player_nickname)
                .replace("%player_uuid%", player_uuid));

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.SYSTEM, message);
        }
        Utils.print(message.toLegacyText());
        event.setQuitMessage(null);
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onKick(PlayerKickEvent event) {
        String player_nickname = event.getPlayer().getName();
        String player_uuid = event.getPlayer().getUniqueId().toString();

        BaseComponent message = ChatUtils.convertToTextComponent(Config.messageKickPlayer
                .replace("%player_nickname%", player_nickname)
                .replace("%player_uuid%", player_uuid)
                .replace("%reason%", event.getReason()));

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.SYSTEM, message);
        }
        Utils.print(message.toLegacyText());
        event.setLeaveMessage(null);
    }

    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onChat(AsyncPlayerChatEvent event) {
        String player_nickname = event.getPlayer().getName();
        String player_uuid = event.getPlayer().getUniqueId().toString();
        String messageContent = event.getMessage();

        for (Chat chat : Chat.chats) {
            Debug.debug("onChat(...): for: chat.type=" + chat.type);
            if (chat.prefix == null) {
                Debug.debug("onChat(...): for: prefix==null: continue;");
                continue;
            }
            if (messageContent.startsWith(chat.prefix) && messageContent.length() > chat.prefix.length()) {
                Debug.debug("onChat(...): for: prefix detected. chat.prefix="+chat.prefix);
                chat.send(event.getPlayer(), chat.pattern
                        .replace("%player_nickname%", player_nickname)
                        .replace("%player_uuid%", player_uuid)
                        .replace("%message_content%", messageContent.replaceFirst(Pattern.quote(chat.prefix), "")),
                        chat.range,
                        chat.messageType);
                event.setCancelled(true);
                return;
            }
        }

        Chat defaultChat = Chat.getChatByType(Config.chatDefault);
        if (defaultChat != null) {
            defaultChat.send(event.getPlayer(), defaultChat.pattern
                    .replace("%player_nickname%", player_nickname)
                    .replace("%player_uuid%", player_uuid)
                    .replace("%message_content%", messageContent),
                    defaultChat.range,
                    defaultChat.messageType);

        } else {
            event.getPlayer().spigot().sendMessage(ChatMessageType.SYSTEM, new TextComponent("<Hardcoded text>: Error from OpenSpigotChat plugin. DefaultChat == null. Please contact server administrator or plugin developer https://fazziclay.ru/"));
        }
        event.setCancelled(true);
    }
}
