package es.javierdz.survivalUtils.listeners;


import es.javierdz.survivalUtils.SurvivalUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdListener implements Listener {
    private final SurvivalUtils plugin;

    public MotdListener(SurvivalUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event) {
        String linea1 = "&4Jefry &dMaricon";
        String linea2 = " ";
        String motd = linea1 + "\n" + linea2;

        event.setMotd(ChatColor.translateAlternateColorCodes('&', motd));
        event.setMaxPlayers(6);
    }

}
