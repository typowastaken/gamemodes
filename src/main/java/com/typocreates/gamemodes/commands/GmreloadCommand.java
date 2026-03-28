package com.typocreates.gamemodes.commands;

import com.typocreates.gamemodes.Gamemodes;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GmreloadCommand implements CommandExecutor {
    private final Gamemodes plugin;
    private final GeneralUtil gu;
    public GmreloadCommand(Gamemodes plugin, GeneralUtil gu) {
        this.plugin = plugin;
        this.gu = gu;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        plugin.reloadConfig();

        gu.sendMessage(commandSender, "Config reloaded!");
        return true;
    }
}
