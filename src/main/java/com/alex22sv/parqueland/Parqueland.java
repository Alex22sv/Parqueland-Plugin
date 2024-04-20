package com.alex22sv.parqueland;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang.text.StrSubstitutor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public final class Parqueland extends JavaPlugin implements Listener {
    private static Util util;
    public String pluginName;
    public Cache<UUID, Long> mutedCooldown = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).build();
    public Cache<UUID, Long> frozenCooldown = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).build();
    @Override
    public void onEnable() {
        util  = new Util();
        pluginName = util.pluginName;
        // Plugin startup logic
        System.out.println(pluginName + " Plugin iniciado correctamente.");
        //Register events
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new MenusListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        // Register config file
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        // Register commands
        getCommand("rayo").setExecutor(new RayoCommand());
        getCommand("adminmenu").setExecutor(new AdminMenuCommand());
        getCommand("dioses").setExecutor(new DiosesCommand());
        getCommand("reglas").setExecutor(new ReglasCommand(this));
        getCommand("enviarobjeto").setExecutor(new EnviarObjetoCommand());
        getCommand("curar").setExecutor(new CurarCommand());
        getCommand("alimentar").setExecutor(new AlimentarCommand());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Boolean customJoinMessage = getConfig().getBoolean("CustomJoinMessage.enabled");
        String joinMsg = util.alternateColorFormat(getConfig().getString("CustomJoinMessage.joinMsg"));
        if (customJoinMessage && joinMsg != "") {
            util.alternateColorFormat(joinMsg);
            HashMap<String, Object> params = new HashMap<>();
            params.put("JUGADOR", e.getPlayer().getDisplayName());
            params.put("CONECTADOS", Bukkit.getOnlinePlayers().size());
            params.put("MAX", Bukkit.getMaxPlayers());
            String result = StrSubstitutor.replace(joinMsg, params, "{", "}");
            e.setJoinMessage(result);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Boolean customQuitMessage = getConfig().getBoolean("CustomQuitMessage.enabled");
        String quitMsg = util.alternateColorFormat(getConfig().getString("CustomQuitMessage.quitMsg"));
        if(customQuitMessage && quitMsg != "") {
            HashMap<String, Object> params = new HashMap<>();
            params.put("JUGADOR", e.getPlayer().getDisplayName()) ;
            params.put("CONECTADOS", Bukkit.getOnlinePlayers().size()-1);
            params.put("MAX", Bukkit.getMaxPlayers());
            String result = StrSubstitutor.replace(quitMsg, params, "{", "}");
            e.setQuitMessage(result);
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(pluginName + " Plugin desactivado correctamente.");
    }
}
