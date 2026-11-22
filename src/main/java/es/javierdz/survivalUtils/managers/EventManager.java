package es.javierdz.survivalUtils.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class EventManager {

    private boolean eventoActivo = false;
    private boolean congelados = false;
    private String nombreEvento = "";

    public void iniciarEvento(String nombre) {
        this.eventoActivo = true;
        this.nombreEvento = nombre;

        String titulo = ChatColor.translateAlternateColorCodes('&', "&6&l¡Inicio del evento!");
        String subtitulo = ChatColor.translateAlternateColorCodes('&', "&e" + nombre);

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(titulo, subtitulo, 10, 70, 20);
            p.playSound(p.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 1.0f, 1.0f);
        }

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                "\n&6&l[Eventos] &fHa comenzado el evento: &e&l" + nombre + "\n"));

    }

    public void terminarEvento() {
        String titulo = ChatColor.translateAlternateColorCodes('&', "&c&l¡Evento terminado!");
        String subtitulo = ChatColor.translateAlternateColorCodes('&', "&7Esta chulo, eh?");

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendTitle(titulo, subtitulo, 10, 70, 20);
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        }
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                "\n&6&l[Eventos] &fHa finalizado el evento"));
        this.eventoActivo = false;
        this.nombreEvento = "";
        this.congelados = false;
    }

    public boolean toggleFreeze() {
        this.congelados = !this.congelados;
        String estadoMsg = this.congelados ? "&b&lCONGELADOS" : "&a&lDESCONGELADOS";
        Sound sonido = this.congelados ? Sound.BLOCK_GLASS_BREAK : Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', "&6&l[Eventos] &fEstado: " + estadoMsg));
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.playSound(p.getLocation(), sonido, 1.0f, 1.0f);
        }
        return this.congelados;
    }
    public boolean isEventoActivo() { return eventoActivo; }
    public boolean isCongelados() { return congelados; }
    public String getNombreEvento() { return nombreEvento; }
}
