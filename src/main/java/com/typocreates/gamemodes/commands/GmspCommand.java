package com.typocreates.gamemodes.commands;
import com.typocreates.gamemodes.data.GmLockData;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class GmspCommand implements CommandExecutor {
    private final GeneralUtil gu;
    private final GmLockData gmLockData;
    public GmspCommand(GeneralUtil gu, GmLockData gmLockData) {
        this.gu = gu;
        this.gmLockData = gmLockData;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender commandSender, @NonNull Command command, @NonNull String s, String[] strings) {
        String gamemode = "Spectator";

//        If there are no args, set players gamemode, if the commandSender isn't a player, send error.
        if (strings.length == 0) {
            if (commandSender instanceof Player player) {
                if (gmLockData.isLocked(player.getUniqueId())) {
                    gu.sendErrorMessage(player, gu.getGamemodeLockedMsg());
                    return true;
                }
                if (gu.isGamemodeBlocked(player, GameMode.SPECTATOR)) {
                    gu.sendErrorMessage(commandSender, gu.getGamemodeBlockedMsg(gamemode));
                    return true;
                }
                gu.sendMessage(player, gu.getTargetGamemodeChangeMsg(gamemode));
                player.setGameMode(GameMode.SPECTATOR);
                return true;
            }
            gu.sendMessage(commandSender, gu.getExecutorNotPlayerMsg());
            return true;
        }

//        If there is one arg, get the player & set their gamemode if the player exists
        if (strings.length == 1) {
            Player target = Bukkit.getServer().getPlayer(strings[0]);
            if (target == null) {
                gu.sendErrorMessage(commandSender, gu.getTargetNotFoundMsg());
                return true;
            }
            if (gmLockData.isLocked(target.getUniqueId())) {
                gu.sendErrorMessage(commandSender, gu.getGamemodeLockedMsg());
                return true;
            }
            if (gu.isGamemodeBlocked(target, GameMode.SPECTATOR)) {
                gu.sendErrorMessage(commandSender, gu.getGamemodeBlockedMsg(gamemode));
                return true;
            }
            target.setGameMode(GameMode.SPECTATOR);
            gu.sendMessage(commandSender, gu.getExecutorConfirmationMsg(gamemode, target.getName()));
            if (gu.sendTarget() && commandSender != target) {
                gu.sendMessage(target, gu.getTargetGamemodeChangeMsg(gamemode));
            }
            return true;
        }

//        If there is more than one arg, send error
        gu.sendErrorMessage(commandSender, gu.getTooManyArgsMsg());
        return true;
    }
}