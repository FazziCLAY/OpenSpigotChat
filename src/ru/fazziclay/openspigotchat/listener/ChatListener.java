package ru.fazziclay.openspigotchat.listener;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import ru.fazziclay.openspigotchat.Chat;
import ru.fazziclay.openspigotchat.Config;
import ru.fazziclay.openspigotchat.util.ChatUtils;
import ru.fazziclay.openspigotchat.util.Utils;


public class ChatListener implements Listener {
    @EventHandler(
            priority = EventPriority.HIGHEST
    )
    public void onJoin(PlayerJoinEvent event) {
        if (!(Config.messageEnable) || event.getJoinMessage() == null) {
            return;
        }
        String player_nickname = event.getPlayer().getName();
        String player_uuid = event.getPlayer().getUniqueId().toString();

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
        if (!(Config.messageEnable) || event.getQuitMessage() == null) {
            return;
        }
        String player_nickname = event.getPlayer().getName();
        String player_uuid = event.getPlayer().getUniqueId().toString();

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
    public void onChat(AsyncPlayerChatEvent event) {
        String messageContent = event.getMessage();
        Chat chat = Chat.getChatByName(Config.chatDefault);

        for (Chat currentChat : Chat.chats) {
            if (currentChat.prefix != null && messageContent.startsWith(currentChat.prefix) && messageContent.length() > currentChat.prefix.length()) {
                chat = currentChat;
            }
        }

        Chat.sendMessage(chat, event.getPlayer(), messageContent);
        event.setCancelled(true);
    }
}
