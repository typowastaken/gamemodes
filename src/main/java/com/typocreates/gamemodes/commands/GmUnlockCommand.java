package com.typocreates.gamemodes.commands;

import com.typocreates.gamemodes.Gamemodes;
import com.typocreates.gamemodes.files.GmLockData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmUnlockCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String playerName = strings[0];
        Player target = Bukkit.getServer().getPlayerExact(playerName);
        GmLockData.get().set(target.getUniqueId().toString(), null);
        GmLockData.save();
        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            p.sendMessage(ChatColor.YELLOW + "Unlocked " + ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + "'s gamemode.");
        }else{
            Gamemodes.getPlugin().getLogger().info(ChatColor.YELLOW + "Unlocked " + ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + "'s gamemode.");
        }
        return true;
    }
}
