package es.javierdz.survivalUtils.util;


/**
 * Desc:
 *
 * @author Javier Rodríguez-Alarcón <9602488@alumnos.ufv.es>
 * @since 21/11/2026
 **/

public class ChatColor {
    public static String getColor(String message) {
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', message);
    }
}
