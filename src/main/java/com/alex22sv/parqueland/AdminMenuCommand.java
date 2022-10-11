package com.alex22sv.parqueland;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class AdminMenuCommand implements CommandExecutor {
    private static Util util;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        util = new Util();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("parqueland.AdminMenuCommand")) {
                Inventory adminMenu = Bukkit.createInventory(player, 45, ChatColor.RED.toString() + ChatColor.BOLD + "Menú para admins");
                // BAN
                ItemStack banOption = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta banOptionMeta = banOption.getItemMeta();
                banOptionMeta.setDisplayName(ChatColor.BLUE + "Banear");
                banOptionMeta.setLore(Arrays.asList(ChatColor.GRAY + "Banea a un jugador que se encuentre en línea!"));
                banOption.setItemMeta(banOptionMeta);
                adminMenu.setItem(19, banOption);

                // KICK
                ItemStack kickOption = new ItemStack(Material.IRON_SWORD);
                ItemMeta kickOptionMeta = kickOption.getItemMeta();
                kickOptionMeta.setDisplayName(ChatColor.BLUE + "Expulsar");
                kickOptionMeta.setLore(Arrays.asList(ChatColor.GRAY + "Expulsa a un jugador que se encuentre en línea \nEl jugador se podrá volver a unir."));
                kickOption.setItemMeta(kickOptionMeta);
                adminMenu.setItem(21, kickOption);

                // MUTE
                ItemStack muteOption = new ItemStack(Material.GOLDEN_SWORD);
                ItemMeta muteOptionMeta = muteOption.getItemMeta();
                muteOptionMeta.setDisplayName(ChatColor.BLUE + "Mutear");
                muteOptionMeta.setLore(Arrays.asList(ChatColor.GRAY + "Quita los permisos de enviar mensajes a un jugador. \nActualmente no disponible"));
                muteOption.setItemMeta(muteOptionMeta);
                adminMenu.setItem(23, muteOption);

                // FREEZE
                ItemStack freezeOption = new ItemStack(Material.STONE_SWORD);
                ItemMeta freezeOptionMeta = freezeOption.getItemMeta();
                freezeOptionMeta.setDisplayName(ChatColor.BLUE + "Congelar");
                freezeOptionMeta.setLore(Arrays.asList(ChatColor.GRAY + "Congela a un jugador para que no se pueda mover. \nActualmente no disponible"));
                freezeOption.setItemMeta(freezeOptionMeta);
                adminMenu.setItem(25, freezeOption);

                // CLOSE BUTTON
                ItemStack closeButton = new ItemStack(Material.BARRIER);
                ItemMeta closeButtonMeta = closeButton.getItemMeta();
                closeButtonMeta.setDisplayName(ChatColor.RED + "Cerrar menú para admins");
                closeButton.setItemMeta(closeButtonMeta);
                adminMenu.setItem(0, closeButton);

                // FRAME
                ItemStack frame = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
                for(int i : new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,20,22,24,26,27,28,29,30,31,32,33,34,35}) {
                    adminMenu.setItem(i, frame);
                }
                // OPEN INVENTORY
                player.openInventory(adminMenu);
            } else {
                util.missingPermissionsText(player);
            }

        } else {
            util.playerOnlyCommandText();
        }

        return true;
    }
}
