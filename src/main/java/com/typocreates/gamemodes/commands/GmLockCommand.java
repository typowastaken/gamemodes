package com.typocreates.gamemodes.commands;
import com.typocreates.gamemodes.Gamemodes;
import com.typocreates.gamemodes.files.GmLockData;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.List;

public class GmLockCommand implements CommandExecutor {
    private final Gamemodes plugin;
    private final GeneralUtil gu;
    private final GmLockData gmLockData;
    public GmLockCommand(Gamemodes plugin, GeneralUtil gu, GmLockData gmLockData) {
        this.plugin = plugin;
        this.gu = gu;
        this.gmLockData = gmLockData;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            gu.sendErrorMessage(commandSender, "You must supply a username and a gamemode.");
            return true;
        }
        if (strings.length == 1) {
            gu.sendErrorMessage(commandSender, "You must supply a gamemode.");
            return true;
        }
        if (strings.length == 2) {
            Player target = Bukkit.getPlayer(strings[0]);
            if (target == null) {
                gu.sendErrorMessage(commandSender, "That player could not be found, maybe they went offline?");
                return true;
            }

            List<String> gamemodes = List.of("ADVENTURE", "CREATIVE", "SURVIVAL", "SPECTATOR");
            String gamemode = strings[1];
            if (!gamemodes.contains(gamemode.toUpperCase())) {
                gu.sendErrorMessage(commandSender, "The gamemode '" + gamemode + "' doesn't exist.");
                return true;
            }

            if (gmLockData.get().getString(target.getUniqueId().toString()) != null) {
                gmLockData.get().set(target.getUniqueId().toString(), null);
            }
            target.setGameMode(GameMode.valueOf(gamemode.toUpperCase()));
            gmLockData.get().set(target.getUniqueId().toString(), "true");
            gmLockData.save();
            gu.sendMessage(commandSender, String.format("%s's gamemode has been locked to %s.", target.getName(), gamemode));
            return true;
        }
        return true;
    }
}