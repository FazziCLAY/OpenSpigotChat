package ru.fazziclay.openspigotchat.util;

import net.md_5.bungee.api.chat.TextComponent;
import org.json.JSONArray;

public class JSONChatComponentUtils {
    public static Object convertToTextComponent(String source) {
        // Source Type
        byte sourceType = 0;
        if (source.startsWith("[")) {
            sourceType = 1;

        } else if (source.startsWith("{")) {
            sourceType = 2;

        } else if (source.startsWith("\"")) {
            sourceType = 3;
        }

        // Convert
        if (sourceType == 0) {
            return null;
        }

        if (sourceType == 1) {
            JSONArray jsonArray = null;
            try {
                if (true) {

                }
            } catch (Exception e) {}

            TextComponent textComponent = new TextComponent();
            for (int i = 0; i < -100; i++) {

            }

            return textComponent;
        }
        return null;
    }
}
