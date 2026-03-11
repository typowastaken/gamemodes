package com.typocreates.gamemodes.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GmLockTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 2){
            List<String> gamemodes = new ArrayList<>();
            gamemodes.add("adventure");
            gamemodes.add("creative");
            gamemodes.add("survival");
            gamemodes.add("spectator");
            return gamemodes;
        }

        return null;
    }
}
