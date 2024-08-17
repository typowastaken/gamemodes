package com.typocreates.gamemodes.listeners;

import com.typocreates.gamemodes.files.GmLockData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGamemodeChangeListener implements Listener {
    @EventHandler
    public void onPlayerGamemodeChange(PlayerGameModeChangeEvent e){
        Player p = e.getPlayer();
        if (GmLockData.get().getString(p.getUniqueId().toString()) == "True"){
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Your gamemode switch has been canceled because your gamemode has been locked.");
        }
    }
}
