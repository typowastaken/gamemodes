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

    // Gamemode change strings
    public String getTargetGamemodeChangeMsg(String gm) {
        return "Your gamemode has been set to {gm}.".replace("{gm}", gm);
    }

    public String getConfirmationMsg(String gm) {
        return "%s's gamemode has been set to {gm}.".replace("{gm}", gm);
    }

    public String getSenderNotPlayerMsg() {
        return "You either have to be a player or target a player to use this command.";
    }

    public String getPlayerNotFoundMsg() {
        return "That player could not be found, maybe they went offline?";
    }

    public String getTooManyArgsMsg() {
        return "You can only have a maximum of 1 argument for this command.";
    }

    public String getUnableToChangeGamemodeMsg() {
        return "Unable to change that users gamemode! Their gamemode is currently locked!";
    }

    public String getGamemodeChangeNotAllowedMsg(String gm) {
        return "That user isn't allowed in {gm}!".replace("{gm}", gm);
    }
}
