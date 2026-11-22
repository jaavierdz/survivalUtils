package es.javierdz.survivalUtils.commands;

import es.javierdz.survivalUtils.SurvivalUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Coords implements CommandExecutor {
    private final SurvivalUtils plugin;
    public Coords(SurvivalUtils plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (!(sender instanceof Player) || !plugin.isCoordsHabilitado()) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cComando no habilitado"));
            return true;
        }
        Player player = (Player) sender;
        Location loc = player.getLocation();

        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();

        String nota = "";
        if (args.length > 0 ) {
            nota = "&8(&f" + String.join(" ", args) + "&8)";

        }
        String mensaje = "&e&l[Coords] &f" + player.getName() + " &7esta en " + x + ", " + y + ", " + z + " " + nota;
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', mensaje));

        for (Player online : Bukkit.getOnlinePlayers()) {
            online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.7f, 1.2f);
        }

        return true;
    }
}
