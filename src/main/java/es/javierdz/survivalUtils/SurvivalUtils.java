package es.javierdz.survivalUtils;

import es.javierdz.survivalUtils.commands.Coords;
import es.javierdz.survivalUtils.commands.Eval;
import es.javierdz.survivalUtils.commands.Ping;
import es.javierdz.survivalUtils.commands.ToggleEnd;
import es.javierdz.survivalUtils.listeners.BanScreenListener;
import es.javierdz.survivalUtils.listeners.CabezonListener;
import es.javierdz.survivalUtils.listeners.EndBlockListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalUtils extends JavaPlugin {

    private boolean endHabilitado;
    private boolean coordsHabilitado;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.endHabilitado = getConfig().getBoolean("end-habilitado", true);
    this.coordsHabilitado = getConfig().getBoolean("coords-habilitado", true);

        if (getCommand("eval") != null) {
            getCommand("eval").setExecutor(new Eval());
        }
        if (getCommand("toggleend") != null) {
            getCommand("toggleend").setExecutor(new ToggleEnd(this));
        }
        if (getCommand("ping") != null) {
            getCommand("ping").setExecutor(new Ping());
        }
        if (getCommand("coords") != null) {
            getCommand("coords").setExecutor(new Coords(this));
        }
        getServer().getPluginManager().registerEvents(new EndBlockListener(this), this);
        getServer().getPluginManager().registerEvents(new BanScreenListener(), this);
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
