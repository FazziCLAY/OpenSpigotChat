package ru.fazziclay.openspigotchat.util;

import ru.fazziclay.openspigotchat.Config;

public class DebugUtils {
    public static void debug(String message) {
        if (Config.isDebugEnable) {
            Utils.print("§e[DEBUG] " + message);
        }
    }

    public static void staticDebug(String file, String function, String other_message) {
        String msg = "["+file+"]" + "("+function+")";
        if (other_message != null) {
            msg = msg + ": " + other_message;
        }
        debug(msg);
    }

    public static void staticDebug(String file, String function) {
        staticDebug(file, function, null);
    }

    public static void objectDebug(Object object, String function, String other_message) {
        String msg = "["+object.getClass().getName()+":"+object+"]" + "("+function+")";
        if (other_message != null) {
            msg = msg + ": " + other_message;
        }
        debug(msg);
    }

    public static void objectDebug(Object object, String function) {
        objectDebug(object, function, null);
    }

}
