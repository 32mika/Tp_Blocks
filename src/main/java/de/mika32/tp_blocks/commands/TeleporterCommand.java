package de.mika32.tp_blocks.commands;

import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import de.mika32.tp_blocks.listeners.TabAutoComplete;

import static de.mika32.tp_blocks.commands.args.SendAktivMessages.sendAktivMessages;
import static de.mika32.tp_blocks.commands.args.SendAliases.sendAliases;
import static de.mika32.tp_blocks.commands.args.SendDelete.sendDelete;
import static de.mika32.tp_blocks.commands.args.SendAktiv.sendAktiv;
import static de.mika32.tp_blocks.commands.args.SendHelp.sendHelp;
import static de.mika32.tp_blocks.commands.args.SendList.sendList;
import static de.mika32.tp_blocks.commands.args.SendSetName.sendSetName;
import static de.mika32.tp_blocks.commands.Tp_Util.StandardisedMessages.*;
import static de.mika32.tp_blocks.commands.args.SendStructure.sendStructure;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import java.util.*;


public class TeleporterCommand implements CommandExecutor, TabCompleter {
    public static final String colorH = ChatColor.DARK_GREEN.toString();
    public static final String colorN = ChatColor.WHITE.toString();
    public static final String colorError = ChatColor.RED.toString();
    public static final String colorErrorN = ChatColor.GOLD.toString();
    protected static String[] names = new String[1];

    @Override
    public boolean onCommand(CommandSender sender, Command command, String aliass, String[] args){
        try{
            args[0].indexOf(1);

        }catch (Exception e){
            classicErrorMessage(sender, "Seems like something went wrong!", "");
            classicErrorMessage(sender, "", "Please try to follow the structure, if you need help use the /teleporter help or the /teleporter structure command!");

            return false;
        }

        args[0] = sameLabel(sender, args[0]);

        switch (args[0].toLowerCase()){
            case "help":{
                try {
                    Integer.parseInt(args[1]);
                    args[1].indexOf(1);

                }catch (Exception e){
                    classicErrorMessage(sender, "Error no valid page number was passed", "");
                    return false;
                }

                sendHelp(sender, args[1]);
                break;
            }

            case "delete":{
                try {
                    args[1].indexOf(1);

                }catch (Exception e){
                    classicErrorMessage(sender, "Error no valid id or name was passed", "");
                    return false;
                }

                sendDelete(sender, args[1]);
                break;
            }

            case "setname":{
                try {
                    Integer.parseInt(args[1]);
                    args[1].indexOf(1);

                }catch (Exception e){
                    classicErrorMessage(sender, "Error no valid Id was passed", "");
                    return false;
                }

                try {
                    args[2].indexOf(1);

                }catch (Exception e){
                    classicErrorMessage(sender, "Error no valid name was passed", "");
                    return false;
                }

                sendSetName(sender, args[1], args[2]);
                break;
            }

            case "aktiv":{
                boolean status;


                try {
                    status  = Boolean.parseBoolean(args[1].toLowerCase());

                }catch (Exception e){
                    classicErrorMessage(sender, "Error with input", "Please insert as second argument true or false");
                    return false;
                }

                sendAktiv(sender, status);
                break;
            }

            case "aktivmessages":{
                boolean status;

                try {
                    status  = Boolean.parseBoolean(args[1].toLowerCase());

                }catch (Exception e){
                    classicErrorMessage(sender, "Error with input", "Please insert as second argument true or false");
                    return false;
                }

                sendAktivMessages(sender, status);
                break;
            }

            case "aliases":{
                try {
                    Integer.parseInt(args[1]);

                    args[1].indexOf(1);

                }catch (Exception e){
                    classicErrorMessage(sender, "Error no valid page number was passed", "");
                    return false;
                }

                sendAliases(sender, args[1]);
                break;
            }

            case "structure":{
                sendStructure(sender);
                break;
            }

            case "list":{
                sendList(sender);
                break;
            }

            default:{
                classicErrorMessage(sender, "Seems like something went wrong!", "Please try to follow the structure, if you need help use the /teleporter help or the /teleporter structure command!");
                break;
            }
        }

        return true;
    }



    //
    // Starting here -> addition Code / outsourcing in Methods
    //



    public static void setNames(String[] names) {
        TeleporterCommand.names = names;
    }

    public static void newNamen(){
        names = new String[1];
    }

    public static String[] getNames() {
        return names;
    }

    private static String sameLabel(CommandSender sender , String s){
        s = s.toLowerCase();

        switch (s){
            case "h":{
                return "help";
            }

            case "del":{
                return "delete";
            }

            case "setN":{
                return "setName";
            }

            case "ak":{
                return "aktiv";
            }

            case "akMes":{
                return "aktivMessages";
            }

            case "ali":{
                return "aliases";
            }

            case "struc":{
                return "structure";
            }

            case "l":{
                return "list";
            }
        }
        return s;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String aliass, String[] args) {
        return TabAutoComplete.onTabComplete(sender, command, aliass, args);
    }
}

// Zufünftige Features:
//                      - mögliche Einstellung eines Preises für den Tp!
//                      - extra Texturen für aktive Tp_Blocks + custom item zum Verbinden der Tp_Blocks!
//                      - Beschriftung direkt in der Minecraftwelt sichtbar machen über Command
//                      - bug fixen nach triple connect instant tp back to start
//                      - Tp_Block als variable machen (nicht nur Wolle als Tp möglich)
//                      - setter für Tp_Blocks variabel machen
//                      - leistung verbessern durch die Benutzung von Hashsets
//                      - sound und partikel beim tp
//                      - befehl für sound und partikel
//                      - leistung beim tp verbessern ggf andere datentypen(Hashmap + ggf. Hashset)
//                      - komische command angebote löschen!
//                      - command methoden optimieren gerade sendDelete
//                      - triple Tps verhindern! <- vorteilhaft mit maps
//                      - delay heraus löschen und durch andere functionality ersetzen
//                      - aliases tab completor fixen nach page
//                      - tab completor auch für aliases -> beim schreiben einfügen

