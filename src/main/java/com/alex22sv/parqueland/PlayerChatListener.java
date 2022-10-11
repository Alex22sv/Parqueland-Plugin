package com.alex22sv.parqueland;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.concurrent.TimeUnit;

public class PlayerChatListener implements Listener {
    private Parqueland main;
    private Util util;
    public PlayerChatListener(Parqueland main) {this.main = main;}
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        util = new Util();
        if(main.mutedCooldown.asMap().containsKey(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
            long distance  = main.mutedCooldown.asMap().get(e.getPlayer().getUniqueId()) - System.currentTimeMillis();
            e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " Debes esperar " + TimeUnit.MILLISECONDS.toSeconds(distance) + " segundos para volver a enviar mensajes.");
        }
    }
}
