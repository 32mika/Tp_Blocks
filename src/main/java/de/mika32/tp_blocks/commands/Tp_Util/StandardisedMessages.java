package de.mika32.tp_blocks.commands.Tp_Util;

import de.mika32.tp_blocks.commands.TeleporterCommand;
import org.bukkit.command.CommandSender;

public class StandardisedMessages {
    protected static final String colorH = TeleporterCommand.colorH;
    protected static final String colorN = TeleporterCommand.colorN;
    protected static final String colorError = TeleporterCommand.colorError;
    protected static final String colorErrorN = TeleporterCommand.colorErrorN;

    public static void classicPlayerMessage(CommandSender sender, String firstP, String secondP) {
        sender.sendMessage(colorH + firstP + colorN + secondP);
    }

    public static void classicPlayerMessage(CommandSender sender, String part) {
        sender.sendMessage(colorH + "/teleporter " + colorN + part);
    }

    public static void classicErrorMessage(CommandSender sender, String firstP, String secondP) {
        if (secondP.equals("")){
            sender.sendMessage(colorError + firstP);
            return;
        }

        sender.sendMessage(colorError + firstP);
        sender.sendMessage(colorErrorN + secondP);
    }
}
