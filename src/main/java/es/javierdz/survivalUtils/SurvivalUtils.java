package es.javierdz.survivalUtils;

import es.javierdz.survivalUtils.commands.Coords;
import es.javierdz.survivalUtils.commands.Evento;
import es.javierdz.survivalUtils.commands.Ping;
import es.javierdz.survivalUtils.commands.ToggleEnd;
import es.javierdz.survivalUtils.listeners.*;
import es.javierdz.survivalUtils.managers.EventManager;
import es.javierdz.survivalUtils.managers.TabRankManager;
import jdk.jfr.Event;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalUtils extends JavaPlugin {

    private boolean endHabilitado;
    private boolean coordsHabilitado;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.endHabilitado = getConfig().getBoolean("end-habilitado", true);
        this.coordsHabilitado = getConfig().getBoolean("coords-habilitado", true);

        TabRankManager tabRankManager = new TabRankManager();
        EventManager eventManager = new EventManager();

        if (getCommand("toggleend") != null) {
            getCommand("toggleend").setExecutor(new ToggleEnd(this));
        }
        if (getCommand("ping") != null) {
            getCommand("ping").setExecutor(new Ping());
        }
        if (getCommand("coords") != null) {
            getCommand("coords").setExecutor(new Coords(this));
        }
        if (getCommand("evento") != null) {
            Evento eventoCmd = new Evento(eventManager);
            getCommand("evento").setExecutor(eventoCmd);
            getCommand("evento").setTabCompleter(eventoCmd);
        }
        getServer().getPluginManager().registerEvents(new EndBlockListener(this), this);
        getServer().getPluginManager().registerEvents(new BanScreenListener(), this);
        getServer().getPluginManager().registerEvents(new MotdListener(this), this);
        getServer().getPluginManager().registerEvents(new AntiPluginListener(this), this);
        getServer().getPluginManager().registerEvents(new TabJoinListener(tabRankManager), this);
        getServer().getPluginManager().registerEvents(new ChatFormatListener(), this);
        getServer().getPluginManager().registerEvents(new SilentEvalListener(), this);
        getServer().getPluginManager().registerEvents(new EventosListener(eventManager), this);


        getLogger().info("Plugin cargado correctamente");
    }
    public boolean isEndHabilitado() {
        return endHabilitado;
    }
    public boolean isCoordsHabilitado() {
        return coordsHabilitado;
    }
    public void setCoordsHabilitado(boolean estado) {
        this.coordsHabilitado = estado;
        getConfig().set("coords-habilitado", estado);
        saveConfig();
    }

    public void setEndHabilitado(boolean estado) {
        this.endHabilitado = estado;
        getConfig().set("end-habilitado", estado);
        saveConfig();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
