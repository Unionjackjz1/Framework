package me.Unionjackjz1.Framework.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {
    public Commands() {
        new HoleCommand();
        new ParticleCommand();
    }

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			sender.sendMessage("--- Framework Commands ---");
			sender.sendMessage("/framework hole <Player> - Makes a hole beneath a player to the void!");
			sender.sendMessage("/framework particle <arg> - Spawns particles in front of you! Use <arg> to set what particle you want!");
			return true;
		} else {
			FrameworkCommand fwcmd = FrameworkCommand.get(args[0].toLowerCase());
			if (fwcmd == null) {
				return false;
			} else {
				if (!fwcmd.hasPermission(sender)) {
					fwcmd.noPermission(sender);
				} else {
					List<String> sendingArgs = Arrays.asList(args).subList(1, args.length);
					fwcmd.execute(sender, sendingArgs);
				}
				return true;
			}
		}
	}
}
