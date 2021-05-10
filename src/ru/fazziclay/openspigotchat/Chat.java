package ru.fazziclay.openspigotchat;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import ru.fazziclay.openspigotchat.debug.Debugger;
import ru.fazziclay.openspigotchat.util.ChatUtils;
import ru.fazziclay.openspigotchat.util.DebugUtils;
import ru.fazziclay.openspigotchat.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    // Static
    public static List<Chat> chats = new ArrayList<>(); // Обьекты чатов из конфига

    public static Chat getChatByName(String type) {
        Debugger debugger = new Debugger("Chat", "getChatByName", "type="+type);

        for (Chat chat : chats) {
            debugger.log("for(chat.name="+chat.name+")");
            if (chat.name.equals(type)) {
                debugger.log("for(chat.name="+chat.name+"): returned");
                return chat;
            }
        }

        return null;
    }

    public static void sendMessage(Chat chat, Player sender, String message) {
        if (sender == null) {
            return;
        }
        if (chat == null || message == null) {
            sender.spigot().sendMessage(ChatMessageType.SYSTEM, ChatUtils.convertToTextComponent(Config.errorText1));
            return;
        }
        String jsonMessage = chat.pattern
                    .replace("%sender_name%", sender.getName())
                    .replace("%sender_uuid%", sender.getUniqueId().toString())
                    .replace("%message_content%", Utils.fixMessage(message));

        chat.send(sender, jsonMessage);
        //ChatBubbles.onMessage(sender, message);
    }

    // Object
    public String name;                 // Имя чата
    public ChatMessageType messageType; // Тип сообщений отправленных в этот чат
    public String pattern;              // Паттерн сообщения отправляемых в это чат
    public String prefix;               // Префикс что бы написать в этот чат
    public int range;                   // Дистанция действия этого чата

    public Chat() {}

    public void send(Player sender, String pattern) {
        BaseComponent message = ChatUtils.convertToTextComponent(pattern);
        List<Player> recipients;

        if (this.range > 0) {
            recipients = ChatUtils.getLocalRecipients(sender, this.range);
        } else {
            recipients = new ArrayList<>(Bukkit.getOnlinePlayers());
        }

        for (Player recipient : recipients) {
            recipient.spigot().sendMessage(this.messageType, sender.getUniqueId(), message);
        }

        Utils.print(message.toLegacyText());
    }
}
