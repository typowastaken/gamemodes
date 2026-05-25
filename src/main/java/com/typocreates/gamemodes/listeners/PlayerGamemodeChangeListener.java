package com.typocreates.gamemodes.listeners;

import com.typocreates.gamemodes.files.GmLockData;
import com.typocreates.gamemodes.utils.GeneralUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

public class PlayerGamemodeChangeListener implements Listener {
    private final GeneralUtil gu;
    private final GmLockData gmLockData;
    public PlayerGamemodeChangeListener(GeneralUtil gu, GmLockData gmLockData) {
        this.gu = gu;
        this.gmLockData = gmLockData;
    }
    @EventHandler
    public void onPlayerGamemodeChange(PlayerGameModeChangeEvent e){
        Player p = e.getPlayer();
        String locked = gmLockData.get().getString(p.getUniqueId().toString());
        if (locked == null) {
            if (gu.isGamemodeBlocked(p, e.getNewGameMode())) {
                e.setCancelled(true);
                gu.sendErrorMessage(p, "You aren't allowed in gamemode " + e.getNewGameMode().toString().toLowerCase() + "!");
            }
            return;
        }

        if (locked.equalsIgnoreCase("true")) {
            e.setCancelled(true);
            gu.sendErrorMessage(p,"Your gamemode switch has been canceled because your gamemode has been locked.");
        }
    }
}
