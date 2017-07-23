package me.Unionjackjz1.Framework.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleManager implements Runnable{
	
	private static Map<Player, Particle> tracker = new HashMap<>();
	private static float angle = 0;

	@Override
	public void run() {
		for (Player player : tracker.keySet()) {
			Location display = player.getLocation().clone();
			display.setPitch(0);
			display.setYaw(angle);
			display.add(display.getDirection().multiply(1));
			player.getWorld().spawnParticle(tracker.get(player), display, 0, 0, 0, 1, 0);
		}
		angle += 15;
		if (angle >= 180) {
			angle = -180;
		}
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
