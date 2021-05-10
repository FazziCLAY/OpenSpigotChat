package ru.fazziclay.openspigotchat.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class Utils {
    public static void print(String message) {
        Bukkit.getLogger().info(message);
    }

    public static String fixMessage(String source) {
        return source.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static int getRandom(int minimum, int maximum) { // Получение случайного числа в диапозоне
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(maximum - minimum + 1) + minimum;
    }
}
