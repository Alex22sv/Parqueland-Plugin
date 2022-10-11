package com.alex22sv.parqueland;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.concurrent.TimeUnit;

public class PlayerMoveListener implements Listener {
    private Parqueland main;
    private Util util;
    public PlayerMoveListener(Parqueland main){
        this.main = main;
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        util = new Util();
        if(main.frozenCooldown.asMap().containsKey(e.getPlayer().getUniqueId())){
            e.setCancelled(true);
            long distance  = main.frozenCooldown.asMap().get(e.getPlayer().getUniqueId()) - System.currentTimeMillis();
            e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + util.pluginName + " ¡Estás congelado! Espera " + TimeUnit.MILLISECONDS.toSeconds(distance) + " segundos para poder moverte." );
        }
    }
}
