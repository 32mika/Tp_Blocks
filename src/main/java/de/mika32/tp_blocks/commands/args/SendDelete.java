package de.mika32.tp_blocks.commands.args;


import de.mika32.tp_blocks.commands.TeleporterCommand;
import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import static de.mika32.tp_blocks.commands.Tp_Util.RefreshNameArray.*;
import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;


public class SendDelete {
    public static void sendDelete(CommandSender sender, String delIndent){
        String[] names = TeleporterCommand.getNames();
        int id = 0;
        int count = 0;
        boolean excThrown = false;
        ArrayList<Block> Tp_Blocks2 = BlocksLinkedListener.getTp_Blocks();

        classicErrorMessage(sender, "Please notice: ", "If you delete one of the paired Tp_Blocks the Second one will get deleted too!");

        if(Tp_Blocks2.size() == 0){
            classicErrorMessage(sender, "Error with Tp_Blocks delete", "You cant delete a Tp_Block when no Tp_Blocks exists");
            return;
        }

        try {
            id = Integer.parseInt(delIndent);
        } catch (Exception e){
            excThrown = true;
        }

        if(!excThrown){
            if(id == 0 | id < 0 | id > Tp_Blocks2.size()){
                classicErrorMessage(sender, "Error the passed id was invalid", "");
                return;
            }

            if(id % 2 == 0){
                Tp_Blocks2.remove(id -1);
                Tp_Blocks2.remove(id -2);
                names[id -1] = null;
                names[id -2] = null;

            }else {
                Tp_Blocks2.remove(id);
                Tp_Blocks2.remove(id -1);
                names[id] = null;
                names[id -1] = null;
            }

            classicPlayerMessage(sender, "Successfully deleted both Tp_Blocks", "");
            TeleporterCommand.setNames(names);
            BlocksLinkedListener.setTp_Blocks(Tp_Blocks2);
            refreshNameArray(BlocksLinkedListener.getTp_Blocks().size());

            try {
                assert names != null;
                for(String s : names){
                    if (s == null) {
                        BlocksLinkedListener.setAktiv(false);
                    }
                }

            }catch (Exception e){
                    names = new String[1];
                    BlocksLinkedListener.setTp_aktiv(false);
            }
            return;
        }

        for(String s : names){
            if (s.equals(delIndent)) {
                id = count;
                break;
            }
            count++;
        }
        id++;

        if(id % 2 == 0){
            Tp_Blocks2.remove(id -1);
            Tp_Blocks2.remove(id -2);
            names[id -1] = null;
            names[id -2] = null;
        }else {
            Tp_Blocks2.remove(id);
            Tp_Blocks2.remove(id -1);
            names[id] = null;
            names[id -1] = null;
        }

        classicPlayerMessage(sender, "Successfully deleted both Tp_Blocks", "");
        TeleporterCommand.setNames(names);
        BlocksLinkedListener.setTp_Blocks(Tp_Blocks2);
        refreshNameArray(BlocksLinkedListener.getTp_Blocks().size());

        try {
            assert names != null;
            for(String s : names){
                if (s == null) {
                    BlocksLinkedListener.setAktiv(false);
                }
            }

        }catch (Exception e){
            names = new String[1];
            BlocksLinkedListener.setTp_aktiv(false);
        }
    }
}
