package de.mika32.tp_blocks.commands.Tp_Util;

import de.mika32.tp_blocks.commands.TeleporterCommand;

public class RefreshNameArray {
    public static void refreshNameArray(int size){
        String[] names = TeleporterCommand.getNames();
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
        TeleporterCommand.setNames(futureNames);
    }
}
