package com.alex22sv.parqueland;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReglasCommand implements CommandExecutor {
    private Parqueland main;
    private static Util util;
    public ReglasCommand(Parqueland main) {this.main = main;}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        util = new Util();
        String serverRules = main.getConfig().getString("ServerRules");
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage(util.alternateColorFormat(serverRules));
        } else {
            System.out.println(util.alternateColorFormat(serverRules));
        }
        return true;
    }
}
