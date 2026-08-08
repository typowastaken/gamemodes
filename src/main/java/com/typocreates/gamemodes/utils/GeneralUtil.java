package com.typocreates.gamemodes.utils;

import com.typocreates.gamemodes.Gamemodes;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GeneralUtil {
    private final Gamemodes plugin;
    public GeneralUtil(Gamemodes plugin) {
        this.plugin = plugin;
    }



    public void sendMessage(CommandSender commandSender, String message) {
        boolean doSounds = plugin.getConfig().getBoolean("do-sound-effects");
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
        boolean doSounds = plugin.getConfig().getBoolean("do-sound-effects");
        if (commandSender instanceof Player player) {
            if (doSounds) {
                player.playSound(player, "minecraft:entity.experience_orb.pickup", 1, 1 );
            }
            player.sendMessage(ChatColor.RED + message);
        } else {
            commandSender.sendMessage(message);
        }
    }

    public boolean isGamemodeBlocked(Player p, GameMode gm) {
        return p.hasPermission("gamemodes.blockaccess." + gm.toString().toLowerCase());
    }

    public boolean sendTarget() {
        return plugin.getConfig().getBoolean("send-target-message", true);
    }

    public boolean isUpdateCheckEnabled() { return plugin.getConfig().getBoolean("update-checker", true); }

    // Gamemode change strings
    public String getTargetGamemodeChangeMsg(String gamemode) {
        return plugin.getConfig()
                .getString("messages.target-gamemode-change", "Your gamemode has been set to {gamemode}.")
                .replace("{gamemode}", gamemode);
    }

    public String getExecutorConfirmationMsg(String gamemode, String targetPlayer) {
        return plugin.getConfig()
                .getString("messages.executor-confirmation", "{player}'s gamemode has been set to {gamemode}.")
                .replace("{gamemode}", gamemode)
                .replace("{player}", targetPlayer);
    }

    public String getExecutorNotPlayerMsg() {
        return plugin.getConfig()
                .getString("messages.executor-not-player", "You have to be a player or target a player to use this command.");
    }

    public String getTargetNotFoundMsg() {
        return plugin.getConfig()
                .getString("messages.target-not-found", "That player could not be found, maybe they went offline?");
    }

    public String getTooManyArgsMsg() {
        return plugin.getConfig()
                .getString("messages.too-many-args", "You can only have a maximum of 1 argument for this command.");
    }

    public String getGamemodeLockedMsg() {
        return plugin.getConfig()
                .getString("messages.gamemode-locked", "Unable to change that users gamemode! Their gamemode is currently locked!");
    }

    public String getGamemodeBlockedMsg(String gamemode) {
        return plugin.getConfig()
                .getString("messages.gamemode-blocked", "That user isn't allowed in {gamemode}!")
                .replace("{gamemode}", gamemode);
    }
}
