package me.Unionjackjz1.Framework.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class FrameworkCommand {

    private String name;

    private String properUse;

    private String description;

    private String[] aliases;

    public static Map<String, FrameworkCommand> instances = new HashMap<>();

    public FrameworkCommand(String name, String properUse, String description, String[] aliases) {
        this.name = name;
        this.properUse = properUse;
        this.description = description;
        this.aliases = aliases;
        instances.put(name, this);
    }

    public String getName() {
        return name;
    }

    public String getProperUse() {
        return properUse;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void help(Player sender, boolean description) {
        sender.sendMessage(ChatColor.RED + "Proper Usage: " + properUse);
        if (description) {
            sender.sendMessage(ChatColor.YELLOW + FrameworkCommand.description);
        }
    }

    public void noPermission(Player sender) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use " + name + " !");
    }

    protected boolean correctLength(Player sender, int size, int min, int max) {
        if (size < min || size > max) {
            FrameworkCommand.help(sender, false);
            return false;
        } else {
            return true;
        }
    }

    protected boolean isPlayer(Player sender) {
        if (sender instanceof Player) {
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to use that command.");
            return false;
        }
    }
    
    public abstract void execute(CommandSender sender, List<String> args);
}
