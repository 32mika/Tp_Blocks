package de.mika32.tp_blocks;

import de.mika32.tp_blocks.commands.TeleporterCommand;
import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import de.mika32.tp_blocks.utils.Config;
import de.mika32.tp_blocks.utils.Save;
import de.mika32.tp_blocks.utils.SetSave;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class Tp_Blocks extends JavaPlugin {
    public static PluginManager pm = Bukkit.getPluginManager();
    private static Tp_Blocks instance;
    private static Config config;
    private static World world;

    @Override
    public void onLoad() {
        config = new Config();
        instance = this;
    }

    @Override
    public void onEnable() {
        pm.registerEvents(new BlocksLinkedListener(), this);
        TeleporterCommand tp = new TeleporterCommand();

        Objects.requireNonNull(getCommand("teleporter")).setExecutor(new TeleporterCommand());
        world = this.getServer().getWorld("world");
        SetSave.setAll();
    }

    @Override
    public void onDisable() {
        Save.saveAll();
        config.save();
    }

    public static World getWorld() {
        return world;
    }

    public static Tp_Blocks getInstance() {
        return instance;
    }

    public static Config getConfig1() {
        return config;
    }
}