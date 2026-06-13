package com.typocreates.gamemodes.listeners;

import com.typocreates.gamemodes.Gamemodes;
import com.typocreates.gamemodes.data.UpdateCheckResponse;
import com.typocreates.gamemodes.utils.GeneralUtil;
import com.typocreates.gamemodes.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class PlayerJoinListener implements Listener {
    private final Gamemodes plugin;
    private final GeneralUtil gu;
    private final UpdateChecker updateChecker;

    public PlayerJoinListener(Gamemodes plugin, GeneralUtil gu, UpdateChecker updateChecker) {
        this.plugin = plugin;
        this.gu = gu;
        this.updateChecker = updateChecker;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Logger logger = plugin.getLogger();
        Player p = e.getPlayer();

        if (gu.isGamemodeBlocked(p, p.getGameMode())) {
            if (gu.isGamemodeBlocked(p, Bukkit.getDefaultGameMode())) {
                gu.sendErrorMessage(p, "Something went wrong! Please tell an administrator of this server to check console!");
                logger.severe(p.getName() + " joined in a gamemode they're not allowed to be in! Unable to change their gamemode to the default as they aren't allowed in it!");
            } else {
                p.setGameMode(Bukkit.getDefaultGameMode());
                gu.sendMessage(p, "Your gamemode has been automatically switched! You weren't allowed in your old gamemode!");
            }
        }

        if (p.hasPermission("gamemodes.notifyupdate")) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                CompletableFuture<UpdateCheckResponse> future = updateChecker.getLatestVersion();
                future.thenAccept((data) -> {
                    if (!p.isOnline()) {
                        return;
                    }

                    if (data.getStatusCode() == 410) {
                        gu.sendErrorMessage(p, ChatColor.YELLOW + "[Gamemodes] " + ChatColor.RED + "Modrinth had a major update and the update checker has broken! Please check console for details! (A restart may be required to view the issue!");
                        return;
                    }

                    if (!data.getLatestVersionNumber().equals(plugin.getDescription().getVersion())) {
                        gu.sendMessage(p, "[Gamemodes] " + ChatColor.GREEN
                                + "New version available: "
                                + ChatColor.GOLD + data.getLatestVersionNumber()
                                + ChatColor.GREEN + "! Current version: "
                                + ChatColor.GOLD + plugin.getDescription().getVersion()
                                + ChatColor.GREEN + "! Download the latest version: "
                                + ChatColor.GOLD + "https://modrinth.com/project/CD4bmArk");
                    }
                });
            }, 100);
        }
    }
}
