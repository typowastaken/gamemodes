package com.typocreates.gamemodes.commands;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmspCommand implements CommandExecutor {
    private final GeneralUtil gu;
    public GmspCommand(GeneralUtil gu) {
        this.gu = gu;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String targetGamemodeChangeMessage = "Your gamemode has been set to Spectator.";
        String confirmationMessage = "%s's gamemode has been set to Spectator.";
        String senderNotPlayerMessage = "You either have to be a player or target a player to use this command.";
        String playerNotFoundMessage = "That player could not be found, maybe they went offline?";
        String tooManyArgsMessage = "You can only have a maximum of 1 argument for this command.";


//        If there are no args, set players gamemode, if the commandSender isn't a player, send error.
        if (strings.length == 0) {
            if (commandSender instanceof Player player) {
                gu.sendMessage(player, targetGamemodeChangeMessage);
                player.setGameMode(GameMode.SPECTATOR);
                return true;
            }
            gu.sendMessage(commandSender, senderNotPlayerMessage);
            return true;
        }

//        If there is one arg, get the player & set their gamemode if the player exists
        if (strings.length == 1) {
            Player target = Bukkit.getServer().getPlayer(strings[0]);
            if (target == null) {
                gu.sendErrorMessage(commandSender, playerNotFoundMessage);
                return true;
            }
            target.setGameMode(GameMode.SPECTATOR);
            gu.sendMessage(commandSender, String.format(confirmationMessage, target.getName()));
            gu.sendMessage(target, targetGamemodeChangeMessage);
            return true;
        }

//        If there is more than one arg, send error
        gu.sendErrorMessage(commandSender, tooManyArgsMessage);
        return true;
    }
}