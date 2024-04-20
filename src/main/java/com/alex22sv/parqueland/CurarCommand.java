package com.alex22sv.parqueland;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurarCommand implements CommandExecutor {
    private Util util;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        util = new Util();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.hasPermission("parqueland.CurarCommand")) {
                util.missingPermissionsText(player);
                return true;
            }
            if(args.length < 1){
                player.sendMessage(util.missingArgsText(command.getName(), "player"));
                return true;
            }
            if(Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                target.setHealth(20);
                target.sendMessage(ChatColor.YELLOW + "¡Has sido curado!");
                player.sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + ChatColor.YELLOW + " el jugador " + target.getName() + " ha sido curado.");
            } else if (args[0].toLowerCase() == "todos" || args[0].toLowerCase() == "@a") {
                for(Player target : Bukkit.getOnlinePlayers()){
                    target.setHealth(20);
                    target.sendMessage(ChatColor.YELLOW + "¡Has sido curado!");
                    player.sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + ChatColor.YELLOW + " el jugador " + target.getName() + " ha sido curado.");

                }
            } else {
                player.sendMessage(util.invalidPlayerText("player"));
            }
        } else {
            if(args.length < 1){
                System.out.println(util.missingArgsText(command.getName(), "console"));
                return true;
            }
            if(Bukkit.getPlayer(args[0]) != null) {
                Player target = Bukkit.getPlayer(args[0]);
                target.setHealth(20);
                target.sendMessage(ChatColor.YELLOW + "¡Has sido curado!");
                System.out.println(util.pluginName + " el jugador " + target.getName() + " ha sido curado.");
            } else if (args[0].toLowerCase() == "todos" || args[0].toLowerCase() == "@a") {
                for(Player target : Bukkit.getOnlinePlayers()){
                    target.setHealth(20);
                    target.sendMessage(ChatColor.YELLOW + "¡Has sido curado!");
                    System.out.println(util.pluginName + " el jugador " + target.getName() + " ha sido curado.");
                }
            } else {
                System.out.println(util.invalidPlayerText("console"));
            }
        }
        return true;
    }
}
