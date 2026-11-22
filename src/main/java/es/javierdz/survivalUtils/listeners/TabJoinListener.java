package es.javierdz.survivalUtils.listeners;


import es.javierdz.survivalUtils.managers.TabRankManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabJoinListener implements Listener {

    private final TabRankManager tabRankManager;

    public TabJoinListener(TabRankManager tabRankManager) {
        this.tabRankManager = tabRankManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        tabRankManager.asignarRangoPorDefecto(event.getPlayer());
    }
}
