package com.typocreates.gamemodes;

import com.typocreates.gamemodes.commands.*;
import com.typocreates.gamemodes.files.GmLockData;
import com.typocreates.gamemodes.listeners.PlayerGamemodeChangeListener;
import com.typocreates.gamemodes.tabcompleters.GmLockTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

//Created with love by Typo <3

public final class Gamemodes extends JavaPlugin {
    private static Gamemodes plugin;
    @Override
    public void onEnable() {
        // Plugin startup login
        plugin = this;
        //Adds plugin Metrics
        Metrics metrics = new Metrics(this, 23009);
        metrics.addCustomChart(new Metrics.SingleLineChart("players", () -> Bukkit.getOnlinePlayers().size()));

        metrics.addCustomChart(new Metrics.DrilldownPie("java_version", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String javaVersion = System.getProperty("java.version");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(javaVersion, 1);
            if (javaVersion.startsWith("1.7")) {
                map.put("Java 1.7", entry);
            } else if (javaVersion.startsWith("1.8")) {
                map.put("Java 1.8", entry);
            } else if (javaVersion.startsWith("1.9")) {
                map.put("Java 1.9", entry);
            } else {
                map.put("Other", entry);
            }
            return map;
        }));

        //Loads config
        saveDefaultConfig();
        //Load the GamemodeLockData file
        GmLockData.setup();
        GmLockData.get().options().copyDefaults(true);
        GmLockData.save();
        //Loads commands
        getCommand("gma").setExecutor(new GmaCommand());
        plugin.getLogger().info("GMA Command loaded.");
        getCommand("gmc").setExecutor(new GmcCommand());
        plugin.getLogger().info("GMC Command loaded.");
        getCommand("gms").setExecutor(new GmsCommand());
        plugin.getLogger().info("GMS Command loaded.");
        getCommand("gmsp").setExecutor(new GmspCommand());
        plugin.getLogger().info("GMSP Command loaded.");
        getCommand("gmlock").setExecutor(new GmLockCommand());
        getCommand("gmlock").setTabCompleter(new GmLockTabCompleter());
        plugin.getLogger().info("Beta Feature: GMLock command loaded.");
        getCommand("gmunlock").setExecutor(new GmUnlockCommand());
        plugin.getLogger().info("Beta Feature: GMUnlock command loaded.");
        getServer().getPluginManager().registerEvents(new PlayerGamemodeChangeListener(), this);
        plugin.getLogger().info("Gamemode change event listener loaded.");
        plugin.getLogger().info("Plugin fully loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        plugin.getLogger().info("Plugin fully stopped.");
    }
    public static Gamemodes getPlugin() { return plugin; }
}
