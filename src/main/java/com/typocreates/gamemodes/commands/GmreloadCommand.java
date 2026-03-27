package com.typocreates.gamemodes.commands;

import com.typocreates.gamemodes.Gamemodes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GmreloadCommand implements CommandExecutor {
    private final Gamemodes plugin;
    public GmreloadCommand(Gamemodes plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        plugin.reloadConfig();
        return true;
    }
}
