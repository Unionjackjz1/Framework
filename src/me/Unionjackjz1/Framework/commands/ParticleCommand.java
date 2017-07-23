package me.Unionjackjz1.Framework.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class ParticleCommand extends FrameworkCommand {
    public ParticleCommand() {
        super("particle", "/particle","Toggles Particles", new String[]{"/particle"});
    }

    public static void execute(Player sender, List<String> args) {
        sender.sendMessage(ChatColor.RED + "PARTICLE COMMAND STILL IN DEVELOPMENT");
    }
}