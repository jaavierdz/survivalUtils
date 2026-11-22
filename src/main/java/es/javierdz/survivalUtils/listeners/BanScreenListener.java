package es.javierdz.survivalUtils.listeners;


import es.javierdz.survivalUtils.util.ChatColor;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.text.SimpleDateFormat;

public class BanScreenListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() == AsyncPlayerPreLoginEvent.Result.KICK_BANNED) {
            BanEntry<?> banEntry = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(event.getName());

            String razon = "Sin razon";
            String autor = "El jose";
            String expiracion = "Permanente";

            if (banEntry != null) {
                if (banEntry.getReason() != null && !banEntry.getReason().isEmpty()) {
                    razon = banEntry.getReason();
                }
                if (banEntry.getSource() != null) {
                    autor = banEntry.getSource();
                }
                if (banEntry.getExpiration() != null) {
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    expiracion = formato.format(banEntry.getExpiration());
                }
            }
            String mensajeCustom =
                    "&cTu cuenta ha sido suspendida del servidor\n" +
                    "\n" +
                    "&cRazon: &f" + razon + "\n" +
                    "&cBaneado por: " + autor + "\n" +
                    "&cExpira el: " + expiracion + "\n" +
                    "maricon";
            event.setKickMessage(ChatColor.getColor(mensajeCustom));
        }
    }

}
