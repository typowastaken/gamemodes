package com.typocreates.gamemodes.model;

import org.bukkit.GameMode;

public enum GM {
    ADVENTURE("Adventure", GameMode.ADVENTURE),
    CREATIVE("Creative", GameMode.CREATIVE),
    SURVIVAL("Survival", GameMode.SURVIVAL),
    SPECTATOR("Spectator", GameMode.SPECTATOR);

    public final String label;
    public final GameMode gamemode;
    GM(String label, GameMode gamemode) {
        this.label = label;
        this.gamemode = gamemode;
    }

}
