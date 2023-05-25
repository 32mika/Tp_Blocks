package de.mika32.finaltest.listeners;

import de.mika32.finaltest.commands.TeleporterCommand;
import it.unimi.dsi.fastutil.ints.Int2IntSortedMaps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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



    @EventHandler
    public void onBlockClick(PlayerInteractEvent event) {
        delay(200);

        if(!aktiv){
            return;
        }

        if(!event.hasBlock()){
            return;
        }

        if(!event.hasItem()){
            return;
        }

        if(Objects.requireNonNull(event.getClickedBlock()).equals(Material.AIR)){
            return;
        }

        if( !(event.getPlayer().equals(p)) & p != null){
            event.getPlayer().sendMessage(ChatColor.DARK_PURPLE.toString() + "Another Player is already linking Blocks. Please try again later!");
            return;
        }

        if(!Objects.requireNonNull(event.getItem()).getType().equals(Material.STICK)){
            return;
        }

        if(!Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.WHITE_WOOL)){
            return;
        }


        if(b1 != (null)){
            b2 = event.getClickedBlock();

            event.getPlayer().sendMessage(ChatColor.GOLD.toString() + "Second Block selected! b2: " + b2.getChunk());
            event.getPlayer().sendMessage("");

            delay(1000); // war 2000

            event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "---Working_on_Connection---");

            if(b1.equals(b2)){
                event.getPlayer().sendMessage(ChatColor.RED.toString() + "Connection Error!");
                event.getPlayer().sendMessage(ChatColor.RED.toString() + "Can not connect one Block to it self!");
                return;
            }

            delay(2000); // war 5000

            event.getPlayer().sendMessage("");
            event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "Blocks linked now!");
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
        event.getPlayer().sendMessage(ChatColor.GOLD.toString() + "First Block selected! b1: " + b1.getChunk());
        event.getPlayer().sendMessage("");
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

        delay(200);

        if(!(tp_aktiv) | !(aktiv)){
            return;
        }

        System.out.println("test1");

        if(!event.hasBlock()) {
            return;
        }

        try {
            if(event.getPlayer().getItemInHand().getType().equals(Material.STICK)){
                return;
            }
        }catch (Exception e){
            System.out.println("no item");
        }


        if(Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.AIR)){
            return;
        }

        if(Tp_Blocks.size()%2 != 0){
            event.getPlayer().sendMessage(ChatColor.RED.toString() + "While adding other Tp_Block you are not allowed to use any Tp_Blocks!");
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
                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "Starting Teleportation...");
                    delay(1000);
                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "...");
                    delay(350);
                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "...");
                    delay(350);
                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "...");
                    delay(350);

                    Location loc2 = Tp_Blocks.get(i -1).getLocation();
                    loc2.setY(loc2.getY() +1);
                    loc2.setX(loc2.getX() + 0.5);
                    loc2.setZ(loc2.getZ() + 0.5);
                    System.out.println(loc2);

                    event.getPlayer().teleport(loc2);

                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "Teleport done!");
                }

                if(i % 2  == 0){
                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "Starting Teleportation...");
                    delay(1000);
                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "...");
                    delay(350);
                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "...");
                    delay(350);
                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "...");
                    delay(350);

                    Location loc2 = Tp_Blocks.get(i +1).getLocation();
                    loc2.setY(loc2.getY() +2);
                    loc2.setX(loc2.getX() + 0.5);
                    loc2.setZ(loc2.getZ() + 0.5);
                    System.out.println(loc2);

                    event.getPlayer().teleport(loc2);

                    event.getPlayer().sendMessage(ChatColor.AQUA.toString() + "Teleport done!");
                }
            }
        }
        return;
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