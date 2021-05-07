package ru.fazziclay.openspigotchat.util;

import ru.fazziclay.openspigotchat.Config;

public class DebugUtils {
    public static void Test() {
        String a = "1";
        a.startsWith(null);
    }

    public static void debug(String message) {
        if (Config.isDebugEnable) {
            Utils.print("§e[DEBUG] " + message);
        }
    }
}
