package com.alex22sv.parqueland;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class MenusListener implements Listener {
    private Util util;

    private Parqueland main;
    public MenusListener(Parqueland main){
        this.main = main;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        util = new Util();
        if(util.alternateColorFormat(e.getView().getTitle()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "Menú para admins") && e.getCurrentItem() != null){
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();
            switch (e.getRawSlot()){
                case 0:
                    break;  
                case 19:
                    Inventory banPlayerInventory = util.createInv(player, Bukkit.getOnlinePlayers().size(), "ban");
                    player.closeInventory();
                    player.openInventory(banPlayerInventory);
                    return;
                case 21:
                    Inventory kickPlayerInventory = util.createInv(player, Bukkit.getOnlinePlayers().size(), "kick");
                    player.closeInventory();
                    player.openInventory(kickPlayerInventory);
                    return;
                case 23:
                    Inventory mutePlayerInventory = util.createInv(player, Bukkit.getOnlinePlayers().size(), "mute");
                    player.closeInventory();
                    player.openInventory(mutePlayerInventory);
                    return;
                case 25:
                    Inventory freezePlayerInventory = util.createInv(player, Bukkit.getOnlinePlayers().size(), "freeze");
                    player.closeInventory();
                    player.openInventory(freezePlayerInventory);
                default:
                    return;
            }
            player.closeInventory();
        } else if(util.alternateColorFormat(e.getView().getTitle()).equals(ChatColor.RED + "Banea a un jugador") && e.getCurrentItem() != null){
            e.setCancelled(true);
            if(e.getRawSlot() == 0){
                e.getWhoClicked().closeInventory();
                return;
            }
            ItemStack clickedItem = e.getCurrentItem();
            SkullMeta clickedItemMeta = (SkullMeta) clickedItem.getItemMeta();
            if(Bukkit.getPlayer(clickedItemMeta.getDisplayName()) != null){
                Player target = Bukkit.getPlayer(clickedItemMeta.getDisplayName());
                if(e.getWhoClicked().getUniqueId() == target.getUniqueId()){
                    e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + "¡No te puedes banear a ti mismo!");
                    e.getWhoClicked().closeInventory();
                    return;
                }
                Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "Baneado por un administrador.", null, null);
                target.kickPlayer(ChatColor.RED + "¡Has sido baneado del servidor! \n\nReason: " + ChatColor.YELLOW + "Baneado por un administrador.");
                e.getWhoClicked().sendMessage(ChatColor.YELLOW + "El jugador " + target.getDisplayName() + " ha sido baneado del servidor.");
            } else {
                e.getWhoClicked().sendMessage(ChatColor.RED + "No se ha encontrado ese jugador.");
            }
            e.getWhoClicked().closeInventory();
        } else if(util.alternateColorFormat(e.getView().getTitle()).equals(ChatColor.RED + "Expulsa a un jugador") && e.getCurrentItem() != null){
            e.setCancelled(true);
            if(e.getRawSlot() == 0){
                e.getWhoClicked().closeInventory();
                return;
            }
            ItemStack clickedItem = e.getCurrentItem();
            SkullMeta clickedItemMeta = (SkullMeta) clickedItem.getItemMeta();
            if(Bukkit.getPlayer(clickedItemMeta.getDisplayName()) != null){
                Player target = Bukkit.getPlayer(clickedItemMeta.getDisplayName());
                if(e.getWhoClicked().getUniqueId() == target.getUniqueId()){
                    e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + "¡No te puedes expulsar a ti mismo!");
                    e.getWhoClicked().closeInventory();
                    return;
                }
                target.kickPlayer(ChatColor.RED + "¡Has sido expulsado del servidor! \n\nReason: " + ChatColor.YELLOW + "Expulsado por un administrador.");
                e.getWhoClicked().sendMessage(ChatColor.YELLOW + "El jugador " + target.getDisplayName() + " ha sido expulsado del servidor.");
            } else {
                e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + "No se ha encontrado ese jugador.");
            }
            e.getWhoClicked().closeInventory();
        } else if(util.alternateColorFormat(e.getView().getTitle()).equals(ChatColor.RED + "Mutea a un jugador") && e.getCurrentItem() != null){
            e.setCancelled(true);
            if(e.getRawSlot() == 0){
                e.getWhoClicked().closeInventory();
                return;
            }
            ItemStack clickedItem = e.getCurrentItem();
            SkullMeta clickedItemMeta = (SkullMeta) clickedItem.getItemMeta();
            if(Bukkit.getPlayer(clickedItemMeta.getDisplayName()) != null){
                Player target = Bukkit.getPlayer(clickedItemMeta.getDisplayName());
                if(e.getWhoClicked().getUniqueId() == target.getUniqueId()){
                    e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + "¡No te puedes mutear a ti mismo!");
                    e.getWhoClicked().closeInventory();
                    return;
                }
                if(!main.mutedCooldown.asMap().containsKey(target.getUniqueId())){
                    main.mutedCooldown.put(target.getUniqueId(), System.currentTimeMillis() + 30000);
                    target.sendMessage(ChatColor.YELLOW + "Has sido muteado por 30 segundos. ¡No podrás enviar mensajes!");
                    e.getWhoClicked().sendMessage(ChatColor.YELLOW + "El jugador " + target.getDisplayName() + " ha sido muteado.");
                } else {
                    e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + target.getName() + " ya está muteado.");
                }
            } else {
                e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + "No se ha encontrado ese jugador.");
            }
            e.getWhoClicked().closeInventory();
        } else if(util.alternateColorFormat(e.getView().getTitle()).equals(ChatColor.RED + "Congela a un jugador") && e.getCurrentItem() != null){
            e.setCancelled(true);
            if(e.getRawSlot() == 0){
                e.getWhoClicked().closeInventory();
                return;
            }
            ItemStack clickedItem = e.getCurrentItem();
            SkullMeta clickedItemMeta = (SkullMeta) clickedItem.getItemMeta();
            if(Bukkit.getPlayer(clickedItemMeta.getDisplayName()) != null){
                Player target = Bukkit.getPlayer(clickedItemMeta.getDisplayName());
                if(e.getWhoClicked().getUniqueId() == target.getUniqueId()){
                    e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + "¡No te puedes congelar a ti mismo!");
                    e.getWhoClicked().closeInventory();
                    return;
                }
                if(!main.frozenCooldown.asMap().containsKey(target.getUniqueId())){
                    main.frozenCooldown.put(target.getUniqueId(), System.currentTimeMillis() + 30000);
                    target.sendMessage(ChatColor.YELLOW + "Has sido congelado por 30 segundos. ¡No podrás moverte!");
                    e.getWhoClicked().sendMessage(ChatColor.YELLOW + "El jugador " + target.getDisplayName() + " ha sido congelado.");
                } else {
                    e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + target.getName() + " ya está congelado.");
                }
            } else {
                e.getWhoClicked().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " " + ChatColor.RED + "No se ha encontrado ese jugador.");
            }
            e.getWhoClicked().closeInventory();
        }
    }
}
