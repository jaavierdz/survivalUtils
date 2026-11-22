package es.javierdz.survivalUtils.managers;


import es.javierdz.survivalUtils.SurvivalUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TabRankManager {

    public void asignarRangoPorDefecto(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        String nombre = player.getName();

        if (scoreboard.getEntryTeam(nombre) != null) {
            player.setScoreboard(scoreboard);
            return;
        }
        Team teamMiembro = scoreboard.getTeam("03_miembro");
        if (teamMiembro != null) {
            teamMiembro.addEntry(nombre);
        }

        player.setScoreboard(scoreboard);
    }
}
