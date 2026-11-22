package es.javierdz.survivalUtils.listeners;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ChatFormatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getEntryTeam(player.getName());

        String prefix = "";
        if (team != null && !team.getPrefix().isEmpty()) {
            prefix = team.getPrefix();
        }
        String formato = prefix + ChatColor.WHITE + "%1$s" + ChatColor.GRAY + ": " + ChatColor.WHITE + "%2$s";
        event.setFormat(formato);
    }

}
