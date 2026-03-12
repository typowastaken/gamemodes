package com.typocreates.gamemodes.commands;

import com.typocreates.gamemodes.Gamemodes;
import com.typocreates.gamemodes.files.GmLockData;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmUnlockCommand implements CommandExecutor {
    private final Gamemodes plugin;
    private final GeneralUtil gu;
    private final GmLockData gmLockData;
    public GmUnlockCommand(Gamemodes plugin, GeneralUtil gu, GmLockData gmLockData) {
        this.plugin = plugin;
        this.gu = gu;
        this.gmLockData = gmLockData;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String playerName = strings[0];
        if (playerName == null) { playerName = commandSender.getName(); }
        Player target = Bukkit.getServer().getPlayer(playerName);
        if (target == null) {
            gu.sendErrorMessage(commandSender, "That player could not be found, maybe they went offline?");
            return true;
        }
        gmLockData.get().set(target.getUniqueId().toString(), null);
        gmLockData.save();
        gu.sendMessage(commandSender, String.format("Unlocked %s's gamemode.", target.getName()));
        return true;
    }
}
