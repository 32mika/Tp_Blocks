package de.mika32.tp_blocks.commands.args;

import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.*;
import org.bukkit.command.CommandSender;

public class SendAktivMessages {
    public static void sendAktivMessages(CommandSender sender, boolean status){
        if(BlocksLinkedListener.isMessagesStatus() == status){
            if(!status){
                classicErrorMessage(sender, "Error with status", "The Tp_Blocks_Messages are already deactivated");
                return;
            }

            classicErrorMessage(sender, "Error with status", "The Tp_Blocks_Messages are already aktive");
            return;
        }

        if(!status){
            BlocksLinkedListener.setMessagesStatus(status);
            classicPlayerMessage(sender, "Successfully deactivated the Tp_Blocks_Messages", "");
            return;
        }

        BlocksLinkedListener.setMessagesStatus(status);
        classicPlayerMessage(sender, "Successfully activated the Tp_Blocks_Messages", "");

    }
}
