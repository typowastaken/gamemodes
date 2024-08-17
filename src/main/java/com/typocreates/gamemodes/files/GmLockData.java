package com.typocreates.gamemodes.files;

import com.typocreates.gamemodes.Gamemodes;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GmLockData {
    private static File file;
    private static FileConfiguration gmLockFile;
    //Finds or generates the GamemodeLockData File
    public static void setup(){
        file = new File(Gamemodes.getPlugin().getDataFolder(), "gmlockdata.yml");
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                Gamemodes.getPlugin().getLogger().warning("Couldn't create the GamemodeLockData file, Reach out to the plugin creator @TypoWasTaken on Discord.");
            }
        }
        gmLockFile = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get(){
        return gmLockFile;
    }
    public static void save(){
        try{
            gmLockFile.save(file);
        }catch (IOException e){
            Gamemodes.getPlugin().getLogger().warning("Couldn't save the GamemodeLockData file, Reach out to the plugin creator @TypoWasTaken on Discord.");
        }
    }
    public static void reload(){
        gmLockFile = YamlConfiguration.loadConfiguration(file);
    }
}
