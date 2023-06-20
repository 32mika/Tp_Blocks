package de.mika32.tp_blocks.commands.args;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.classicPlayerMessage;

public class SendStructure {
    public static void sendStructure(CommandSender sender){
        sender.sendMessage(ChatColor.YELLOW.toString() + "---------" + ChatColor.RED.toString() + " Please notice the structure: " + ChatColor.YELLOW.toString() + "-----------");

        classicPlayerMessage(sender, "help <Page>");
        classicPlayerMessage(sender, "delete <Name or id>");
        classicPlayerMessage(sender, "setName <id> <new Name>");
        classicPlayerMessage(sender, "aktiv <true / false>");
        classicPlayerMessage(sender, "aktivMessages <true / false>");
        classicPlayerMessage(sender, "structure");
        classicPlayerMessage(sender, "aliases");
        classicPlayerMessage(sender, "list");

        sender.sendMessage(ChatColor.YELLOW.toString() + "---------------------------------------------" );
    }
}