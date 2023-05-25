package de.mika32.finaltest;

import de.mika32.finaltest.commands.TeleporterCommand;
import de.mika32.finaltest.commands.TimerCommand;
import de.mika32.finaltest.listeners.BlocksLinkedListener;
import de.mika32.finaltest.listeners.JoinListener;
import de.mika32.finaltest.listeners.TNTListener;
import de.mika32.finaltest.listeners.WolfListener;
import de.mika32.finaltest.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.mika32.finaltest.timer.Timer;

import java.util.Objects;

public final class Finaltest extends JavaPlugin {
    public static PluginManager pm = Bukkit.getPluginManager();
    public static Finaltest instance;
    public static Timer timer;
    private Config config;

    @Override
    public void onLoad() {
        config = new Config();
        instance = this;
    }

    @Override
    public void onEnable() {
        pm.registerEvents(new JoinListener(), this);
        //pm.registerEvents(new TNTListener(), this);
        pm.registerEvents(new WolfListener(), this);
        pm.registerEvents(new BlocksLinkedListener(), this);

        timer = new Timer();
        TeleporterCommand tp = new TeleporterCommand();

        Objects.requireNonNull(getCommand("timer")).setExecutor(new TimerCommand());
        Objects.requireNonNull(getCommand("teleporter")).setExecutor(new TeleporterCommand());
        //getCommand("timer")).setExecutor(new TimerCommand();




    }

    @Override
    public void onDisable() {
        timer.save();
        config.save();

    }

    public static Finaltest getInstance() {
        return instance;
    }


    public Config getConfig1() {
        return config;
    }
}


