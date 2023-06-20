package de.mika32.tp_blocks.commands.args;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.classicErrorMessage;
import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.classicPlayerMessage;

public class SendAliases {
    public static void sendAliases(CommandSender sender, String page){
        int page2 = Integer.parseInt(page);

        if(page2 == 0 | page2 > 2 | page2 < 0){
            classicErrorMessage(sender, "Error no valid page number was passed", "");
            return;
        }

        sender.sendMessage(ChatColor.YELLOW.toString() + "-----------------" + ChatColor.WHITE.toString() + " Aliases Page (" + page + "/2) " + ChatColor.YELLOW.toString() + "------------------");

        if(page2 == 1){
            classicPlayerMessage(sender, "/teleporter ->", " /tporter or /tPorter");
            classicPlayerMessage(sender, "help ->", " h");
            classicPlayerMessage(sender, "delete ->", " del");
            classicPlayerMessage(sender, "setName ->", " setN");
            classicPlayerMessage(sender, "aktiv ->", " ak");
            classicPlayerMessage(sender, "aktivMessages ->", " akMes");

        }

        if(page2 == 2){
            classicPlayerMessage(sender, "structure ->", " struc");
            classicPlayerMessage(sender, "aliases ->", " ali");
            classicPlayerMessage(sender, "list ->", " l");
            classicPlayerMessage(sender, "*Comming soon*", "");
            classicPlayerMessage(sender, "*Comming soon*", "");
            classicPlayerMessage(sender, "*Comming soon*", "");

        }

        classicPlayerMessage(sender, "", "");
        classicPlayerMessage(sender, "Upper and lower case are irrelevant", "");
        sender.sendMessage(ChatColor.YELLOW.toString() + "----------------------------------------------------" );
    }
}
