package de.mika32.tp_blocks;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import de.mika32.tp_blocks.commands.TeleporterCommand;
import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import de.mika32.tp_blocks.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Tp_Blocks extends JavaPlugin {
    public static PluginManager pm = Bukkit.getPluginManager();
    private static Tp_Blocks instance;
    private static Config config;

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

        //config.getConfig().set("Tp_Blocks.blocks.locations", null);
        //config.getConfig().set("Tp_Blocks.blocks.names", null);


        if(config.getConfig().contains("Tp_Blocks.blocks.locations")){
            ArrayList<String> stringLoc = new ArrayList<>(config.getConfig().getStringList("Tp_Blocks.blocks.locations"));
            ArrayList<Block> bl = new ArrayList<>();
            World world = this.getServer().getWorld("world");

            for(String s : stringLoc){
                double x = Double.parseDouble(s.substring(s.indexOf("x") +2, s.indexOf(",", s.indexOf("x"))));
                double y = Double.parseDouble(s.substring(s.indexOf("y") +2, s.indexOf(",", s.indexOf("y"))));
                double z = Double.parseDouble(s.substring(s.indexOf("z") +2, s.indexOf(",", s.indexOf("z"))));
                float pitch =  Float.parseFloat(s.substring(s.indexOf("pit") +6, s.indexOf(",", s.indexOf("pitch"))));
                float yaw =  Float.parseFloat(s.substring(s.indexOf("yaw") +4, s.indexOf("}", s.indexOf("yaw"))));

                Location c = new Location(world,x, y, z, yaw, pitch);

                bl.add(c.getBlock());
            }

            BlocksLinkedListener.setTp_aktiv(true);
            BlocksLinkedListener.setTp_Blocks(bl);

            if(config.getConfig().contains("Tp_Blocks.blocks.names")){
                ArrayList<String> stringNames = new ArrayList<>(config.getConfig().getStringList("Tp_Blocks.blocks.names"));
                TeleporterCommand.setNames(stringNames.toArray(TeleporterCommand.getNames()));


            }else{
                TeleporterCommand.newNamen();
            }
        }




    }

    @Override
    public void onDisable() {
        BlocksLinkedListener.save();
        config.save();
    }

    public static Tp_Blocks getInstance() {
        return instance;
    }

    public static Config getConfig1() {
        return config;
    }
}