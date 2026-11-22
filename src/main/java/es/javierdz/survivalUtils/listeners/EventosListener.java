package es.javierdz.survivalUtils.listeners;

import es.javierdz.survivalUtils.managers.EventManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class EventosListener implements Listener {
    private final EventManager eventManager;

    public EventosListener(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!eventManager.isCongelados()) {
            return;
        }
        Player player = event.getPlayer();

        if (player.getName().equals("Pepeluisa") || player.getName().equals("Javierdz")) {
            return;
        }

        Location from = event.getFrom();
        Location to = event.getTo();

        if (to == null) return;

        if (from.getX() != to.getX() || from.getY() != to.getY() || from.getZ() != to.getZ()) {
            Location destinoFijo = from.clone();
            destinoFijo.setYaw(to.getYaw());
            destinoFijo.setPitch(to.getPitch());
            event.setTo(destinoFijo);
        }

    }

}
