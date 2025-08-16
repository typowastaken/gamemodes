package com.typocreates.gamemodes.commands;

import com.google.common.base.CaseFormat;
import com.typocreates.gamemodes.Gamemodes;
import com.typocreates.gamemodes.files.GmLockData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmLockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                p.sendMessage(ChatColor.RED + "You must supply a username and a gamemode.");
            } else {
                Gamemodes.getPlugin().getLogger().info(ChatColor.RED + "You must supply a username and a gamemode.");
            }
        } else if (strings.length == 1) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                p.sendMessage(ChatColor.RED + "You must also supply a gamemode.");
            } else {
                Gamemodes.getPlugin().getLogger().info(ChatColor.RED + "You must also supply a gamemode.");
            }
        } else if (strings.length == 2) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                String playerName = strings[0];
                Player target = Bukkit.getServer().getPlayerExact(playerName);
                String gamemode = strings[1];
                if (target != null) {
                    if (gamemode.equalsIgnoreCase("adventure")) {
                        target.setGameMode(GameMode.ADVENTURE);
                        GmLockData.get().set(target.getName(), "True");
                        GmLockData.save();
                    } else if (gamemode.equalsIgnoreCase("creative")) {
                        target.setGameMode(GameMode.CREATIVE);
                        GmLockData.get().set(target.getName(), "True");
                        GmLockData.save();
                    } else if (gamemode.equalsIgnoreCase("survival")) {
                        target.setGameMode(GameMode.SURVIVAL);
                        GmLockData.get().set(target.getName(), "True");
                        GmLockData.save();
                    } else if (gamemode.equalsIgnoreCase("spectator")) {
                        target.setGameMode(GameMode.SPECTATOR);
                        GmLockData.get().set(target.getName(), "True");
                        GmLockData.save();
                    }

                }
            }
        }
        return false;
    }
}