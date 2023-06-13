package de.mika32.tp_blocks.listeners;

import de.mika32.tp_blocks.commands.TeleporterCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TabAutoComplete {
    public static List<String> onTabComplete(CommandSender sender, Command command, String aliass, String[] args) {
        int id;
        int count = 0;
        boolean hasId;
        String currentArg = args[args.length- 1];
        String[] names = TeleporterCommand.getNames();
        ArrayList<String> retListe = new ArrayList<>();
        ArrayList<String> compList = new ArrayList<>();

        try{
            if(args[0] == null){
                retListe.add("No Suggestions");
                return retListe;
            }
        }catch (Exception e){
            retListe.add("No Suggestions");
            return retListe;
        }

        if(args.length == 1){
            retListe.add("help");
            retListe.add("delete");
            retListe.add("setName");
            retListe.add("aktiv");
            retListe.add("structure");
            retListe.add("aliases");
            retListe.add("list");
            retListe.add("aktivMessages");
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("help")){
            for (int i = 1; i <= 2; i++){
                retListe.add(Integer.toString(i));
            }
        }

        if(args.length == 3 && args[0].equalsIgnoreCase("setName")){
            retListe.add("<new Name>");
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("setName")){
            for (int i = 0; i < names.length; i++) {
                retListe.add(Integer.toString(i +1));
            }
        }

        if(args.length == 2 && args[0].equalsIgnoreCase("delete")){
            for (String s : names){
                try{
                    id = Integer.parseInt(s);
                    hasId = true;

                }catch (Exception e){
                    hasId = false;
                }

                if(!hasId){
                    retListe.add(s);
                    retListe.add(Integer.toString(count +1));

                }

                if(hasId){
                    retListe.add(Integer.toString(count +1));

                }

                count++;
            }
            retListe.removeIf(Objects::isNull);

            if(retListe.contains("1") && retListe.size() == 1){
                retListe.add("");
                retListe.remove("1");
            }

        }

        if(args.length == 2 && args[0].equalsIgnoreCase("aktiv") ||args.length == 2 && args[0].equalsIgnoreCase("aktivMessages")){
            retListe.add("true");
            retListe.add("false");
        }

        for (String s : retListe) {

            assert s != null;
            if(s.toLowerCase().startsWith(currentArg)){
                compList.add(s);
            }
        }

        return compList;
    }
}
