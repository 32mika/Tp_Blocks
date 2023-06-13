package de.mika32.tp_blocks.commands;

import de.mika32.tp_blocks.Tp_Blocks;
import de.mika32.tp_blocks.listeners.BlocksLinkedListener;
import de.mika32.tp_blocks.listeners.TabAutoComplete;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.*;

public class TeleporterCommand implements CommandExecutor, TabCompleter {
    protected static final String colorH = ChatColor.DARK_GREEN.toString();
    protected static final String colorN = ChatColor.WHITE.toString();
    protected static final String colorError = ChatColor.RED.toString();
    protected static final String colorErrorN = ChatColor.GOLD.toString();
    protected static String[] names = new String[1];

    @Override
    public boolean onCommand(CommandSender sender, Command command, String aliass, String[] args){
        try{
            if(null == args[0]){
                return true;
            }

        }catch (Exception e){
            classicErrorMessage(sender, "Seems like something went wrong!", "");
            classicErrorMessage(sender, "", "Please try to follow the structure, if you need help use the /teleporter help or the /teleporter structure command!");

            return true;

        }

        args[0] = sameLabel(sender, args[0]);

        switch (args[0].toLowerCase()){
            case "help":{
                try {
                    Integer.parseInt(args[1]);

                    if(args[1].equals(null)){
                        return false;
                    }
                }catch (Exception e){
                    classicErrorMessage(sender, "Error no valid page number was passed", "");
                    return false;
                }

                sendHelp(sender, args[1]);
                break;
            }

            case "delete":{
                try {
                    if(args[1].equals(null)){
                        return false;
                    }
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
                    if(args[1].equals(null)){
                        return false;
                    }
                }catch (Exception e){
                    classicErrorMessage(sender, "Error no valid Id was passed", "");
                    return false;
                }

                try {
                    if(args[2].equals(null)){
                        return false;
                    }
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

                    if(args[1].equals(null)){
                        return false;
                    }
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

    public static void sendList(CommandSender sender){
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

    public static void sendDelete(CommandSender sender, String delIdent){
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
            id = Integer.parseInt(delIdent);
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
            BlocksLinkedListener.setTp_Blocks(Tp_Blocks2);
            refreshNameArray(BlocksLinkedListener.getTp_Blocks().size());

            try {
                assert names != null;
                if(names.length == 0){
                    names = new String[1];

                }
            }catch (Exception e){
                if(names == null){
                    names = new String[1];
                }
            }

            BlocksLinkedListener.setTp_aktiv(false);
            return;

        }

        for(String s : names){
            if (s.equals(delIdent)) {
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
        BlocksLinkedListener.setTp_Blocks(Tp_Blocks2);
        refreshNameArray(BlocksLinkedListener.getTp_Blocks().size());

        try {
            assert names != null;
            if(names.length == 0){
                names = new String[1];
            }
        }catch (Exception e){
            if(names == null){
                names = new String[1];
            }
        }

        BlocksLinkedListener.setTp_aktiv(false);
    }

    public static void sendSetName(CommandSender sender, String index, String nameNew){
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
    }

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



    //
    // Starting here -> addition Code / outsourcing in Methods
    //



    public static void refreshNameArray(int size){
        String[] futureNames = new String[size];
        int count = 0;
        boolean done = false;
        boolean foundnull = false;


        if(size == 0){
            names = new String[1];
            return;
        }

        for(int i = 0; !done ; i++){
            for(int d = 0; d < names.length -1; d++){
                foundnull = false;

                if(names[d] == (null)){
                    if(!(names[d +1] == (null))){
                        names[d] = names[d +1];
                        names[d +1] = null;
                        foundnull = true;

                    }
                }
            }

            if(!foundnull){
                done = true;
            }
        }

        for(String s : names){
            if (s != null){
                futureNames[count] = s;
                count++;
            }
        }

        for(int i = names.length +1; i < size; i++){
            futureNames[i] = Integer.toString(i +1);
        }

        names = new String[size];
        names = futureNames;
    }

    // P stands for Part
    // Methode for classic prints based of coloration and placement
    public static void classicPlayerMessage(CommandSender sender, String firstP, String secondP){
        sender.sendMessage(colorH + firstP + colorN + secondP);
    }

    // Classic print with /teleporter asinged as first part
    public static void classicPlayerMessage(CommandSender sender, String part){
        sender.sendMessage(colorH + "/teleporter " + colorN + part);
    }

    public static void classicErrorMessage(CommandSender sender, String firstP, String secondP){
        if(secondP.equals("")){
            sender.sendMessage(colorError + firstP);
            return;
        }

        sender.sendMessage(colorError + firstP);
        sender.sendMessage(colorErrorN + secondP);

    }

    public static void setNames(String[] names) {
        TeleporterCommand.names = names;
    }

    public static void newNamen(){
        names = new String[1];
    }

    public static String[] getNames() {
        return names;
    }

    public static String sameLabel(CommandSender sender , String s){
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

