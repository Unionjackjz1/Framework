package me.Unionjackjz1.Framework.commands;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import me.Unionjackjz1.Framework.Main;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Arrays;
import java.util.List;

public class Commands implements Listener {
    public Commands(Main plugin) {
        new HoleCommand();
        new ParticleCommand();
    }

    String[] holealiases     = new String[] { "/hole"};
    String[] particlealiases = new String[] { "/particle"};

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCommand(PlayerCommandPreprocessEvent event) {
        final String cmd = event.getMessage().toLowerCase();
        final String[] args = cmd.split("\\s+");
        List<String> sendingArgs = null;
        Player sender = event.getPlayer();

        sendingArgs = Arrays.asList(args).subList(1, args.length);

        if (Arrays.asList(holealiases).contains(args[0].toLowerCase()) && sender.hasPermission("onion.hole")) {
            HoleCommand.execute(sender, sendingArgs);
            event.setCancelled(true);
        }else if(Arrays.asList(holealiases).contains(args[0].toLowerCase()) && !(sender.hasPermission("onion.hole"))){
            HoleCommand.noPermission(sender);
            event.setCancelled(true);
        }
        if (Arrays.asList(particlealiases).contains(args[0].toLowerCase())) {
            ParticleCommand.execute(sender, sendingArgs);
            event.setCancelled(true);
        }
    }
}
