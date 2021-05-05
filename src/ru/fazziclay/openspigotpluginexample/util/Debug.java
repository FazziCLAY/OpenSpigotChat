package ru.fazziclay.openspigotpluginexample.util;

import ru.fazziclay.openspigotpluginexample.Config;

public class Debug {
    public static void debug(String message) {
        if (Config.isDebugEnable) {
            Utils.print("§e[DEBUG] " + message);
        }
    }
}
