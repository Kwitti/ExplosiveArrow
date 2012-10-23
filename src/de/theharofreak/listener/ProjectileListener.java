package de.theharofreak.listener;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import de.theharofreak.ExplosiveArrow;

public class ProjectileListener implements Listener {
	
	private ExplosiveArrow plugin;
	public ProjectileListener (ExplosiveArrow plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void arrowEvent (ProjectileHitEvent event) {

		if(! (event.getEntity() instanceof Arrow))
			return;
		
		Arrow arrow = (Arrow) event.getEntity();
		Entity shooter = arrow.getShooter();
		
		if(! (shooter instanceof Player))
			return;
		
		Player player = (Player) shooter;
		
		if(! (plugin.playerList.contains(player.getName())))
			return;
		
		player.getWorld().createExplosion(arrow.getLocation(), plugin.getConfig().getInt("ExplosiveArrow.Radius"));
		
	}
}
