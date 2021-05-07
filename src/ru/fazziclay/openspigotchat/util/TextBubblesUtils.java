package ru.fazziclay.openspigotchat.util;

import org.bukkit.Particle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class TextBubblesUtils {
    public static AreaEffectCloud spawnTextBubble(Entity a, String text, int duration) {
        AreaEffectCloud textEntity = (AreaEffectCloud) a.getWorld().spawnEntity(a.getLocation(), EntityType.AREA_EFFECT_CLOUD);
        textEntity.setParticle(Particle.TOWN_AURA);
        textEntity.setRadius(0.0F);
        textEntity.setCustomName(text);
        textEntity.setCustomNameVisible(true);
        textEntity.setWaitTime(0);
        textEntity.setDuration(duration);
        a.addPassenger(textEntity);
        return textEntity;
    }

    public static void killTextBubble(AreaEffectCloud a) {
        a.remove();
    }
}
