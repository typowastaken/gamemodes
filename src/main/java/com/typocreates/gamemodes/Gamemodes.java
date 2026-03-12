package com.typocreates.gamemodes;

import com.typocreates.gamemodes.commands.*;
import com.typocreates.gamemodes.files.GmLockData;
import com.typocreates.gamemodes.listeners.PlayerGamemodeChangeListener;
import com.typocreates.gamemodes.tabcompleters.GmLockTabCompleter;
import com.typocreates.gamemodes.utils.GeneralUtil;
import com.typocreates.gamemodes.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

// Created with love by Typo <3

public final class Gamemodes extends JavaPlugin {
    private Gamemodes plugin;
    private GeneralUtil gu;
    private GmLockData lockData;


    @Override
    public void onEnable() {
        plugin = this;
        Logger logger = plugin.getLogger();

        // Adds plugin Metrics
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

        // Loads config
        saveDefaultConfig();


        gu = new GeneralUtil(plugin);

        // Load the GamemodeLockData file
        lockData = new GmLockData(plugin);
        lockData.setup();
        lockData.get().options().copyDefaults(true);
        lockData.save();
        // Loads commands
        logger.info("Loading commands!");
        getCommand("gma").setExecutor(new GmaCommand(gu));
        getCommand("gmc").setExecutor(new GmcCommand(gu));
        getCommand("gms").setExecutor(new GmsCommand(gu));
        getCommand("gmsp").setExecutor(new GmspCommand(gu));
        getCommand("gmlock").setExecutor(new GmLockCommand(plugin, lockData));
        getCommand("gmlock").setTabCompleter(new GmLockTabCompleter());
        getCommand("gmunlock").setExecutor(new GmUnlockCommand(plugin, lockData));
        logger.info("Commands loaded!");

        getServer().getPluginManager().registerEvents(new PlayerGamemodeChangeListener(lockData), plugin);
        getLogger().info("Gamemode change event listener loaded.");

        new UpdateChecker(this, 118865).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("Update check complete, no need updates needed.");
            } else {
                getLogger().info("Update check complete, updates are needed! Please update here: https://modrinth.com/project/CD4bmArk");
            }
        });

        plugin.getLogger().info("Plugin fully loaded.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        plugin.getLogger().info("Plugin fully stopped.");
    }
}
