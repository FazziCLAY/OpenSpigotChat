package ru.fazziclay.openspigotchat;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatBubbles {
    // --- Queue --- (start)
    public static HashMap<String, List<String>> queue = new HashMap<>();

    public static void createQueue(String nickname) { // Создать пустую очередь для игрока
        queue.put(nickname, new ArrayList<>());
    }

    public static void deleteQueue(String nickname) { // Удалить очередь у игрока
        queue.remove(nickname);
    }

    public static void addMessageToQueue(String nickname, String message) { // Добавить сообщение в очередь к игроку
        queue.get(nickname).add(message);
    }

    public static void removeMessageInQueue(String nickname, String message) { // Удалить сообщение из очереди игрока
        queue.get(nickname).remove(message);
    }

    public static boolean isQueueExist(String nickname) { // Есть ли у игрока очередь
        return queue.containsKey(nickname);
    }

    public static boolean isQueueEmpty(String nickname) { // Пустая ли очередь у игрока
        return queue.get(nickname).isEmpty();
    }
    // --- Queue --- (end)

    public static void onMessage(Player sender, String message) {
        int MAX_LENGTH = 15; // Максимальная длинна в символах
        // привет привет а
        int MAX_HEIGHT = 4;  // Максимальная высота в строках
        String[] message_split = message.split(" ");
        for (int i=0; i < message_split.length; i++) {
            if (message_split[i].length() <= MAX_LENGTH) {
                if (isQueueExist(sender.getName())) {
                    addMessageToQueue(sender.getName(), message);
                }
                else
                {
                    createQueue(sender.getName());
                    spawnTextLine(sender, message_split[i], 100 * 20);

                    OpenSpigotChat.getPlugin(OpenSpigotChat.class).getServer().getScheduler().scheduleAsyncRepeatingTask(OpenSpigotChat.getPlugin(OpenSpigotChat.class), new Runnable() {
                        public void run() {

                        }
                    }, 1200, 1200);
                }
            }
        }
    }

    public static void spawnTextLine(Entity sender, String message, int duration) {
        Location spawnLocation = sender.getLocation();
        spawnLocation.setY(1024);
        World spawnWorld = sender.getWorld();

        AreaEffectCloud text = (AreaEffectCloud) spawnWorld.spawnEntity(spawnLocation, EntityType.AREA_EFFECT_CLOUD);
        text.setRadius(0);
        text.setDuration(duration);
        text.setCustomName(message);
        text.setCustomNameVisible(true);
        sender.addPassenger(text);
    }
}
