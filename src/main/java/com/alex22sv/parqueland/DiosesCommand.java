package com.alex22sv.parqueland;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DiosesCommand implements CommandExecutor {
    private static Util util;
    private String prefixMessage;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        util = new Util();
        prefixMessage = ChatColor.YELLOW.toString() + ChatColor.MAGIC + "|" + ChatColor.RESET + ChatColor.RED + " DIOSES " + ChatColor.YELLOW + ChatColor.MAGIC + "|" + ChatColor.RESET  ;
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.hasPermission("parqueland.DiosesCommand")) {
                util.missingPermissionsText(player);
                return true;
            }
            if(args.length < 2){
                player.sendMessage(util.missingArgsText(command.getName(), "player"));
                return true;
            }
            if(Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                target.sendMessage(prefixMessage + " " + ChatColor.AQUA + util.fromArgsToString(args));
            } else if (args[0].toLowerCase() == "todos" || args[0].toLowerCase() == "@a") {
                for(Player target : Bukkit.getOnlinePlayers()){
                    target.sendMessage(prefixMessage + " " + ChatColor.AQUA + util.fromArgsToString(args));
                }
            } else {
                player.sendMessage(util.invalidPlayerText("player"));
            }
        } else {
            if(args.length < 2){
                System.out.println(util.missingArgsText(command.getName(), "console"));
                return true;
            }
            if(Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                target.sendMessage(prefixMessage + " " + ChatColor.AQUA + util.fromArgsToString(args));
                System.out.println(util.pluginName + " El mensaje ha sido enviado a " + target.getName());
            } else if (args[0].toLowerCase() == "todos" || args[0].toLowerCase() == "@a") {
                for(Player target : Bukkit.getOnlinePlayers()){
                    target.sendMessage(prefixMessage + " " + ChatColor.AQUA + util.fromArgsToString(args));
                    System.out.println(util.pluginName + " El mensaje ha sido enviado a " + target.getName());
                }
            } else {
                System.out.println(util.invalidPlayerText("console"));
            }
        }

        return true;
    }
}
