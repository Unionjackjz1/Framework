package me.Unionjackjz1.Framework.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.Unionjackjz1.Framework.Main;
import me.Unionjackjz1.Framework.utils.TempBlock;

public class HoleCommand extends FrameworkCommand {
    public HoleCommand() {
        super("hole", "/framework hole <player>", "Drops player into void.", new String[]{"hole", "h"});
    }

    @Override
    public void execute(CommandSender sender, List<String> args) {
        if(args.size() != 1) {
            help(sender, false);
            return;
        }
        
        Player target = Bukkit.getPlayer(args.get(0));
        if (target == null) {
        	sender.sendMessage(ChatColor.RED + "Player not found!");
        	return;
        }
        
        Location loc = target.getLocation().clone();
        new BukkitRunnable() {
        	
			@Override
			public void run() {
	        	loc.subtract(0, 1, 0);
	        	new TempBlock(loc.getBlock(), Material.AIR, (byte) 0).setRevertTime(10000);
			}
			
        }.runTaskTimer(Main.instance, 0, 1);
        
        sender.sendMessage(ChatColor.GREEN + target.getName() + " has been punished!");
    }
}