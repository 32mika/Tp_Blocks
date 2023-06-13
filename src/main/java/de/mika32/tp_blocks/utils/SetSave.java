package de.mika32.tp_blocks.utils;

import de.mika32.tp_blocks.Tp_Blocks;
import de.mika32.tp_blocks.commands.TeleporterCommand;
import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class SetSave {
    public static void setAll(){
        Config config = de.mika32.tp_blocks.Tp_Blocks.getConfig1();

        if(config.getConfig().contains("Tp_Blocks.blocks.locations")) {
            ArrayList<String> stringLoc = new ArrayList<>(config.getConfig().getStringList("Tp_Blocks.blocks.locations"));
            ArrayList<Block> bl = new ArrayList<>();
            World world = Tp_Blocks.getWorld();

            for (String s : stringLoc) {
                double x = Double.parseDouble(s.substring(s.indexOf("x") + 2, s.indexOf(",", s.indexOf("x"))));
                double y = Double.parseDouble(s.substring(s.indexOf("y") + 2, s.indexOf(",", s.indexOf("y"))));
                double z = Double.parseDouble(s.substring(s.indexOf("z") + 2, s.indexOf(",", s.indexOf("z"))));
                float pitch = Float.parseFloat(s.substring(s.indexOf("pit") + 6, s.indexOf(",", s.indexOf("pitch"))));
                float yaw = Float.parseFloat(s.substring(s.indexOf("yaw") + 4, s.indexOf("}", s.indexOf("yaw"))));

                Location c = new Location(world, x, y, z, yaw, pitch);

                bl.add(c.getBlock());
            }

            BlocksLinkedListener.setTp_aktiv(true);
            BlocksLinkedListener.setTp_Blocks(bl);

            if (config.getConfig().contains("Tp_Blocks.blocks.names")) {
                ArrayList<String> stringNames = new ArrayList<>(config.getConfig().getStringList("Tp_Blocks.blocks.names"));
                TeleporterCommand.setNames(stringNames.toArray(TeleporterCommand.getNames()));


            } else {
                TeleporterCommand.newNamen();
            }
        }
    }
}
