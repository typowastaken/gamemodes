package com.typocreates.gamemodes.listeners;

import com.typocreates.gamemodes.Gamemodes;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Logger;

public class PlayerJoinListener implements Listener {
    private final Gamemodes plugin;
    private final GeneralUtil gu;

    public PlayerJoinListener(Gamemodes plugin, GeneralUtil gu) {
        this.plugin = plugin;
        this.gu = gu;
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
    }
}
