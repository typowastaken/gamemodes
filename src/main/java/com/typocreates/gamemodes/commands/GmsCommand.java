package com.typocreates.gamemodes.commands;
import com.typocreates.gamemodes.files.GmLockData;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmsCommand implements CommandExecutor {
    private final GeneralUtil gu;
    private final GmLockData gmLockData;
    public GmsCommand(GeneralUtil gu, GmLockData gmLockData) {
        this.gu = gu;
        this.gmLockData = gmLockData;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String gamemode = "Survival";
        String targetGamemodeChangeMsg = gu.getTargetGamemodeChangeMsg().replace("{gm}", gamemode);
        String confirmationMsg = gu.getConfirmationMsg().replace("{gm}", gamemode);
        String senderNotPlayerMsg = gu.getSenderNotPlayerMsg();
        String playerNotFoundMsg = gu.getPlayerNotFoundMsg();
        String tooManyArgsMsg = gu.getTooManyArgsMsg();
        String unableToChangeGamemodeMsg = gu.getUnableToChangeGamemodeMsg();
        String gamemodeChangeNotAllowedMsg = gu.getGamemodeChangeNotAllowedMsg().replace("{gm}", gamemode);


//        If there are no args, set players gamemode, if the commandSender isn't a player, send error.
        if (strings.length == 0) {
            if (commandSender instanceof Player player) {
                if (gmLockData.isLocked(player.getUniqueId())) {
                    gu.sendErrorMessage(player, unableToChangeGamemodeMsg);
                    return true;
                }
                if (gu.isGamemodeBlocked(player, GameMode.SURVIVAL)) {
                    gu.sendErrorMessage(commandSender, gamemodeChangeNotAllowedMsg);
                    return true;
                }
                gu.sendMessage(player, targetGamemodeChangeMsg);
                player.setGameMode(GameMode.SURVIVAL);
                return true;
            }
            gu.sendMessage(commandSender, senderNotPlayerMsg);
            return true;
        }

//        If there is one arg, get the player & set their gamemode if the player exists
        if (strings.length == 1) {
            Player target = Bukkit.getServer().getPlayer(strings[0]);
            if (target == null) {
                gu.sendErrorMessage(commandSender, playerNotFoundMsg);
                return true;
            }
            if (gmLockData.isLocked(target.getUniqueId())) {
                gu.sendErrorMessage(commandSender, unableToChangeGamemodeMsg);
                return true;
            }
            if (gu.isGamemodeBlocked(target, GameMode.SURVIVAL)) {
                gu.sendErrorMessage(commandSender, gamemodeChangeNotAllowedMsg);
                return true;
            }
            target.setGameMode(GameMode.SURVIVAL);
            gu.sendMessage(commandSender, String.format(confirmationMsg, target.getName()));
            if (gu.sendTarget() && commandSender != target) {
                gu.sendMessage(target, targetGamemodeChangeMsg);
            }
            return true;
        }

//        If there is more than one arg, send error
        gu.sendErrorMessage(commandSender, tooManyArgsMsg);
        return true;
    }
}