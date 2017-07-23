package me.Unionjackjz1.Framework.commands;

import me.Unionjackjz1.Framework.Main;
import me.Unionjackjz1.Framework.utils.TempBlock;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HoleCommand extends FrameworkCommand {
    public HoleCommand() {
        super("hole", "/hole <player>", "Drops player into void.", new String[]{"hole"});
    }

    public static HashMap<Integer, ArrayList> arrays = new HashMap<Integer, ArrayList>();
    static int currentHole;
    static int currentRevert ;

    public static void execute(Player sender, List<String> args) {
        if(args.size() != 1) {
            HoleCommand.help(sender, false);
        }

        else {
            Player target = sender.getServer().getPlayer(args.get(0));
            Location targetLocation = target.getLocation();
            targetLocation.setY((int)targetLocation.getY()+1);
            targetLocation.setX((int)targetLocation.getX());
            targetLocation.setZ((int)targetLocation.getZ());
            if(target.isOnline()) {
                ArrayList<Block> blocks = new ArrayList<Block>();
                for(double i = targetLocation.getY(); i != 0; i--) {
                    Block block = target.getLocation().subtract(0, i, 0).getBlock();
                    new TempBlock(block, Material.AIR, (byte) 0);
                    blocks.add(block);
                }
                arrays.put(currentHole, blocks);
                currentHole++;
                sender.teleport(targetLocation);
                sender.sendMessage(ChatColor.RED + target.getName() + " has been punished with Hole!");

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        if (blocks.size() != 0) {
                            for (int i = 0; i < arrays.get(currentRevert).size(); i++) {
                                ArrayList<Block> playerBlocks = new ArrayList<Block>(arrays.get(currentRevert));
                                TempBlock.revertBlock(playerBlocks.get(i), Material.AIR);
                            }
                            arrays.remove(currentRevert);
                            currentRevert++;
                            sender.sendMessage(ChatColor.RED + target.getName() + "'s hole has been restored!");
                        }
                    }
                }.runTaskLater(Main.instance, 200);
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid player");
            }
        }
    }
}