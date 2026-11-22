package es.javierdz.survivalUtils.listeners;


import es.javierdz.survivalUtils.SurvivalUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AntiPluginListener implements Listener {
    private final SurvivalUtils plugin;
    private final List<String> comandosBloqueados = Arrays.asList(
            "/pl",
            "/plugins",
            "/bukkit:pl",
            "/bukkit:plugins"
    );

    public AntiPluginListener(SurvivalUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (player.getName().equals("Pepeluisa") || player.getName().equals("Javierdz")) {
            return;
        }
        String mensaje = event.getMessage().toLowerCase().trim();
        String comandosBase = mensaje.split(" ")[0];

        if (comandosBloqueados.contains(comandosBase)) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo vas a ver los plugins"));
        }
    }

}
