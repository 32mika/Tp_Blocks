package de.mika32.tp_blocks;

import org.bukkit.plugin.java.JavaPlugin;
import de.mika32.tp_blocks.commands.TeleporterCommand;
import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import de.mika32.tp_blocks.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class Tp_Blocks extends JavaPlugin {
    public static PluginManager pm = Bukkit.getPluginManager();
    public static Tp_Blocks instance;
    private Config config;

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
    }

    @Override
    public void onDisable() {
        config.save();
    }

    public static Tp_Blocks getInstance() {
        return instance;
    }

    public Config getConfig1() {
        return config;
    }
}