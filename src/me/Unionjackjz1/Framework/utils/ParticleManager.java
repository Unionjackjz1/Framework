package me.Unionjackjz1.Framework.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleManager implements Runnable{
	
	private static Map<Player, Particle> tracker = new HashMap<>();
	private static Map<Player, Pattern> patterns = new HashMap<>();
	private static float   angle = 0;
	private static double  y     = 0;
	private static boolean up    = true;

	@Override
	public void run() {
		if (tracker.isEmpty()) return;
		for (Player player : tracker.keySet()) {
			display(player);
		}
		if (up) {
			if (y >= 1.9) up = false;
			else y += 0.04;
		} else {
			if (y <= 0) up = true;
			else y -= 0.04;
		}
		angle = angle >= 180 ? -180 : angle + 15;
	}
	
	private void display(Player player) {
		Pattern pattern = patterns.get(player);
		switch (pattern) {
			case CIRCLE:
				displayCircle(player);
				break;
			case HELIX:
				displayHelix(player);
				break;
			case DOUBLE_HELIX:
				displayDoubleHelix(player);
				break;
			case CROWN:
				displayCrown(player);
				break;
		}
	}
	
	private void displayCircle(Player player) {
		Location loc = player.getLocation().clone();
		loc.setPitch(0);
		loc.setYaw(angle);
		loc.add(loc.getDirection().multiply(1));
		loc.getWorld().spawnParticle(tracker.get(player), loc, 0, 0, 0, 1, 0);
	}
	
	private void displayHelix(Player player) {
		Location loc = player.getLocation().clone();
		loc.setPitch(0);
		loc.setYaw(angle);
		loc.setY(loc.getY() + y);
		loc.add(loc.getDirection().multiply(1));
		loc.getWorld().spawnParticle(tracker.get(player), loc, 0, 0, 0, 1, 0);
	}

	private void displayDoubleHelix(Player player) {
		displayHelix(player);
		Location loc = player.getLocation().clone();
		loc.setPitch(0);
		loc.setYaw(-angle);
		loc.setY(loc.getY() + y);
		loc.add(loc.getDirection().multiply(1));
		loc.getWorld().spawnParticle(tracker.get(player), loc, 0, 0, 0, 1, 0);
	}
	
	private void displayCrown(Player player) {
		for (int i = -180; i < 180; i += 20) {
			Location loc = player.getEyeLocation().clone();
			loc.setPitch(0);
			loc.setYaw(i);
			loc.setY(loc.getY() + 0.35);
			loc.add(loc.getDirection().multiply(0.4));
			loc.getWorld().spawnParticle(tracker.get(player), loc, 0, 0, 0, 1, 0);
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
	
	public static void setPattern(Player player, Pattern pattern) {
		patterns.put(player, pattern);
	}
	
	public static Pattern getPattern(Player player) {
		return patterns.get(player);
	}
	
	public static enum Pattern {
		CIRCLE,
		HELIX,
		DOUBLE_HELIX,
		CROWN;
	}
}
