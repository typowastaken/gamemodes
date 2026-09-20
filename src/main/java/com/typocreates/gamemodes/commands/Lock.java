package com.typocreates.gamemodes.commands;
import com.typocreates.gamemodes.data.GmLockData;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Lock implements CommandExecutor, TabCompleter {
    private final GeneralUtil gu;
    private final GmLockData gmLockData;
    public Lock(GeneralUtil gu, GmLockData gmLockData) {
        this.gu = gu;
        this.gmLockData = gmLockData;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender commandSender, @NonNull Command command, @NonNull String s, String[] strings) {
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
            if (gu.isGamemodeBlocked(target, GameMode.valueOf(gamemode.toUpperCase()))) {
                gu.sendErrorMessage(commandSender, "Unable to lock that users gamemode! They aren't allowed in that gamemode!");
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

    @Override
    public List<String> onTabComplete(@NonNull CommandSender commandSender, @NonNull Command command, @NonNull String s, String[] strings) {
        if (strings.length == 2) {
            List<String> gamemodes = new ArrayList<>();
            gamemodes.add("adventure");
            gamemodes.add("creative");
            gamemodes.add("survival");
            gamemodes.add("spectator");

            gamemodes.removeIf(gm -> !(gm.startsWith(strings[1].toLowerCase())));

            return gamemodes;
        }

        if (strings.length > 2) {
            return new ArrayList<>();
        }
        return null;
    }
}