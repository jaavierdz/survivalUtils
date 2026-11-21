package es.javierdz.survivalUtils.commands;


import es.javierdz.survivalUtils.SurvivalUtils;
import es.javierdz.survivalUtils.util.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class ToggleEnd implements CommandExecutor {
    private final SurvivalUtils plugin;

    public ToggleEnd(SurvivalUtils plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.getName().equals("Pepeluisa") || sender == Bukkit.getConsoleSender()) {
            boolean nuevoEstado = !plugin.isEndHabilitado();
            plugin.setEndHabilitado(nuevoEstado);

            if (nuevoEstado) {
                Bukkit.broadcastMessage(ChatColor.getColor("&a&l[+] El END ha sido HABILITADO"));

            } else {
                Bukkit.broadcastMessage(ChatColor.getColor("&c&l[-] El END ha sido DESHABILITADO"));
            }

            return true;
        }
        sender.sendMessage(ChatColor.getColor("&cNo tienes permiso para ejecutar este comando"));

        return true;
    }
}
