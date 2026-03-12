package com.typocreates.gamemodes.utils;

import com.typocreates.gamemodes.Gamemodes;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GeneralUtil {
    Gamemodes plugin = Gamemodes.getPlugin();
    boolean doSounds = plugin.getConfig().getBoolean("do-sound-effects");

    public void sendMessage(CommandSender commandSender, String message) {
        if (commandSender instanceof Player player) {
            if (doSounds) {
                player.playSound(player, "minecraft:entity.experience_orb.pickup", 1, 1 );
            }
            player.sendMessage(ChatColor.YELLOW + message);
        } else {
            commandSender.sendMessage(message);
        }

    }

    public void sendErrorMessage(CommandSender commandSender, String message) {
        if (commandSender instanceof Player player) {
            if (doSounds) {
                player.playSound(player, "minecraft:entity.experience_orb.pickup", 1, 1 );
            }
            player.sendMessage(ChatColor.RED + message);
        } else {
            commandSender.sendMessage(message);
        }
    }
}
