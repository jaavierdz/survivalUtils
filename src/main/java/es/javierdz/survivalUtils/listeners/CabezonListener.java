package es.javierdz.survivalUtils.listeners;


import es.javierdz.survivalUtils.SurvivalUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class CabezonListener implements Listener {
    private final SurvivalUtils plugin;

    public CabezonListener(SurvivalUtils plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victima = event.getEntity();
        Player asesino = victima.getKiller();

        // por si se suicida
        if (asesino == null || asesino.equals(victima)) {
            return;
        }
        if(!plugin.getConfig().getBoolean("cabezas-pvp", true)) {
            return;
        }

        ItemStack cabeza = crearCabeza(victima, asesino);
        event.getDrops().add(cabeza);
    }

    private ItemStack crearCabeza(Player victima, Player asesino) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if (meta != null) {
            meta.setOwningPlayer(victima);
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eCabeza de &f" + victima.getName()));
            meta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Asesinado por: " + asesino.getName())));
            item.setItemMeta(meta);
        }
        return item;
    }

}
