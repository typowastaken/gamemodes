package com.typocreates.gamemodes.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GeneralUtil {
    public void sendMessage(Player player, String message) {
        player.playSound(player, "minecraft:entity.experience_orb.pickup", 1, 1 );
        player.sendMessage(ChatColor.YELLOW + message);
    }
}
