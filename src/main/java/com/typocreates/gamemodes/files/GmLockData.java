package com.typocreates.gamemodes.files;

import com.typocreates.gamemodes.Gamemodes;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class GmLockData {
    private final Gamemodes plugin;
    public GmLockData(Gamemodes plugin) {
        this.plugin = plugin;
    }

    private File file;
    private FileConfiguration gmLockFile;
//    Finds or generates the GamemodeLockData File
    public void setup(){
        file = new File(plugin.getDataFolder(), "gmlockdata.yml");
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                plugin.getLogger().warning("Couldn't create the GamemodeLockData file, reach out to the plugin creator @TypoWasTaken on Discord.");
            }
        }
        gmLockFile = YamlConfiguration.loadConfiguration(file);
    }
    public FileConfiguration get() {
        return gmLockFile;
    }

    public boolean isLocked(UUID uuid) {
        String locked = gmLockFile.getString(uuid.toString());
        if (locked != null && locked.equalsIgnoreCase("true")) {
            return true;
        } else {
            return false;
        }
    }

    public void save() {
        try {
            gmLockFile.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("Couldn't save the GamemodeLockData file, reach out to the plugin creator @TypoWasTaken on Discord.");
        }
    }
    public void reload() {
        gmLockFile = YamlConfiguration.loadConfiguration(file);
    }
}
