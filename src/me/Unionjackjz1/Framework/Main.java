package me.Unionjackjz1.Framework;

import org.bukkit.plugin.java.JavaPlugin;

import me.Unionjackjz1.Framework.commands.Commands;
import me.Unionjackjz1.Framework.utils.ParticleManager;
import me.Unionjackjz1.Framework.utils.TempBlock;

public class Main extends JavaPlugin {
    public static Main instance;

    public void onEnable() {
        instance = this;
        getServer().getPluginCommand("framework").setExecutor(new Commands());
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new ParticleManager(), 0, 1);
        TempBlock.startReversion();
    }

    public void onDisable() {
    }
}