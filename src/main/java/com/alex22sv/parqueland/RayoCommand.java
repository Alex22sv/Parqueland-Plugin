package com.alex22sv.parqueland;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RayoCommand implements CommandExecutor {
    private Util util;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        util = new Util();
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(!player.hasPermission("parqueland.RayoCommand")){
                util.missingPermissionsText(player);
                return true;
            }
            if(args.length < 1){
                player.sendMessage(util.missingArgsText(command.getName(), "player"));
                return true;
            }
            if(Bukkit.getPlayer(args[0]) != null) {
                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    Bukkit.getWorld(target.getWorld().getUID()).strikeLightning(target.getLocation());
                    target.sendMessage(ChatColor.YELLOW + "¡Has sido golpeado con un rayo!");

                } catch (Exception e) {
                    throw e;
                }
            } else if (args[0].toLowerCase() == "todos" || args[0].toLowerCase() == "@a") {
                for(Player target : Bukkit.getOnlinePlayers()){
                    Bukkit.getWorld(target.getWorld().getUID()).strikeLightning(target.getLocation());
                    target.sendMessage(ChatColor.YELLOW + "¡Has sido golpeado con un rayo!");
                }
            } else {
                player.sendMessage(util.invalidPlayerText("player"));
            }

            }
        else {
            if(args.length < 1){
                System.out.println(util.missingArgsText(command.getName(), "console"));
                return true;
            }
            if(Bukkit.getPlayer(args[0]) != null) {
                try {
                    Player target = Bukkit.getPlayer(args[0]);
                    Bukkit.getWorld(target.getWorld().getUID()).strikeLightning(target.getLocation());
                    target.sendMessage(ChatColor.YELLOW + "¡Has sido golpeado con un rayo!");
                    System.out.println(util.pluginName + " " + target.getName() + " ha sido golpeado con un rayo.");
                } catch (Exception e) {
                    throw e;
                }
            } else if (args[0].toLowerCase() == "todos" || args[0].toLowerCase() == "@a") {
                for(Player target : Bukkit.getOnlinePlayers()){
                    Bukkit.getWorld(target.getWorld().getUID()).strikeLightning(target.getLocation());
                    target.sendMessage(ChatColor.YELLOW + "¡Has sido golpeado con un rayo!");
                }
            } else {
                System.out.println(util.invalidPlayerText("console"));
            }

        }
        return true;
    }
}
