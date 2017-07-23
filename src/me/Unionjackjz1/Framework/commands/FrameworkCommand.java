package me.Unionjackjz1.Framework.commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class FrameworkCommand {

    private String name;

    private String properUse;

    private String description;

    private String[] aliases;

    public static Map<String, FrameworkCommand> instances = new HashMap<>();
    public static Map<String, FrameworkCommand> aliasInstances = new HashMap<>();

    public FrameworkCommand(String name, String properUse, String description, String[] aliases) {
        this.name = name;
        this.properUse = properUse;
        this.description = description;
        this.aliases = aliases;
        instances.put(name.toLowerCase(), this);
        for (String alias : aliases) {
        	aliasInstances.put(alias.toLowerCase(), this);
        }
    }
    
    public static FrameworkCommand get(String arg) {
    	FrameworkCommand cmd = instances.get(arg);
    	if (cmd == null)  {
    		cmd = aliasInstances.get(arg);
    	}
    	
    	if (cmd != null) {
    		return cmd;
    	}
    	return null;
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

    public void help(CommandSender sender, boolean description) {
        sender.sendMessage(ChatColor.RED + "Proper Usage: " + properUse);
        if (description) {
            sender.sendMessage(ChatColor.YELLOW + this.description);
        }
    }
    
    public boolean hasPermission(CommandSender sender) {
    	return sender.hasPermission("onion.commands");
    }

    public void noPermission(CommandSender sender) {
        sender.sendMessage(ChatColor.RED + "You don't have permission to use " + name + " !");
    }

    protected boolean correctLength(CommandSender sender, int size, int min, int max) {
        if (size < min || size > max) {
            help(sender, false);
            return false;
        } else {
            return true;
        }
    }

    protected boolean isPlayer(CommandSender sender) {
        if (sender instanceof Player) {
            return true;
        } else {
            sender.sendMessage(ChatColor.RED + "You must be a player to use that command.");
            return false;
        }
    }
    
    public abstract void execute(CommandSender sender, List<String> args);
}
