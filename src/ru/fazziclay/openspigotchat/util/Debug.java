package ru.fazziclay.openspigotchat.util;

import ru.fazziclay.openspigotchat.Config;

public class Debug {
    public static void debug(String message) {
        if (Config.isDebugEnable) {
            Utils.print("§e[DEBUG] " + message);
        }
    }
}
