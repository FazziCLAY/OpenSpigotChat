package ru.fazziclay.openspigotchat.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.json.JSONArray;
import org.json.JSONObject;

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
        BaseComponent[] __a = ComponentSerializer.parse(source);
        BaseComponent __b = new TextComponent();
        for (BaseComponent c : __a) {
            __b.addExtra(c);
        }

        return __b;
        ////////// end /////////
        /*
        TextComponent message__ = new TextComponent();
        JSONArray jsonComponent = null;
        try {
            jsonComponent = new JSONArray(source);

        } catch (Exception e) {
            try {
                JSONObject a = new JSONObject(source);
                jsonComponent = new JSONArray();
                jsonComponent.put(a);

            } catch (Exception e2) {
                jsonComponent = new JSONArray();
                jsonComponent.put(source);
            }
        }


        for (int i = 0; i < jsonComponent.length(); i++) {
            Object currentObject = jsonComponent.get(i);

            JSONObject currentJsonComponent = null;

            if (currentObject instanceof String) {
                message__.addExtra((String) currentObject);
                continue;

            } else if (currentObject instanceof JSONObject) {
                currentJsonComponent = (JSONObject) currentObject;

            } else {
                continue;
            }

            // JSONComponent
            String text             = currentJsonComponent.optString("text", "<none_text>");
            boolean bold            = currentJsonComponent.optBoolean("bold", false);
            boolean italic          = currentJsonComponent.optBoolean("italic", false);
            boolean strikethrough   = currentJsonComponent.optBoolean("strikethrough", false);
            boolean underlined      = currentJsonComponent.optBoolean("underlined", false);
            boolean obfuscated      = currentJsonComponent.optBoolean("obfuscated", false);
            String color            = currentJsonComponent.optString("color", null);
            ClickEvent clickEvent;
            HoverEvent hoverEvent;

            // ClickEvent
            try {
                JSONObject clickEventJson = currentJsonComponent.optJSONObject("clickEvent");
                String clickEventActionString = clickEventJson.optString("action", null);
                String clickEventValueString = clickEventJson.optString("value", null);

                if (clickEventActionString != null && clickEventValueString != null) {
                    clickEvent = new ClickEvent(ClickEvent.Action.valueOf(clickEventActionString.toUpperCase()), clickEventValueString);

                } else {
                    clickEvent = null;
                }

            } catch (Exception e) {
                clickEvent = null;
            }

            // HoverEvent
            try {
                JSONObject hoverEventJson = currentJsonComponent.optJSONObject("hoverEvent");
                String hoverEventActionString = hoverEventJson.getString("action");
                Object hoverEventContentObject = hoverEventJson.get("contents");

                HoverEvent.Action hoverEventAction = HoverEvent.Action.valueOf(hoverEventActionString.toUpperCase());
                Text hoverEventContent = null;

                if (hoverEventContentObject instanceof JSONObject) {
                    hoverEventContent = new Text("< Unsupported hover event. Contact the plugin developer. https://fazziclay.ru/ >");

                } else if (hoverEventContentObject instanceof String) {
                    hoverEventContent = new Text((String) hoverEventContentObject);
                }

                hoverEvent = new HoverEvent(hoverEventAction, hoverEventContent);

            } catch (Exception e) {
                hoverEvent = null;
            }

            // TextComponent
            TextComponent extra = new TextComponent();
            extra.setText(text);
            extra.setBold(bold);
            extra.setItalic(italic);
            extra.setStrikethrough(strikethrough);
            extra.setUnderlined(underlined);
            extra.setObfuscated(obfuscated);
            if (color != null) {
                extra.setColor(ChatColor.of(color.toUpperCase()));
            }
            extra.setClickEvent(clickEvent);
            extra.setHoverEvent(hoverEvent);

            message__.addExtra(extra);
        }

        return message__; */
    }
}
