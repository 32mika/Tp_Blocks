package de.mika32.tp_blocks.commands.args;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.*;

public class SendHelp {
    public static void sendHelp(CommandSender sender, String page){
        if(!page.equals("1") & !page.equals("2")){
            classicErrorMessage(sender, "Error no valid page number was passed", "");
            return;
        }

        sender.sendMessage(ChatColor.YELLOW.toString() + "--------------" + ChatColor.WHITE.toString() + " Help Teleporter Page (" + page + "/2) " + ChatColor.YELLOW.toString() + "--------------");

        if(page.equals("1")){
            classicPlayerMessage(sender, "/teleporter help: ", "Explains the use of the different commands");
            classicPlayerMessage(sender, "/teleporter delete: ", "Deletes your Tp_Blocks by Name or id!");
            classicPlayerMessage(sender, "/teleporter setName: ", "setNames to Tp_Blocks by id and name");
            classicPlayerMessage(sender, "/teleporter aktiv: ", "Activate or deactivate Tp_Blocks in general");
            classicPlayerMessage(sender, "/teleporter structure: ", "Structure of Tp_Block Commands");
            classicPlayerMessage(sender, "/teleporter aliases: ", "Shows all aliases for Commands");
            classicPlayerMessage(sender, "/teleporter list: ", "Lists up all Tp_Block with Nicknames");
        }

        if (page.equals("2")){
            classicPlayerMessage(sender, "/teleporter aktivMessages: ", "Activate or deactivate Messages and delay while Tp-ing");

            for (int i = 0; i < 5; i++){
                classicPlayerMessage(sender, "*Coming soon*", "");
            }
        }

        sender.sendMessage(ChatColor.YELLOW.toString() + "----------------------------------------------------");
        classicPlayerMessage(sender, "", "");
    }
}
