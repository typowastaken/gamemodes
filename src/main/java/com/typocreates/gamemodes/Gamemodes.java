package com.typocreates.gamemodes;

import com.typocreates.gamemodes.commands.GmaCommand;
import com.typocreates.gamemodes.commands.GmcCommand;
import com.typocreates.gamemodes.commands.GmsCommand;
import com.typocreates.gamemodes.commands.GmspCommand;
import org.bukkit.plugin.java.JavaPlugin;

//Created with love by Typo <3

public final class Gamemodes extends JavaPlugin {
    private static Gamemodes plugin;
    @Override
    public void onEnable() {
        // Plugin startup login
        int pluginId = 23009;
        Metrics metrics = new Metrics(this, pluginId);
        plugin = this;
        saveDefaultConfig();
        saveConfig();
        getCommand("gma").setExecutor(new GmaCommand());
        plugin.getLogger().info("GMA Command loaded.");
        getCommand("gmc").setExecutor(new GmcCommand());
        plugin.getLogger().info("GMC Command loaded.");
        getCommand("gms").setExecutor(new GmsCommand());
        plugin.getLogger().info("GMS Command loaded.");
        getCommand("gmsp").setExecutor(new GmspCommand());
        plugin.getLogger().info("GMSP Command loaded.");
        plugin.getLogger().info("Plugin fully loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        plugin.getLogger().info("Plugin fully stopped.");
    }
    public static Gamemodes getPlugin() { return plugin; }
}
