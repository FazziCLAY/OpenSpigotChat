package ru.fazziclay.openspigotchat;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.fazziclay.openspigotchat.util.ChatUtils;

public class CommandsExecutor {
    public static void onDirectMessageCommand(CommandSender sender, Object[] args) {
        // Args
        Player recipientPlayer = (Player) args[0];
        String message = (String) args[1];

        // Variables
        String sender_nickname = sender.getName();
        String recipient_nickname = recipientPlayer.getName();

        // Message to recipient
        BaseComponent recipientMessage = ChatUtils.convertToTextComponent(Config.directMessageRecipientPattern
                .replace("%sender_nickname%", sender_nickname)
                .replace("%recipient_nickname%", recipient_nickname)
                .replace("%message_content%", message)
        );

        // Message to sender
        BaseComponent senderMessage = ChatUtils.convertToTextComponent(Config.directMessageSenderPattern
                .replace("%sender_nickname%", sender_nickname)
                .replace("%recipient_nickname%", recipient_nickname)
                .replace("%message_content%", message)
        );

        // Message playerNotFound
        BaseComponent playerNotFoundMessage = ChatUtils.convertToTextComponent(Config.directMessagePlayerNotFound
                .replace("%sender_nickname%", sender_nickname)
                .replace("%recipient_nickname%", recipient_nickname)
                .replace("%message_content%", message)
        );

        if (sender instanceof Player) {
            Player senderPlayer = (Player) sender;
            if (!recipientPlayer.isOnline()) {
                senderPlayer.spigot().sendMessage(ChatMessageType.SYSTEM, playerNotFoundMessage);
                return;
            }

            recipientPlayer.spigot().sendMessage(ChatMessageType.CHAT, senderPlayer.getUniqueId(), recipientMessage);
            senderPlayer.spigot().sendMessage(ChatMessageType.SYSTEM, senderMessage);
        } else {
            if (!recipientPlayer.isOnline()) {
                sender.sendMessage(playerNotFoundMessage.toLegacyText());
                return;
            }
            recipientPlayer.spigot().sendMessage(ChatMessageType.SYSTEM, recipientMessage);
            sender.sendMessage(senderMessage.toLegacyText());
        }
    }
}
