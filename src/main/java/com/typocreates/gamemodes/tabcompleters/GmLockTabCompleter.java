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
            gamemodes.add("Adventure");
            gamemodes.add("Creative");
            gamemodes.add("Survival");
            gamemodes.add("Spectator");
            return gamemodes;
        }

        return null;
    }
}
