package de.mika32.tp_blocks.commands.args;

import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import org.bukkit.command.CommandSender;
import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.*;

public class SendAktiv {
    public static void sendAktiv(CommandSender sender, boolean status){
        if(BlocksLinkedListener.isAktiv() == status){
            if(!status){
                classicErrorMessage(sender, "Error with status", "The Tp_Blocks are already deactivated");
                return;
            }

            classicErrorMessage(sender, "Error with status", "The Tp_Blocks are already aktive");
            return;
        }

        if(!status){
            BlocksLinkedListener.setAktiv(status);
            classicPlayerMessage(sender, "Successfully deactivated the Tp_Blocks", "");
            return;
        }

        BlocksLinkedListener.setAktiv(status);
        classicPlayerMessage(sender, "Successfully activated the Tp_Blocks", "");

    }
}
