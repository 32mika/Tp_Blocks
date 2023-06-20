package de.mika32.tp_blocks.commands.args;

import de.mika32.tp_blocks.commands.TeleporterCommand;
import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.classicErrorMessage;
import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.classicPlayerMessage;

public class SendList {
    public static void sendList(CommandSender sender){
        String[] names = TeleporterCommand.getNames();
        int count = 1;
        int id = 0;
        boolean hasId = false;

        if(BlocksLinkedListener.getTp_Blocks().size() < 2){
            classicErrorMessage(sender, "Error with List command", "There are no Tp_Blocks in the list");
            return;
        }

        classicPlayerMessage(sender, "", "");
        sender.sendMessage(ChatColor.YELLOW.toString() + "------------------" + ChatColor.WHITE.toString() + " List of Tp_Blocks " + ChatColor.YELLOW.toString() + "-------------------" );
        classicPlayerMessage(sender, "Structured is like:", "");
        classicPlayerMessage(sender, "<Id> has the Name <Name>", "");
        classicPlayerMessage(sender, "", "");

        for (String s : names){
            try{
                id = Integer.parseInt(s);
                hasId = true;

            }catch (Exception e){
                hasId = false;
            }

            if(!hasId){
                if(s == null){
                    s = ChatColor.YELLOW.toString() + "no Name applied";
                }

                sender.sendMessage(ChatColor.DARK_GREEN.toString() + 0 + (count) + ChatColor.WHITE.toString() + " has the Name: " + ChatColor.DARK_GREEN.toString() + s);

            }

            if(hasId){
                sender.sendMessage(ChatColor.DARK_GREEN.toString() + 0 + (count) + ChatColor.WHITE.toString() + " has the Name: " + ChatColor.YELLOW.toString() + "no name applied");

            }

            count++;
        }

        sender.sendMessage(ChatColor.YELLOW.toString() + "-----------------------------------------------------");

    }
}
