package es.javierdz.survivalUtils.commands;


import es.javierdz.survivalUtils.util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Ping implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.getColor("&cSolamente se puede ejecutar si eres un jugador"));
                return true;
            }
            Player player = (Player) sender;
            int ping = player.getPing();
            player.sendMessage(ChatColor.getColor("&aTu ping es: " + formatearPing(ping)));
            return true;
        }

        Player objetivo = Bukkit.getPlayerExact(args[0]);
        if (objetivo == null || !objetivo.isOnline()) {
            sender.sendMessage(ChatColor.getColor("&cEse jugador no esta online"));
            return true;
        }
        int ping = objetivo.getPing();
        sender.sendMessage(ChatColor.getColor("&aEl ping de &e" + objetivo.getName() + " " + formatearPing(ping)));

        return true;
    }
    private String formatearPing(int ping) {
        if (ping < 80) {
            return "&2" + ping;
        } else if (ping < 160) {
            return "&e" + ping;
        } else {
            return "&c" + ping;
        }
    }
}
