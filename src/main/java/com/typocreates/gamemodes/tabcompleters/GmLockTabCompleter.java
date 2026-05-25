package com.typocreates.gamemodes.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class GmLockTabCompleter implements TabCompleter {
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
