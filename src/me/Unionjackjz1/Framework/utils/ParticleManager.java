package me.Unionjackjz1.Framework.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleManager implements Runnable{
	
	private static Map<Player, Particle> tracker = new HashMap<>();
	private static float  angle = 0;
	private static double y     = 0;

	@Override
	public void run() {
		for (Player player : tracker.keySet()) {
			Location display = player.getLocation().clone();
			display.setY(display.getY()+y);
			display.setPitch(0);
			display.setYaw(angle);
			display.add(display.getDirection().multiply(1));
			player.getWorld().spawnParticle(tracker.get(player), display, 0, 0, 0, 1, 0);
		}
		y = y >= 1.9 ? 0 : y + 0.04;
		angle = angle >= 180 ? -180 : angle + 15;
	}

	public static void track(Player player, Particle particle) {
		tracker.put(player, particle);
	}
	
	public static void remove(Player player) {
		tracker.remove(player);
	}
	
	public static boolean isTracking(Player player) {
		return tracker.containsKey(player);
	}
}
