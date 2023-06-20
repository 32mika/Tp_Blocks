package de.mika32.tp_blocks.commands.args;

import de.mika32.tp_blocks.commands.TeleporterCommand;
import org.bukkit.command.CommandSender;
import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.classicErrorMessage;

public class SendSetName {
    protected static final String colorH = TeleporterCommand.colorH;
    protected static final String colorN = TeleporterCommand.colorN;
    protected static final String colorError = TeleporterCommand.colorError;
    protected static final String colorErrorN = TeleporterCommand.colorErrorN;

    public static void sendSetName(CommandSender sender, String index, String nameNew){
        String[] names = TeleporterCommand.getNames();

        if(names.length == 1){
            classicErrorMessage(sender, "Error there are no Tp_Blocks you could apply a name to", "");
            return;
        }

        if(Integer.parseInt(index) -1 < 0 | Integer.parseInt(index) > names.length){
            classicErrorMessage(sender, "Error the passed Id is invalid", "");
            return;
        }

        names[Integer.parseInt(index) -1] = nameNew;
        sender.sendMessage(colorH + "Your name " + colorN + nameNew + colorH + " was successfully applied to the Tp_Block with the Id " + colorN + (Integer.parseInt(index)));
        TeleporterCommand.setNames(names);
    }
}
