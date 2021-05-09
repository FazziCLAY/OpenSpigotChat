package ru.fazziclay.openspigotchat.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChatUtils {
    public static List<Player> getLocalRecipients(Player sender, int range) {
        List<Player> recipients = new ArrayList<>();

        Location playerLocation = sender.getLocation();
        Iterator<Player> worldPlayers = sender.getWorld().getPlayers().iterator();
        double squaredDistance = Math.pow(range, 2.0D);

        while(true) {
            Player recipient;
            do {
                if (!worldPlayers.hasNext()) {
                    return recipients;
                }
                recipient = worldPlayers.next();

            } while(playerLocation.distanceSquared(recipient.getLocation()) > squaredDistance);

            recipients.add(recipient);
        }
    }

    public static BaseComponent convertToTextComponent(String source) {
        BaseComponent[] components = ComponentSerializer.parse(source);
        BaseComponent outputComponent = new TextComponent();
        outputComponent.setColor(ChatColor.RESET);
        for (BaseComponent component : components) {
            outputComponent.addExtra(component);
        }

        return outputComponent;
    }
}
