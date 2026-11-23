package es.javierdz.survivalUtils.listeners;

import es.javierdz.survivalUtils.managers.EventManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class EventosListener implements Listener {
    private final EventManager eventManager;

    public EventosListener(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    private boolean puedeSaltarseReglas(Player player) {
        return player.getName().equalsIgnoreCase("Javierdz") || player.getName().equalsIgnoreCase("Pepeluisa");
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!eventManager.isCongelados()) {
            return;
        }
        Player player = event.getPlayer();

        if (puedeSaltarseReglas(player)) {
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

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!eventManager.isEventoActivo() && !eventManager.isProtegerBloques()) {
            return;
        }
        if (!puedeSaltarseReglas(event.getPlayer())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &cAhora no puedes romper bloques"));
        }

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!eventManager.isEventoActivo() && !eventManager.isKeepInventoryEvento()) return;
        event.setKeepInventory(true);
        event.setKeepLevel(true);
        event.getDrops().clear();
        event.setDroppedExp(0);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!eventManager.isEventoActivo() && !eventManager.isSinHambre()) return;

        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
        }

    }

    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if (!eventManager.isEventoActivo() && !eventManager.isSinCaida()) {
            return;
        }
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FALL) {
            event.setCancelled(true);
        }
    }

}
