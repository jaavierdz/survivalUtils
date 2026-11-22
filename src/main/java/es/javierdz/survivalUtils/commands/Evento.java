package es.javierdz.survivalUtils.commands;


import es.javierdz.survivalUtils.managers.EventManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Evento implements CommandExecutor, TabCompleter {
    private final EventManager eventManager;

    public Evento(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.getName().equalsIgnoreCase("Pepeluisa") && !sender.getName().equalsIgnoreCase("Javierdz")){
            sender.sendMessage("De que vas");
            return true;
        }
        if (args.length == 0) {
            ayuda(sender);
            return true;
        }

        String subcomando = args[0].toLowerCase();

        switch (subcomando) {
            case "iniciar":
                if (eventManager.isEventoActivo()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &cYa hay un evento activo, usa &e/evento terminar &cprimero"));
                    return true;
                }
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &cA la proxima pon el nombre crack (/evento iniciar <nombre>)"));
                    return true;
                }
                String nombre = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                eventManager.iniciarEvento(nombre);
                break;
            case "terminar" :
                if (!eventManager.isEventoActivo()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &cNo hay ningun evento activo"));
                    return true;
                }
                eventManager.terminarEvento();
                break;
            case "freeze" :
                eventManager.toggleFreeze();
                break;
            case "tpall" :
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &cEste comando solo lo puede usar un jugador"));
                    return true;
                }
                Player admin = (Player) sender;
                int contador = 0;
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (!p.equals(admin)) {
                        p.teleport(admin.getLocation());
                        p.playSound(p.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
                        contador++;
                    }
                    admin.sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&6&l[Eventos] &aHas tpeado a " + contador + " jugadores a ti"));
                }
                break;
            case "regla":
                if (!eventManager.isEventoActivo()) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &cSolo cuando hay evento activo"));
                    return true;
                }
                if (args.length < 2) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &fFalta la regla tio"));
                    return true;
                }
                switch (args[1].toLowerCase()) {
                    case "bloques":
                        boolean bloq = eventManager.toggleProtegerBloques();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &fProteccion de bloques: " + formatearEstado(bloq)));
                        break;
                    case "keepinv" :
                        boolean ki = eventManager.toggleKeepInventoryEvento();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &fKeep Inventory: " + formatearEstado(ki)));
                        break;
                    case "hambre":
                        boolean ham = eventManager.toggleSinHambre();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &fSin hambre: " + formatearEstado(ham)));
                        break;
                    case "caida" :
                        boolean cai = eventManager.toggleSinCaida();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &fDaño de caida: " + formatearEstado(cai)));
                        break;
                    default:
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[Eventos] &fque"));
                        break;
                }
            default:
                ayuda(sender);
                break;
        }

        return true;
    }

    private void ayuda(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&6&l===Eventos del survival===\n&e/evento iniciar <nombre> &6- &fAnuncia el inicio del evento\n&e/evento terminar &6- &fPues lo acaba\n&e/evento freeze &6- &fComo cuando te hacen una ss en holy\n&e/evento tpall &6- &fPues tpall\n&e/evento reglas bloques|keepinv|hambre|caida &6- &flas diferentes reglas que hay"));

    }
    private String formatearEstado(boolean activo) {
        return activo ? "&aActivado" : "&cDesactivado";
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.getName().equalsIgnoreCase("Pepeluisa") && !sender.getName().equalsIgnoreCase("Javierdz")) {
            return new ArrayList<>();
        }
        if (args.length == 1) {
            List<String> opciones = Arrays.asList("iniciar", "terminar", "freeze", "tpall");
            List<String> sugerencias = new ArrayList<>();
            for (String op : opciones) {
                if (op.startsWith(args[0].toLowerCase())) {
                    sugerencias.add(op);
                }
            }
            return sugerencias;
        }
        return new ArrayList<>();
    }
}
