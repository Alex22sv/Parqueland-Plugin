package com.alex22sv.parqueland;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import static java.lang.Integer.parseInt;

public class EnviarObjetoCommand implements CommandExecutor {
    private Util util;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        util = new Util();
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("parqueland.EnviarObjetoCommand")){
                if(args.length < 2){
                    player.sendMessage(util.missingArgsText(command.getName(), "player"));
                    return true;
                }
                PlayerInventory playerInventory = player.getInventory();
                if(playerInventory.getItemInMainHand() != null && playerInventory.getItemInMainHand().getType() != Material.AIR){
                    if(Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        int newAmount = parseInt(args[1]);
                        ItemStack itemStack = playerInventory.getItemInMainHand();
                        String itemStackDisplayName = itemStack.getType().toString();

                        int originalAmount = itemStack.getAmount();
                        if(newAmount > originalAmount) {
                            player.sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + ChatColor.RED + " La cantidad escrita en el comando es mayor que la cantidad de objetos que tienes.");
                            return true;
                        } else if(newAmount <= 0){
                            player.sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + ChatColor.RED + " La cantidad escrita en el comando no puede ser menor o igual a cero.");
                            return true;
                        } else {
                            itemStack.setAmount(newAmount);
                            target.getInventory().addItem(itemStack);
                            target.sendMessage(ChatColor.YELLOW + "Has recibido " + args[1] + " " + itemStackDisplayName + " de " + player.getName());
                            itemStack.setAmount(originalAmount-newAmount);
                            player.sendMessage(ChatColor.YELLOW + "Se han entregado " + args[1] + " " + itemStackDisplayName + " a " + target.getName());

                        }
                    } else {
                        player.sendMessage(util.invalidPlayerText("player"));
                    }
                } else {
                    player.sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + ChatColor.RED + " No tienes ningún objeto en la mano.");
                }
            } else {
                util.missingPermissionsText(player);
            }
        } else {
            util.playerOnlyCommandText();
        }
        return true;
    }
}
