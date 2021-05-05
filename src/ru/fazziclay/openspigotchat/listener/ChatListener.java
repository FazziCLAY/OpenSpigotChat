package ru.fazziclay.openspigotchat.listener;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.fazziclay.openspigotchat.util.ChatUtils;

public class ChatListener implements Listener {
    @EventHandler(
            priority = EventPriority.LOWEST,
            ignoreCancelled = true
    )
    public void onChat(AsyncPlayerChatEvent event) {
        String player_nickname = event.getPlayer().getName();
        String messageContent = event.getMessage();

        if (event.isCancelled()) {
            return;
        }
        TextComponent message = ChatUtils.convertToTextComponent("[{\"text\":\"[L]\",\"color\":\"aqua\"},{\"text\":\" \"},{\"text\":\"%player_nickname%\",\"clickEvent\":{\"action\":\"suggest_command\",\"value\":\"tell %player_nickname%\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":\"Click to send DM!\"}},{\"text\":\": %message%\"}]"
        .replace("%player_nickname%", player_nickname)
        .replace("%message%", messageContent));

        Bukkit.spigot().broadcast(message);
        event.setCancelled(true);
    }
}
