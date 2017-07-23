package me.Unionjackjz1.Framework.commands;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Unionjackjz1.Framework.utils.ParticleManager;

public class ParticleCommand extends FrameworkCommand {
    public ParticleCommand() {
        super("particle", "/framework particle <arg>","Toggles Particles", new String[]{"particle", "p"});
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
    	if (!correctLength(sender, args.size(), 0, 1)) {
    		return;
    	}
    	
    	if (!isPlayer(sender)) {
    		return;
    	}
    	
    	Player player = (Player) sender;
    	
    	boolean remove = false;
    	
    	Particle particle = Particle.FIREWORKS_SPARK;
    	if (args.size() == 1) {
    		if (args.get(0).equalsIgnoreCase("remove")) {
    			remove = ParticleManager.isTracking(player);
    			if (!remove) {
    				sender.sendMessage(ChatColor.RED + "Nothing to remove!");
    				return;
    			}
    		} else {
    			try {
    				particle = Particle.valueOf(args.get(0).toUpperCase());
    			} catch (IllegalArgumentException e) {
    				sender.sendMessage(ChatColor.RED + "Particle not found!");
    				return;
    			}
    		}
    	} else {
    		remove = ParticleManager.isTracking(player);
    	}
    	
    	if (remove) {
    		ParticleManager.remove(player);
    		sender.sendMessage(ChatColor.GREEN + "Particles removed!");
    	} else {
    		ParticleManager.track(player, particle);
    		sender.sendMessage(ChatColor.GREEN + "Particles set!");
    	}
    }
}
