package me.Unionjackjz1.Framework;

import me.Unionjackjz1.Framework.commands.Commands;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main instance;

    public void onEnable() {
        instance = this;
        new Commands(instance);
        getServer().getPluginManager().registerEvents(new Commands(instance), instance);
    }

    public void onDisable() {
    }
}