package de.mika32.tp_blocks.utils;

import de.mika32.tp_blocks.commands.TeleporterCommand;
import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Save {
    public static void saveAll(){
        int id;
        int count = 0;
        boolean hasId;
        ArrayList<Block> Tp_Blocks = BlocksLinkedListener.getTp_Blocks();
        ArrayList<String> loc = new ArrayList<>();
        ArrayList<String> names1 = new ArrayList<>();
        Config config = de.mika32.tp_blocks.Tp_Blocks.getConfig1();

        for(Block b : Tp_Blocks){
            loc.add(b.getLocation().toString());
        }

        config.getConfig().set("Tp_Blocks.blocks.locations", loc);

        for (String s : TeleporterCommand.getNames()){
            try{
                id = Integer.parseInt(s);
                hasId = true;

            }catch (Exception e){
                hasId = false;
            }

            if(!hasId){
                if(s == null){
                    names1.add(Integer.toString(count +1));
                }else {
                    names1.add(s);
                }
            }

            if(hasId){
                names1.add(Integer.toString(count +1));
            }

            count++;
        }

        config.getConfig().set("Tp_Blocks.blocks.names", names1);
    }
}
