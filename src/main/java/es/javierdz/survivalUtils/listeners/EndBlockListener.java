package es.javierdz.survivalUtils.listeners;


import es.javierdz.survivalUtils.SurvivalUtils;
import es.javierdz.survivalUtils.util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class EndBlockListener implements Listener{
    private final SurvivalUtils plugin;

    private final Map<UUID, Long> cooldownAvisos = new HashMap<>();
    public EndBlockListener(SurvivalUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (plugin.isEndHabilitado()) return;

        if (event.getTo() != null && event.getTo().getWorld() != null) {
            if(event.getTo().getWorld().getEnvironment() == World.Environment.THE_END) {
                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.getColor("&cEl end esta deshabilitado!"));
                gestionarIntento(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (plugin.isEndHabilitado()) return;

        if (event.getTo() != null && event.getTo().getWorld() != null) {
            if (event.getTo().getWorld().getEnvironment() == World.Environment.THE_END) {

                event.setCancelled(true);
                event.getPlayer().sendMessage(ChatColor.getColor("&cEl end esta deshabilitado!"));
                gestionarIntento(event.getPlayer());
            }
        }
    }

    private void gestionarIntento(Player maricon) {
        long ahora = System.currentTimeMillis();
        long ultimoAviso = cooldownAvisos.getOrDefault(maricon.getUniqueId(), 0L);

        if (ahora - ultimoAviso < 3000 ) return;

        cooldownAvisos.put(maricon.getUniqueId(), ahora);
        Player pepeluisa = Bukkit.getPlayerExact("Pepeluisa");
        if (pepeluisa != null && pepeluisa.isOnline()) {
            String mensaje = "el maricon de " + maricon.getName() + " ha intentado entrar al end";
            pepeluisa.sendMessage(mensaje);
        }

    }

}
