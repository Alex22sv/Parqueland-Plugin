package com.alex22sv.parqueland;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Util {
    public String pluginName = "[Parqueland]";
    public void missingPermissionsText(Player player){
        player.sendMessage(ChatColor.LIGHT_PURPLE + pluginName + ChatColor.RED + " No tienes permisos para ejecutar este comando!");
    }
    public void playerOnlyCommandText() {
        System.out.println(pluginName + " Este comando no puede ser ejecutado en la consola del servidor.");
    }
    public String missingArgsText(String commandName, String typeOfSender){
        if(typeOfSender == "player") {
            String message = ChatColor.LIGHT_PURPLE + pluginName + ChatColor.RED + " Argumentos incompletos. Ejecuta el comando /help " + commandName;
            return message;
        } else if(typeOfSender  == "console") {
            String message = pluginName + " Argumentos incompletos. Ejecuta el comando /help " + commandName;
            return message;
        } else {
            return null;
        }
    }
    public String invalidPlayerText(String typeOfSender) {
        if(typeOfSender == "player"){
            String message = ChatColor.LIGHT_PURPLE + pluginName + ChatColor.RED + " Jugador no encontrado, revisa que esté conectado.";
            return message;

        } else if(typeOfSender == "console") {
            String message = pluginName + " Jugador no encontrado, revisa que esté conectado.";
            return message;
        } else {
            return null;

        }
    }
    public String fromArgsToString(String[] args){
        StringBuilder builderMsg = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builderMsg.append(args[i]).append(" ");
        }
        return builderMsg.toString();
    }
    public String fromArgsToStringAsList(String[] args){
        StringBuilder builderMsg = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builderMsg.append(args[i]).append(", ");
        }
        return builderMsg.toString();
    }
    public String alternateColorFormat(String str){
        // Alternate the color format
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    private Inventory inv;
    private String invTitle;
    public Inventory createInv(Player player, int size, String action){
        if(action == "ban"){
            invTitle = ChatColor.RED + "Banea a un jugador";
        } else if(action == "kick"){
            invTitle = ChatColor.RED + "Expulsa a un jugador";
        }  else if(action == "mute"){
            invTitle = ChatColor.RED + "Mutea a un jugador";
        }  else if(action == "freeze"){
            invTitle = ChatColor.RED + "Congela a un jugador";
        }
        if(size <= 18){
            inv = Bukkit.createInventory(player, 18, invTitle);
        } else if(size <= 27){
            inv = Bukkit.createInventory(player, 27, invTitle);
        } else if(size <= 36){
            inv = Bukkit.createInventory(player, 36, invTitle);
        } else if(size <= 45){
            inv = Bukkit.createInventory(player, 45, invTitle);
        } else {
            player.sendMessage(ChatColor.LIGHT_PURPLE + pluginName + ChatColor.RED + " Ocurrió un error al querer mostrar la lista de jugadores. Esto es causado porque el servidor tiene más de 45 jugadores. Contacta al desarrollador del plugin para solucionarlo.");
        }
        ItemStack closeButton = new ItemStack(Material.BARRIER);
        ItemMeta closeButtonMeta = closeButton.getItemMeta();
        closeButtonMeta.setDisplayName(ChatColor.RED + "Cerrar menú para admins");
        closeButton.setItemMeta(closeButtonMeta);
        inv.setItem(0, closeButton);
        for(Player onlinePlayer : Bukkit.getOnlinePlayers()){
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta playerHeadMeta = (SkullMeta) playerHead.getItemMeta();
            playerHeadMeta.setOwningPlayer(onlinePlayer);
            playerHeadMeta.setDisplayName(onlinePlayer.getName());
            playerHead.setItemMeta(playerHeadMeta);
            inv.addItem(playerHead);
        }
        return inv;
    }
}

