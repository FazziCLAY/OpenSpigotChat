package ru.fazziclay.openspigotchat.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Content;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.json.JSONArray;
import org.json.JSONObject;

public class ChatUtils {
    public static TextComponent convertToTextComponent(String source) {
        TextComponent message = new TextComponent();
        JSONArray jsonComponent = new JSONArray(source);

        for (int i = 0; i < jsonComponent.length(); i++) {
            // JSONComponent
            JSONObject currentJsonComponent = jsonComponent.getJSONObject(i);
            String text             = currentJsonComponent.optString("text", "<none_text>");
            boolean bold            = currentJsonComponent.optBoolean("bold", false);
            boolean italic          = currentJsonComponent.optBoolean("italic", false);
            boolean strikethrough   = currentJsonComponent.optBoolean("strikethrough", false);
            boolean underlined      = currentJsonComponent.optBoolean("underlined", false);
            boolean obfuscated      = currentJsonComponent.optBoolean("obfuscated", false);
            String color            = currentJsonComponent.optString("color", null);
            ClickEvent clickEvent = new ClickEvent(ClickEvent.Action.valueOf("suggest_command".toUpperCase()), "/tell Notch hello!");
            HoverEvent hoverEvent;

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
            extra.setColor(ChatColor.of(color));
            extra.setClickEvent(clickEvent);
            extra.setHoverEvent(hoverEvent);

            message.addExtra(extra);
        }

        return message;
    }
}
