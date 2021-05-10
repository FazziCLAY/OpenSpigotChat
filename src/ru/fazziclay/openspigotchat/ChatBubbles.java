package ru.fazziclay.openspigotchat;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ru.fazziclay.openspigotchat.debug.Debugger;

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

    public static String getFirstMessage(String nickname) { // Создать пустую очередь для игрока
        return queue.get(nickname).get(0);
    }
    // --- Queue --- (end)

    public static void onMessage(Player sender, String message) {
        Debugger debugger = new Debugger("ChatBubbles", "onMessage", "sender(name)="+sender.getName()+", message="+message);

        new BukkitRunnable() {
            @Override
            public void run() {
                debugger.log("BukkitRunnable.run()");
                int MAX_LENGTH = 15; // Максимальная длинна в символах
                int MAX_HEIGHT = 4;  // Максимальная высота в строках

                String msg = "";

                String[] words = message.split(" ");
                for (int i = 0; i < words.length; i++) {
                    for (int o = 0; o< 4; o++) {
                        msg = "";
                    }
                }

                // to send
                if (isQueueExist(sender.getName())) {
                    addMessageToQueue(sender.getName(), message);

                } else {
                    int duration = 100 * 20;
                    createQueue(sender.getName());
                    spawnTextLine(sender, msg, duration);

                    OpenSpigotChat.getPlugin(OpenSpigotChat.class).getServer().getScheduler().scheduleAsyncRepeatingTask(OpenSpigotChat.getPlugin(OpenSpigotChat.class), new Runnable() {
                        public void run() {
                            if (isQueueEmpty(sender.getName())) {
                                deleteQueue(sender.getName());

                            } else {
                                String a = getFirstMessage(sender.getName());
                                onMessage(sender, a);
                                removeMessageInQueue(sender.getName(), a);
                            }
                        }
                    }, duration, duration);
                }
            }
        }.runTaskLater(OpenSpigotChat.getPlugin(OpenSpigotChat.class), 0);
    }

    public static void spawnTextScreen(Entity sender, String message, int duration) {
        String[] lines = message.split("\n");

        Entity vehicle = sender;
        for (int i = lines.length-1; i > 0; i--) {
            String str = lines[i];
            vehicle = spawnTextLine(vehicle, str, duration);
        }
    }

    public static AreaEffectCloud spawnTextLine(Entity sender, String message, int duration) {
        Debugger debugger = new Debugger("ChatBubbles", "spawnTextLine", "sender(name)="+sender.getName()+", message="+message+", duration="+duration);

        Location spawnLocation = sender.getLocation();
        spawnLocation.setY(1024);
        World spawnWorld = sender.getWorld();

        AreaEffectCloud text = (AreaEffectCloud) spawnWorld.spawnEntity(spawnLocation, EntityType.AREA_EFFECT_CLOUD);
        text.setRadius(0);
        text.setDuration(duration);
        text.setCustomName(message);
        text.setCustomNameVisible(true);
        sender.addPassenger(text);
        return text;
    }
}
