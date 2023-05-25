package de.mika32.finaltest.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private final File file;
    private final YamlConfiguration config;

    public YamlConfiguration getConfig() {
        return config;
    }

    public File getFile() {
        return file;
    }

    public Config() {
        File dir = new File("./plugins/FinalTest/");

        if(!dir.exists()) {
            dir.mkdirs();
        }

        this.file = new File(dir, "config.yml");

        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {

             }

        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try {
            config.save(file);
        } catch (IOException e) {
        }
    }

}
