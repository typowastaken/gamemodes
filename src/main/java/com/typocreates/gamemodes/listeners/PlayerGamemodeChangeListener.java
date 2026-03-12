package com.typocreates.gamemodes.listeners;

import com.typocreates.gamemodes.files.GmLockData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGamemodeChangeListener implements Listener {
    private final GmLockData lockData;
    public PlayerGamemodeChangeListener(GmLockData lockData) {
        this.lockData = lockData;
    }
    @EventHandler
    public void onPlayerGamemodeChange(PlayerGameModeChangeEvent e){
        Player p = e.getPlayer();
        String locked = lockData.get().getString(p.getUniqueId().toString());
        if (locked == null) {
            return;
        }

        if (locked.equalsIgnoreCase("true")) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.RED + "Your gamemode switch has been canceled because your gamemode has been locked.");
        }
    }
}
