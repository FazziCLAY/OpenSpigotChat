package ru.fazziclay.openspigotchat.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler(
            priority = EventPriority.LOWEST,
            ignoreCancelled = true
    )
    public void onChat(AsyncPlayerChatEvent event) {
        event.setMessage(event.getMessage().replace("&", "0000000000000000000000"));
    }
}
