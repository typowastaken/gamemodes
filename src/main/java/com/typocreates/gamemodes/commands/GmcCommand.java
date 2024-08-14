package com.typocreates.gamemodes.commands;

import com.typocreates.gamemodes.Gamemodes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.Console;

public class GmcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Gamemodes.getPlugin().reloadConfig();
        boolean doSounds = Gamemodes.getPlugin().getConfig().getBoolean("do-sound-effects");
        if (strings.length == 0){
            if (commandSender instanceof Player ) {
                Player p = (Player) commandSender;
                if (p.getGameMode() != GameMode.CREATIVE){
                    if (doSounds){
                        p.playSound(p, "minecraft:entity.experience_orb.pickup", 1, 1 );
                    }
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(ChatColor.YELLOW + "Your gamemode has been set to Creative.");
                }else{
                    p.sendMessage(ChatColor.RED + "Your gamemode hasn't changed since you're already in Creative.");
                }
            }else{
                Gamemodes.getPlugin().getLogger().info(ChatColor.RED + "You either have to be a player or target a player to use this command.");
            }
        }else if (strings.length == 1){
            String playerName = strings[0];
            Player target = Bukkit.getServer().getPlayerExact(playerName);
            if (target != null){
                if (target.getGameMode() != GameMode.CREATIVE){
                    target.setGameMode(GameMode.CREATIVE);
                    if (commandSender instanceof Player) {
                        Player p = (Player) commandSender;
                        if (doSounds){
                            p.playSound(p, "minecraft:entity.experience_orb.pickup", 1, 1 );
                        }
                        p.sendMessage(ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + "'s gamemode has been set to Creative.");
                    }else{
                        Gamemodes.getPlugin().getLogger().info(ChatColor.AQUA + target.getDisplayName() + ChatColor.YELLOW + "'s gamemode has been set to Creative.");
                    }
                }else if (commandSender instanceof Player){
                    Player p = (Player) commandSender;
                    p.sendMessage(ChatColor.RED + "Nothing happened because the targeted player is already in Creative.");
                }else{
                    Gamemodes.getPlugin().getLogger().info(ChatColor.RED + "Nothing happened because the targeted player is already in Creative.");
                }
            }else if (commandSender instanceof Player){
                Player p = (Player) commandSender;
                p.sendMessage(ChatColor.RED + "That player either does not exist or isn't currently online.");
            }else{
                Gamemodes.getPlugin().getLogger().info(ChatColor.RED + "That player either does not exist or isn't currently online.");
            }
        }else if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            p.sendMessage(ChatColor.RED + "The maximum number of arguments is 1. Example: /gmc <player>");
        }else{
            Gamemodes.getPlugin().getLogger().info(ChatColor.RED + "The maximum number of arguments is 1. Example: /gmc <player>");
        }
        return true;
    }
}