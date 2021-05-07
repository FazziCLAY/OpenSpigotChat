package ru.fazziclay.openspigotchat;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.fazziclay.openspigotchat.util.ChatUtils;
import ru.fazziclay.openspigotchat.util.DebugUtils;
import ru.fazziclay.openspigotchat.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    // Static
    public static List<Chat> chats = new ArrayList<>();

    public static Chat getChatByType(String type1) {
        for (Chat chat : chats) {
            DebugUtils.debug("getChatByType(type=" + type1 + "): for: chat.type=" + chat.type);
            if (chat.type.equals(type1)) {
                DebugUtils.debug("getChatByType(type=" + type1 + "): returned");
                return chat;
            }
        }

        return null;
    }

    // Object
    public String type;
    public ChatMessageType messageType;
    public String pattern;
    public String prefix;
    public int range;


    public Chat(String type, String pattern, String prefix, int range, ChatMessageType messageType) {
        this.type = type;
        this.pattern = pattern;
        this.prefix = prefix;
        this.range = range;
        this.messageType = messageType;
    }

    public void send(Player sender, String pattern, int range, ChatMessageType messageType) {
        DebugUtils.debug("Chat<object>.send(player=-, pattern="+pattern+", range="+range+", messageType=-");
        BaseComponent message = ChatUtils.convertToTextComponent(pattern);
        List<Player> recipients;

        if (range > 0) {
            recipients = ChatUtils.getLocalRecipients(sender, range);
        } else {
            recipients = new ArrayList<>(Bukkit.getOnlinePlayers());
        }

        for (Player recipient : recipients) {
            recipient.spigot().sendMessage(messageType, sender.getUniqueId(), message);
        }

        Utils.print(message.toLegacyText());
    }

    public void sendBubbles(Player sender, String message) {

    }
}
