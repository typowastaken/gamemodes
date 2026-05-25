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
    String targetGamemodeChangeMsg = "Your gamemode has been set to {gm}.";
    String confirmationMsg = "%s's gamemode has been set to {gm}.";
    String senderNotPlayerMsg = "You either have to be a player or target a player to use this command.";
    String playerNotFoundMsg = "That player could not be found, maybe they went offline?";
    String tooManyArgsMsg = "You can only have a maximum of 1 argument for this command.";
    String unableToChangeGamemodeMsg = "Unable to change that users gamemode! Their gamemode is currently locked!";
    String gamemodeChangeNotAllowedMsg = "That user isn't allowed in {gm}!";

    public String getTargetGamemodeChangeMsg(String gm) {
        return targetGamemodeChangeMsg.replace("{gm}", gm);
    }

    public String getConfirmationMsg(String gm) {
        return confirmationMsg.replace("{gm}", gm);
    }

    public String getSenderNotPlayerMsg() {
        return senderNotPlayerMsg;
    }

    public String getPlayerNotFoundMsg() {
        return playerNotFoundMsg;
    }

    public String getTooManyArgsMsg() {
        return tooManyArgsMsg;
    }

    public String getUnableToChangeGamemodeMsg() {
        return unableToChangeGamemodeMsg;
    }

    public String getGamemodeChangeNotAllowedMsg(String gm) {
        return gamemodeChangeNotAllowedMsg.replace("{gm}", gm);
    }
}
