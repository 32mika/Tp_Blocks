package de.mika32.tp_blocks.listeners;

import de.mika32.tp_blocks.commands.TeleporterCommand;
import it.unimi.dsi.fastutil.ints.Int2IntSortedMaps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;


public class BlocksLinkedListener implements Listener {
    protected static Block b1;
    protected static Block b2;
    protected static Player p = null;
    private static ArrayList<Block> Tp_Blocks = new ArrayList<Block>();
    private static boolean tp_aktiv = false;
    private static boolean aktiv = true;
    private static DecimalFormat df = new DecimalFormat("#");
    private static boolean messagesStatus = true;
    private static String colorErrorH = ChatColor.RED.toString();
    private static String colorErrorN = ChatColor.GOLD.toString();
    private static String colorH = ChatColor.AQUA.toString();
    private static String colorN = ChatColor.GOLD.toString();
    private static Player sender;


    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        sender = event.getPlayer();

        if (!aktiv || !event.hasBlock() || !event.hasItem()) {
            return;
        }

        if (event.getClickedBlock().getType() != Material.WHITE_WOOL) {
            return;
        }

        if (!event.getItem().getType().equals(Material.STICK)) {
            return;
        }

        if( !(event.getPlayer().equals(p)) & p != null){
            classicErrorMessage(sender, "Error duplicated User", "Another Player is currently linking Tp_Blocks. Please try again later!");
            return;
        }

        if(b1 != (null)){
            b2 = event.getClickedBlock();

            classicCleanMessage(sender, ChatColor.GOLD.toString(), "Second Tp_Block selected" , 0);
            classicCleanMessage(sender, ChatColor.RED.toString(), "", 0);
            classicCleanMessage(sender, ChatColor.GOLD.toString(), "Both Tp_Blocks selected successfully!" , 1000);
            classicCleanMessage(sender, ChatColor.RED.toString(), "", 0);


            classicCleanMessage(sender, ChatColor.AQUA.toString(), "---Working_on_Connection---", 1000);

            if(b1.equals(b2)){
                classicErrorMessage(sender, "Error while connecting", "Cant connect a Tp_Block to it self!");
                return;
            }


            classicCleanMessage(sender, ChatColor.RED.toString(), "", 2000);
            classicCleanMessage(sender, ChatColor.AQUA.toString(), "Blocks linked now!", 0);

            Tp_Blocks.add(b1);
            Tp_Blocks.add(b2);
            tp_aktiv = true;
            b1 = null;
            b2 = null;
            p = null;

            TeleporterCommand.refreshNameArray(Tp_Blocks.size());

            return;
        }

        b1 = event.getClickedBlock();
        p = event.getPlayer();

        classicCleanMessage(sender, ChatColor.GOLD.toString(), "First Tp_Block selected!", 0);
        classicCleanMessage(sender, ChatColor.RED.toString(), "", 0);
    }

    private static void delay(int millisekunden){
        try {
            Thread.sleep(millisekunden);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    public void onWhoolClick(PlayerInteractEvent event) {
        String s;

        if(!tp_aktiv){
            return;
        }

        try {
            if (!aktiv || !event.hasBlock()) {
                System.out.println("Whool raus bei 1");
                return;
            }
        }catch (Exception e){
            System.out.println("Wirft Except: " + e);
        }

        if (event.getClickedBlock().getType() != Material.WHITE_WOOL) {
            System.out.println("Whool raus bei 2");
            return;
        }

        if(Tp_Blocks.size()%2 != 0){
            classicErrorMessage(sender, "Error while adding Tp_Blocks", "While adding other Tp_Block you are not allowed to use any Tp_Blocks!");
            return;
        }
        System.out.println("test2");


        if(!(Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.WHITE_WOOL))){
            return;
        }

        System.out.println("test3");

        Location loc = event.getClickedBlock().getLocation();

        loc.setY(loc.getY());
        loc.setX(Double.parseDouble(df.format(loc.getX())));
        loc.setZ(Double.parseDouble(df.format(loc.getZ())));
        loc.setPitch(0);
        loc.setYaw(0);

        System.out.println(loc.getBlock().getType());

        if(!(loc.getBlock().getType().equals(Material.WHITE_WOOL))){
            return;
        }


        System.out.println("vor for schleife");

        for(int i = 0; i < Tp_Blocks.size(); i++){
            System.out.println("in for schleife turn nr " + i);


            if(loc.equals(Tp_Blocks.get(i).getLocation())){
                if(i % 2  != 0){
                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "Starting Teleportation...", 0);
                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "...", 1000);
                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "...", 350);
                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "...", 350);

                    Location loc2 = Tp_Blocks.get(i -1).getLocation();
                    loc2.setY(loc2.getY() +1);
                    loc2.setX(loc2.getX() + 0.5);
                    loc2.setZ(loc2.getZ() + 0.5);
                    System.out.println(loc2);

                    event.getPlayer().teleport(loc2);

                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "Teleport done!", 350);
                }

                if(i % 2  == 0){
                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "Starting Teleportation...", 0);
                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "...", 1000);
                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "...", 350);
                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "...", 350);

                    Location loc2 = Tp_Blocks.get(i +1).getLocation();
                    loc2.setY(loc2.getY() +2);
                    loc2.setX(loc2.getX() + 0.5);
                    loc2.setZ(loc2.getZ() + 0.5);
                    System.out.println(loc2);

                    event.getPlayer().teleport(loc2);

                    classicCleanMessage(sender, ChatColor.AQUA.toString(), "Teleport done!", 350);
                }
            }
        }
        return;
    }

    public static void classicCleanMessage(CommandSender sender, String col, String part, int delay){
        if(!messagesStatus){
            return;
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        sender.sendMessage(col + part);
    }

    public static void classicErrorMessage(CommandSender sender, String part1 , String part2){
        if(!messagesStatus){
            return;
        }

        if(part2.equals("")){
            sender.sendMessage(colorErrorH + part1);
            return;
        }

        sender.sendMessage(colorErrorH + part1);
        sender.sendMessage(colorErrorN + part2);
    }

    public static boolean isMessagesStatus() {
        return messagesStatus;
    }

    public static void setMessagesStatus(boolean messagesStatus) {
        BlocksLinkedListener.messagesStatus = messagesStatus;
    }

    public static boolean isAktiv() {
        return aktiv;
    }

    public static void setAktiv(boolean aktiv) {
        BlocksLinkedListener.aktiv = aktiv;
    }

    public static boolean isTp_aktiv() {
        return tp_aktiv;
    }

    public static void setTp_aktiv(boolean tp_aktiv) {
        BlocksLinkedListener.tp_aktiv = tp_aktiv;
    }

    public static ArrayList<Block> getTp_Blocks() {
        return Tp_Blocks;
    }

    public static void setTp_Blocks(ArrayList<Block> tp_Blocks) {
        Tp_Blocks = tp_Blocks;
    }
}